package com.ryuseicode.siap.controller.award;



import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.award.QuotationCreationParam;
import com.ryuseicode.siap.paraminput.award.QuotationValidationDateParam;
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
	
}
