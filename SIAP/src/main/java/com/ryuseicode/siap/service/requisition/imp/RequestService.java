package com.ryuseicode.siap.service.requisition.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.requisition.Request;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.requisition.imp.RequestRepository;
import com.ryuseicode.siap.service.requisition.intf.IRequestService;

/**
 * @name RequestService
 * {@summary Service to implement the behavior of IRequestService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Repository
public class RequestService implements IRequestService {
	/**
	 * Request Repository
	 */
	@Autowired
	private RequestRepository requestRepository;
	/**
	 * @name GetByEntryId
	 * {@summary Method to get list of request by EntryId }
	 * @param entryId
	 * @return
	 */
	public List<Request> getByEntryId(int entryId) {
		return this.requestRepository.getByEntryId(entryId);
	}
	/**
	 * @name getByEntryIdStatus
	 * {@summary Method to get by status and entryid }
	 * @param entryId
	 * @param status
	 * @return
	 */
	public List<Request> getByEntryIdStatus(int entryId, String status) {
		return this.requestRepository.getByEntryIdStatus(entryId, status);
	}
	/**
	 * @name GetByRequestId
	 * {@sumamry Method to get request by id }
	 * @param requestId
	 * @return
	 */
	public Request getByRequestId(int requestId) {
		return this.requestRepository.getByRequestId(requestId);
	}
	/**
	 * @name getByCode
	 * {@summary Method to get by code }
	 * @param code
	 * @return
	 */
	public Request getByCode(String code) {
		return this.requestRepository.getByCode(code);
	}
	/**
	 * @name save
	 * {@summary Method to save request }
	 * @param request
	 * @return
	 */
	public void save(Request request) throws Exception {
		// Set default data
		request.setCreationDate(LocalDateTime.now());
		request.setAmount(0);
		request.setStatus(Request.STATUS_IN_PROCESS);
		request.setActive(1);
		// save the request
		this.requestRepository.save(request);
	}
	/**
	 * @name updateStatus
	 * {@summary Method to update status of request }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	public void updateStatus(int requestId, String status) throws Exception {
		// checks if exist
		if(this.requestRepository.getByRequestId(requestId) == null)
			throw new ServiceException("No se ha encontrado la requisición a actualizar");
		// update the status
		this.requestRepository.updateStatus(requestId, status);
	}
	/**
	 * @name updateAmount
	 * {@summary Method to update amount }
	 * @param requestId
	 * @param amount
	 * @throws Exception
	 */
	public void updateAmount(int requestId, double amount) throws Exception {
		// checks if exist
		if(this.requestRepository.getByRequestId(requestId) == null)
			throw new ServiceException("No se ha encontrado la requisición a actualizar");
		// update the status
		this.requestRepository.updateAmount(requestId, amount);
	}
	/**
	 * @name updateCloseDate
	 * {@summary Method to update close date }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	public void updateCloseDate(int requestId, LocalDateTime closeDate) throws Exception {
		// checks if exist
		if(this.requestRepository.getByRequestId(requestId) == null)
			throw new ServiceException("No se ha encontrado la requisición a actualizar");
		// update close date
		this.requestRepository.updateCloseDate(requestId, closeDate);
	}
}
