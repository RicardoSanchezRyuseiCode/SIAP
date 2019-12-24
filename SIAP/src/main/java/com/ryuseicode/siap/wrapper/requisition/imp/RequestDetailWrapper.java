package com.ryuseicode.siap.wrapper.requisition.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.requisition.Request;
import com.ryuseicode.siap.entity.requisition.RequestDetail;
import com.ryuseicode.siap.service.requisition.imp.RequestDetailService;
import com.ryuseicode.siap.service.requisition.imp.RequestService;
import com.ryuseicode.siap.wrapper.requisition.intf.IRequestDetailWrapper;
/**
 * @name RequestDetailWrapper
 * {@summary Wrapper to implement the IRequestWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 23, 2019
 */
@Service
@Transactional
public class RequestDetailWrapper implements IRequestDetailWrapper {
	/**
	 * RequestService
	 */
	@Autowired
	private RequestService requestService;
	/**
	 * RequestDetailService
	 */
	@Autowired
	private RequestDetailService requestDetailService;	
	/**
	 * @name save
	 * {@summary Method to save a request detail }
	 * @param requestDetail
	 * @throws Exception
	 */
	public void save(RequestDetail requestDetail) throws Exception {
		// Get request
		Request request = this.requestService.getByRequestId(requestDetail.getRequestId());
		// Calculate request total
		requestDetail.setTotal(requestDetail.getUnitPrice() * requestDetail.getQuantity());
		// Sum the amount to update request
		double amount = requestDetail.getTotal() + request.getAmount();
		// save request detail
		this.requestDetailService.save(requestDetail);
		// update amount
		this.requestService.updateAmount(request.getRequestId(), amount);
		
	}
	/**
	 * @name delete
	 * {@summary Method to delete a request detail }
	 * @param requestDetail
	 * @throws Exception
	 */
	public void delete(int requestDetailId) throws Exception {
		// Get request detail
		RequestDetail requestDetail = this.requestDetailService.getByRequestDetailId(requestDetailId);
		// Get request
		Request request = this.requestService.getByRequestId(requestDetail.getRequestId());
		// Sum the amount to update request
		double amount = request.getAmount() - requestDetail.getTotal();
		// save request detail
		this.requestDetailService.delete(requestDetailId);
		// update amount
		this.requestService.updateAmount(request.getRequestId(), amount);
						
	}
}
