package com.ryuseicode.siap.service.requisition.intf;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuseicode.siap.entity.requisition.Request;

/**
 * @name IRequestService
 * {@summary Service to define the behavior of IRequestService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IRequestService {
	/**
	 * @name GetByEntryId
	 * {@summary Method to get list of request by EntryId }
	 * @param entryId
	 * @return
	 */
	List<Request> getByEntryId(int entryId);
	/**
	 * @name getByEntryIdStatus
	 * {@summary Method to get by status and entryid }
	 * @param entryId
	 * @param status
	 * @return
	 */
	List<Request> getByEntryIdStatus(int entryId, String status);
	/**
	 * @name GetByRequestId
	 * {@sumamry Method to get request by id }
	 * @param requestId
	 * @return
	 */
	Request getByRequestId(int requestId);
	/**
	 * @name getByCode
	 * {@summary Method to get by code }
	 * @param code
	 * @return
	 */
	Request getByCode(String code);
	/**
	 * @name save
	 * {@summary Method to save request }
	 * @param request
	 * @return
	 */
	void save(Request request) throws Exception;
	/**
	 * @name updateStatus
	 * {@summary Method to update status of request }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	void updateStatus(int requestId, String status) throws Exception;
	/**
	 * @name updateAmount
	 * {@summary Method to update amount }
	 * @param requestId
	 * @param amount
	 * @throws Exception
	 */
	void updateAmount(int requestId, double amount) throws Exception;
	/**
	 * @name closeDate
	 * {@summary Method to update close date }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	void updateCloseDate(int requestId, LocalDateTime closeDate) throws Exception;
}
