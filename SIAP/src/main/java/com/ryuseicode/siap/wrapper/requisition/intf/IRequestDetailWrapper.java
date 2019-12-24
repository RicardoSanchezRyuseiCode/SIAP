package com.ryuseicode.siap.wrapper.requisition.intf;

import com.ryuseicode.siap.entity.requisition.RequestDetail;

/**
 * @name IRequestDetailWrapper
 * {@summary Interface to define the behavior of IRequestDetailWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 23, 2019
 */
public interface IRequestDetailWrapper {
	/**
	 * @name save
	 * {@summary Method to save a request detail }
	 * @param requestDetail
	 * @throws Exception
	 */
	void save(RequestDetail requestDetail) throws Exception;
	/**
	 * @name delete
	 * {@summary Method to delete a request detail }
	 * @param requestDetail
	 * @throws Exception
	 */
	void delete(int requestDetailId) throws Exception;
}
