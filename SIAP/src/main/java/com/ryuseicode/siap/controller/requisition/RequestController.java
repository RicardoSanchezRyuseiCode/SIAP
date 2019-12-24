package com.ryuseicode.siap.controller.requisition;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.requisition.Request;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.service.requisition.imp.RequestService;
import com.ryuseicode.siap.wrapper.requisition.imp.RequestWrapper;

/**
 * @name RequestController
 * {@summary Controller to expose request endpoints }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Controller
public class RequestController {
	/**
	 * RequestService
	 */
	@Autowired
	private RequestService requestService;
	/**
	 * RequestWrapper
	 */
	@Autowired
	private RequestWrapper requestWrapper;
	/**
	 * @name getByEntryId
	 * {@summary Method to get by entryId }
	 * @param entryId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/request/getByEntryId/{entryId}", produces = "application/json")	
    public List<Request> getByEntryId(@PathVariable int entryId) {
		return this.requestService.getByEntryId(entryId);
	}
	/**
	 * @name getByEntryIdStatus
	 * {@summary MEthod to get entry by status }
	 * @param entryId
	 * @param status
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/request/getByEntryIdStatus/{entryId}/{status}", produces = "application/json")	
    public List<Request> getByEntryIdStatus(@PathVariable int entryId, @PathVariable String status) {
		return this.requestService.getByEntryIdStatus(entryId, status);
	}
	/**
	 * @name getByRequestId
	 * {@summary Method to get by requestId }
	 * @param requestId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/request/getByRequestId/{requestId}", produces = "application/json")	
    public Request getByRequestId(@PathVariable int requestId) {
		return this.requestService.getByRequestId(requestId);
	}
	/**
	 * @name getByCode
	 * {@summary Method to get by code }
	 * @param code
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/request/getByCode/{code}", produces = "application/json")	
    public Request getByCode(@PathVariable String code) {
		return this.requestService.getByCode(code);
	}
	/**
	 * @name getNextCode
	 * {@summary Method to get next code }
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/request/getNextCode", produces = "application/json")
	public Request getNextCode() {
		Request request = new Request(0,0,UUID.randomUUID().toString().substring(0, 7).toUpperCase(), "", 0, null, null, 1);
		return  request;
	}
	/**
	 * @name post
	 * {@summary Method to create a request }
	 * @param request
	 */
	@PostMapping(path = "/requisition/request/post", consumes = "application/json")
	public void post(@RequestBody Request request, HttpServletResponse response) {
		 try {
	    	this.requestService.save(request);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar requisicion: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name close
	 * {@summary Method to close a request }
	 * @param requestId
	 */
	@PostMapping(path = "/requisition/request/close", consumes = "application/json")
	public void close(@RequestBody int requestId, HttpServletResponse response) {
		 try {
	    	this.requestWrapper.close(requestId);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al cerrar requisicion: %s", ex.getMessage()));
	    }
	}
}