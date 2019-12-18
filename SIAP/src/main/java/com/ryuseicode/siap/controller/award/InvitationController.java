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
import com.ryuseicode.siap.wrapper.award.imp.InvitationWrapper;

//import com.ryuseicode.siap.exception.ServiceException;
/**
 * @name InvitationController
 * {@summary Controller class to expose the endpoints of invitation }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 6, 2019
 */
@Controller
public class InvitationController {
	/**
	 * InvitationWrapper
	 */
	@Autowired
	private InvitationWrapper invitationWrapper;
	/**
	 * @name create
	 * {@summary Method to create invitation documents }
	 * @param adjudicationId
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/invitation/create", consumes = "application/json") 
	public ResponseEntity<Object> create(@RequestBody int adjudicationId, HttpServletResponse response) {
		try {
	    	return new ResponseEntity<Object>(invitationWrapper.create(adjudicationId), HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al crear adjudicación: %s", ex.getMessage()));
	    }
	}
}
