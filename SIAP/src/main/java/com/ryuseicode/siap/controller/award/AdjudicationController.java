package com.ryuseicode.siap.controller.award;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.award.AdjudicationCloseParam;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.wrapper.award.imp.AdjudicationWrapper;

/**
 * @name AdjudicationController
 * {@summary Controller to expose endpoints for Adjudication }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
@Controller
public class AdjudicationController {
	/**
	 * AdjudicationService
	 */
	@Autowired
	private AdjudicationService adjudicationService;
	/**
	 * AdjudicationWrapper
	 */
	@Autowired
	private AdjudicationWrapper adjudicationWrapper;
	/**
	 * @name Get
	 * {@summary Method to get a collection of adjudication objects }
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/adjudication/get", produces = "application/json")	
    public List<Adjudication> get() {
		return this.adjudicationService.get();		
    }
	/**
	 * @name Get
	 * {@summary Method to get by adjudication id }
	 * @param adjudicationId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/adjudication/getById/{adjudicationId}", produces = "application/json")	
    public Adjudication getById(@PathVariable int adjudicationId) {
		return this.adjudicationService.getById(adjudicationId);
    }
	/**
	 * @name getPending
	 * {@summary Method to get pending adjudications }
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/adjudication/getPending", produces = "application/json")	
    public List<Adjudication> getPending() {
		return this.adjudicationService.getPending();		
    }
	/**
	 * @name getFinished
	 * {@summary Method to get finished adjudications }
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/adjudication/getFinished", produces = "application/json")	
    public List<Adjudication> getFinished() {
		return this.adjudicationService.getFinished();		
    }
	/**
	 * @name getByProcedureNumber
	 * {@summary Method to get adjudication by procedureNumber }
	 * @param procedureNumber
	 * @return
	 */
	@PostMapping(path = "/award/adjudication/getByProcedureNumber", consumes = "application/json")
	public ResponseEntity<Object> getByProcedureNumber(@RequestBody String procedureNumber) {
		Adjudication adjudication = this.adjudicationService.getByProcedureNumber(procedureNumber);
		return new ResponseEntity<Object>(adjudication, HttpStatus.OK);
    }
	/**
	 * @name post
	 * {@summary Method to get save an adjudication }
	 * @param adjudication
	 * @param response
	 */
	@PostMapping(path = "/award/adjudication/post", consumes = "application/json") 
	public void post(@RequestBody Adjudication adjudication, HttpServletResponse response) {
	    try {
	    	this.adjudicationService.save(adjudication);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar adjudicación: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name validateAmount
	 * {@summary Method to validate if amount is correct }
	 * @param adjudication
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/adjudication/validateAmount", consumes = "application/json") 
	public ResponseEntity<Object> validateAmount(@RequestBody Adjudication adjudication, HttpServletResponse response) {
	    try {
	    	return new ResponseEntity<Object>(adjudicationWrapper.validateAmount(adjudication), HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar adjudicación: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name close
	 * {@summary Method to close adjudication }
	 * @param adjudicationCloseParam
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/adjudication/close", consumes = "application/json") 
	public ResponseEntity<Object> close(@RequestBody AdjudicationCloseParam adjudicationCloseParam, HttpServletResponse response) {
		try {
	    	return new ResponseEntity<Object>(adjudicationWrapper.closeAdjudication(adjudicationCloseParam.getAdjudicationId(), adjudicationCloseParam.getCloseDate()), HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al cerrar adjudicación: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name finishAdjudication
	 * {@summary Method to finish adjudication }
	 * @param adjudication
	 * @param response
	 */
	@PostMapping(path = "/award/adjudication/finish", consumes = "application/json") 
	public void finish(@RequestBody int adjudicationId, HttpServletResponse finishAdjudication) {
	    try {
	    	this.adjudicationWrapper.finishAdjudication(adjudicationId);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al finalizar adjudicación: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name Delete
	 * {@summary Method to delete adjudication }
	 * @param adjudicationId
	 * @return
	 */
	@DeleteMapping(path = "/award/adjudication/delete/{adjudicationId}", consumes = "application/json") 
	public ResponseEntity<Object> delete(@PathVariable int adjudicationId) { 
		try {
			this.adjudicationService.delete(adjudicationId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} 
	    catch (ServiceException ex) {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al eliminar el adjudicación %s", ex.getMessage()));
	    }
	}
	
}