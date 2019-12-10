package com.ryuseicode.siap.controller.award;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.award.Opening;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.wrapper.award.imp.OpeningWrapper;
/**
 * @name OpeningController
 * {@summary Controller to expose endpoints for Opening }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
@Controller
public class OpeningController {
	/**
	 * OpeningWrapper
	 */
	@Autowired
	private OpeningWrapper openingWrapper;
	/**
	 * @name post
	 * {@summary Method to save an opening object }
	 * @param opening
	 * @param response
	 */
	@PostMapping(path = "/award/opening/post", consumes = "application/json") 
	public void post(@RequestBody Opening opening, HttpServletResponse response) { 
		try {
	    	this.openingWrapper.save(opening);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar datos de apertura : %s", ex.getMessage()));
	    }
	}
}