package com.ryuseicode.siap.controller.award;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.award.Contract;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.wrapper.award.imp.ContractWrapper;
/**
 * @name ContractController
 * {@summary Controller class to expose the endpoints of Contract }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 17, 2019
 */
@Controller
public class ContractController {
	/**
	 * ContractWrapper
	 */
	@Autowired
	private ContractWrapper contractWrapper;
	/**
	 * @name create
	 * {@summary Method to create a new contract}
	 * @param contract
	 * @return
	 */
	@PostMapping(path = "/award/contract/create", consumes = "application/json")
	public ResponseEntity<Object> create(@RequestBody Contract contract) {
		try {
	    	return new ResponseEntity<Object>(this.contractWrapper.create(contract), HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al crear contrato: %s", ex.getMessage()));
	    }
	}
}
