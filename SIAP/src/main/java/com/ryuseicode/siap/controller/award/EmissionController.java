package com.ryuseicode.siap.controller.award;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.award.EmissionValidateDaysParam;
import com.ryuseicode.siap.service.award.imp.EmissionService;
import com.ryuseicode.siap.wrapper.award.imp.EmissionWrapper;

@Controller
public class EmissionController {
	/**
	 * EmissionService
	 */
	@Autowired
	private EmissionService emissionService;
	
	@Autowired
	private EmissionWrapper emissionWrapper;
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get an emission by adjudication id }
	 * @param adjudicationId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/emission/getByAdjudicationId/{adjudicationId}", produces = "application/json")
	public Emission getByAdjudicationId(@RequestParam int adjudicationId) {
		return this.emissionService.getByAdjudicationId(adjudicationId);
	}
	/**
	 * @name post
	 * {@summary Method to save an emission object }
	 * @param emission
	 * @param response
	 */
	@PostMapping(path = "/award/emission/post", consumes = "application/json") 
	public void post(@RequestBody Emission emission, HttpServletResponse response) {
	    try {
	    	this.emissionService.save(emission); 
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar datos de emisión : %s", ex.getMessage()));
	    }
	}
	/**
	 * @name validateDays
	 * {@summary Method to validate is days are ok }
	 * @param emissionValidateDaysParam
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/emission/validateDays", consumes = "application/json") 
	public ResponseEntity<Object> validateDays(@RequestBody EmissionValidateDaysParam emissionValidateDaysParam, HttpServletResponse response) {
		try {
	    	return new ResponseEntity<Object>(emissionWrapper.ValidateDays(emissionValidateDaysParam.getSourceOrigin(), emissionValidateDaysParam.getDaysForPayment()), HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar adjudicación: %s", ex.getMessage()));
	    }
	}
}