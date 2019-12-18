package com.ryuseicode.siap.controller.award;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.award.AdjudicationDocument;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;

/**
 * @name AdjudicationDocumentController
 * {@summary Controlle to expose adjudication document controller }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 18, 2019
 */
@Controller
public class AdjudicationDocumentController {

	/**
	 * AdjudicationDocumentService
	 */
	@Autowired
	private AdjudicationDocumentService adjudicationDocumentService;
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get adjudicationId}
	 * @param adjudicationId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/adjudicationDocument/getByAdjudicationId/{adjudicationId}", produces = "application/json")	
    public List<String> getByAdjudicationId(@PathVariable int adjudicationId) {
		List<AdjudicationDocument> documents = this.adjudicationDocumentService.GetByAdjudicationId(adjudicationId);
		ArrayList<String> files = new ArrayList<String>();
		for(AdjudicationDocument document : documents) {
			files.add(document.getName());
		}
		return files;
    }
	/**
	 * @name download
	 * {@summary Method to download document }
	 * @param fileName
	 * @param request
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/adjudicationDocument/download/{fileName:.+}")
	public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName, HttpServletRequest request) {
		try {
			// Get document
			AdjudicationDocument adjudicationDocument =  adjudicationDocumentService.GetByName(fileName);
			// Get file content
			String contentType = request.getServletContext().getMimeType(adjudicationDocument.getPath());
			if(contentType == null) {
	            contentType = "application/octet-stream";
	        }
			// return the resource
			return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
	                .body(new ByteArrayResource(Files.readAllBytes(Paths.get(adjudicationDocument.getPath()))));	
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al descargar anexo: %s", ex.getMessage()));
	    }
	}
}
