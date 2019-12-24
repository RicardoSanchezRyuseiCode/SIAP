package com.ryuseicode.siap.controller.requisition;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.requisition.RequestDetail;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.service.requisition.imp.RequestDetailService;
import com.ryuseicode.siap.wrapper.requisition.imp.RequestDetailWrapper;
/**
 * @name RequestDetailController
 * {@summary Controller to expose endpoints for RequestDetail }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 23, 2019
 */
@Controller
public class RequestDetailController {
	/**
	 * RequestDetailService
	 */
	@Autowired
	private RequestDetailService requestDetailService;
	/**
	 * RequestDetailWrapper
	 */
	@Autowired
	private RequestDetailWrapper requestDetailWrapper; 
	/**
	 * @name getByRequestId
	 * {@summary Method to get request details by request id }
	 * @param requestId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/requestDetail/getByRequestId/{requestId}", produces = "application/json")	
    public List<RequestDetail> getByRequestId(@PathVariable int requestId) {
		return this.requestDetailService.getByRequestId(requestId);
	}
	/**
	 * @name getByRequestId
	 * {@summar Method to get request detail by request detail id }
	 * @param requestDetailId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/requestDetail/getByRequestDetailId/{requestDetailId}", produces = "application/json")	
    public RequestDetail getByRequestDetailId(@PathVariable int requestDetailId) {
		return this.requestDetailService.getByRequestDetailId(requestDetailId);
	}
	/**
	 * @name Post
	 * {@summary Method to post a requestDetail }
	 * @param requestDetail
	 */
	@PostMapping(path = "/requisition/requestDetail/post", consumes = "application/json")
	public void post(@RequestBody RequestDetail requestDetail, HttpServletResponse response) {
		try {
	    	this.requestDetailWrapper.save(requestDetail);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar detalle de requisicion: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name delete
	 * {@summary Method to delete a request detail }
	 * @param requestDetailId
	 */
	@DeleteMapping(path = "/requisition/requestDetail/delete/{requestDetailId}", consumes = "application/json")
	public void delete(@PathVariable int requestDetailId, HttpServletResponse response) {
		try {
	    	this.requestDetailWrapper.delete(requestDetailId);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar detalle de requisicion: %s", ex.getMessage()));
	    }
	}
}
