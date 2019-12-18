package com.ryuseicode.siap.controller.award;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.award.AnnexCreationParam;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AnnexService;
import com.ryuseicode.siap.wrapper.award.imp.AnnexWrapper;
/**
 * @name AnnexController
 * {@summary Controller to expose annex endpoints }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
@Controller
public class AnnexController {
	/**
	 * AnnexService
	 */
	@Autowired
	private AnnexService annexService;
	/**
	 * AnnexWrapper
	 */
	@Autowired
	private AnnexWrapper annexWrapper;
	/**
	 * folderProperties
	 */
	@Autowired
	private FolderProperties folderProperties;
	/**
	 * @name getbyAdjudicationId
	 * {@summary Method to get a collection of AnnexParamOutput by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/annex/getByAdjudicationId/{adjudicationId}")
	public List<AnnexParamOutput> getbyAdjudicationId(@PathVariable("adjudicationId") int adjudicationId) {
		return this.annexService.GetByAdjudicationId(adjudicationId);
	}
	/**
	 * @name uplodad
	 * {@summary Method to upload an asset file to import the quantity}
	 * @param file
	 * @param response
	 * @return
	 */
	@PostMapping("/award/annex/upload") 
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
		// Check if file is not empty
		if (file.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No ha enviado algpun archivo a procesar");
        }		
		try {
			// create timestampt
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			// Get file extension
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			// Define string
			String pathStr = folderProperties.getUpload()  + "/"  + dateFormat.format(date) + "." + extension;
			// Define path
			Path path = Paths.get(pathStr);
			// Get byter of file
            byte[] bytes = file.getBytes();
            // Write files to path
            Files.write(path, bytes);
            // After save the file process to save the list of goods
            return new ResponseEntity<Object>(this.annexWrapper.importFile(pathStr), HttpStatus.OK);  
        } 
		catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al importar anexos: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name create
	 * {@summary Method to create annex and documents for adjudication }
	 * @param annexCreationParam
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/annex/create", consumes = "application/json") 
	public ResponseEntity<Object> create(@RequestBody AnnexCreationParam  annexCreationParam, HttpServletResponse response) {
		try {
	    	return new ResponseEntity<Object>(this.annexWrapper.create(annexCreationParam.getAdjudicationId(), annexCreationParam.getAnnexs()), HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al crear adjudicación: %s", ex.getMessage()));
	    }
	}
}
