package com.ryuseicode.siap.repository.requisition.intf;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuseicode.siap.entity.requisition.Request;

/**
 * @name IRequestRepository
 * {@summary Interface to define the behavior of IRequestRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IRequestRepository {
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
	int save(Request request);
	/**
	 * @name updateStatus
	 * {@summary Method to update status of request }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	int updateStatus(int requestId, String status);
	/**
	 * @name updateAmount
	 * {@summary Method to update amount }
	 * @param requestId
	 * @param amount
	 * @return
	 */
	int updateAmount(int requestId, double amount);
	/**
	 * @name closeDate
	 * {@summary Method to update close date }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	int updateCloseDate(int requestId, LocalDateTime closeDate);
	
}
