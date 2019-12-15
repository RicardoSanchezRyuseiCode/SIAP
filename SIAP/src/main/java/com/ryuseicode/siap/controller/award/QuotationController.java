package com.ryuseicode.siap.controller.award;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.award.AdjudicationDocument;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.award.QuotationCreationParam;
import com.ryuseicode.siap.paraminput.award.QuotationValidationDateParam;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;
import com.ryuseicode.siap.wrapper.award.imp.QuotationWrapper;

/**
 * @name QuotationController
 * {@summary Controller to expose endpoints for quotation }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 14, 2019
 */
@Controller
public class QuotationController {
	/**
	 * QuotationWrapper
	 */
	@Autowired
	private QuotationWrapper QuotationWrapper;
	/**
	 * AdjudicationDocumentService
	 */
	@Autowired
	private AdjudicationDocumentService adjudicationDocumentService;
	/**
	 * @name create
	 * {@summary Method to create quotation for adjudication }
	 * @param quotationCreationParam
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/quotation/create", consumes = "application/json") 
	public ResponseEntity<Object> create(@RequestBody QuotationCreationParam  quotationCreationParam, HttpServletResponse response) {
		try {
	    	return new ResponseEntity<Object>(this.QuotationWrapper.create(quotationCreationParam.getQuotation(), quotationCreationParam.getQuotationDetails()) , HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al crear cotización: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name validateDate
	 * {@summary Method to validate date of quotation }
	 * @param quotationValidationDateParam
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/quotation/validateDate", consumes = "application/json") 
	public ResponseEntity<Object> validateDate(@RequestBody QuotationValidationDateParam  quotationValidationDateParam, HttpServletResponse response) {
		try {
	    	return new ResponseEntity<Object>(this.QuotationWrapper.validateQuotationDate(quotationValidationDateParam.getAdjudicationId(), quotationValidationDateParam.getQuotationDate()) , HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al crear cotización: %s", ex.getMessage()));
	    }
	} 
	/**
	 * @name download
	 * {@summary Method to download document }
	 * @param fileName
	 * @param request
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/quotation/download/{fileName:.+}")
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
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al descargar apertura: %s", ex.getMessage()));
	    }
	}	
	
}
