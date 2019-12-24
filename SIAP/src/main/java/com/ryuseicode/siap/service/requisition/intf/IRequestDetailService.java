package com.ryuseicode.siap.service.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.RequestDetail;

/**
 * @name IRequestDetailService
 * {@summary Interface to define the behavior of IRequestDetailService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IRequestDetailService {
	/**
	 * @name getByRequestId
	 * {@summary Method to get list of request detail by request id }
	 * @param requestId
	 * @return
	 */
	List<RequestDetail> getByRequestId(int requestId);
	/**
	 * @name getByRequestDetailId
	 * {@summary Method to get requestdetail by id }
	 * @param requestDetailId
	 * @return
	 */
	RequestDetail getByRequestDetailId(int requestDetailId);
	/**
	 * @name getByAsset
	 * {@summary Method to get by Asset }
	 * @param asset
	 * @return
	 */
	RequestDetail getByAsset(String asset, int requestId);
	/**
	 * @name save
	 * {@summary Method to save a request detail }
	 * @param requestDetail
	 * @return
	 */
	void save(RequestDetail requestDetail) throws Exception;
	/**
	 * @name save
	 * {@summary Method to save a list of request details }
	 * @param requestDetails
	 * @return
	 */
	void save(List<RequestDetail> requestDetails) throws Exception;
	/**
	 * @name delete
	 * @summary Method to delete request detail id
	 * @param requestDetailId
	 * @return
	 */
	void delete(int requestDetailId) throws Exception;
	/**
	 * @name deleteByRequestId
	 * {@summary Method to delete request details by requestId }
	 * @param requestId
	 * @return
	 */
	void deleteByRequestId(int requestId);
}
