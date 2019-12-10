package com.ryuseicode.siap.controller.util;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.wrapper.util.imp.DateParserWrapper;

/**
 * @name DateParserController
 * {@summary Controller to expose endpoints to parse dates }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 5, 2019
 */
@Controller
public class DateParserController {
	/**
	 * DateParserWrapper
	 */
	@Autowired
	private DateParserWrapper dataParserWrapper;
	/**
	 * @name shortDate
	 * {@summary Method to parse short date}
	 * @param date
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/util/dateParser/shortDate", consumes = "application/json") 
	public ResponseEntity<Object> shortDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, HttpServletResponse response) { 
		try 
		{
			return new ResponseEntity<Object>(dataParserWrapper.shortDate(date) , HttpStatus.OK);
		}
		catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al traducir fecha: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name time
	 * {@summary Method to parse time to text}
	 * @param time
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/util/dateParser/time", consumes = "application/json") 
	public ResponseEntity<Object> time(@RequestParam("time") String time, HttpServletResponse response) { 
		try 
		{
			return new ResponseEntity<Object>(dataParserWrapper.time(time), HttpStatus.OK);
		}
		catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al traducir tiempo: %s", ex.getMessage()));
	    }
	}
}
