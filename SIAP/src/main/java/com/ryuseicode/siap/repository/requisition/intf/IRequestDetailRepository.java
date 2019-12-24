package com.ryuseicode.siap.repository.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.RequestDetail;

/**
 * @name IRequestDetailRepository
 * {@summary Interface to define the behavior of IRequestDetailRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IRequestDetailRepository {
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
	int save(RequestDetail requestDetail);
	/**
	 * @name save
	 * {@summary Method to save a list of request details }
	 * @param requestDetails
	 * @return
	 */
	int save(List<RequestDetail> requestDetails);
	/**
	 * @name delete
	 * @summary Method to delete request detail id
	 * @param requestDetailId
	 * @return
	 */
	int delete(int requestDetailId);
	/**
	 * @name deleteByRequestId
	 * {@summary Method to delete request details by requestId }
	 * @param requestId
	 * @return
	 */
	int deleteByRequestId(int requestId);
}
