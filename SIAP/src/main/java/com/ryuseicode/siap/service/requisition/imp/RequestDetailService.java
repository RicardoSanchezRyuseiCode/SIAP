package com.ryuseicode.siap.service.requisition.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.requisition.RequestDetail;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.requisition.imp.RequestDetailRepository;
import com.ryuseicode.siap.service.requisition.intf.IRequestDetailService;

/**
 * @name RequestDetailService
 * {@summary Service to implement the behavior of IRequestDetailService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Service
public class RequestDetailService implements IRequestDetailService {
	/**
	 * RequestDetailRepository
	 */
	@Autowired
	private RequestDetailRepository requestDetailRepository;
	/**
	 * @name getByRequestId
	 * {@summary Method to get list of request detail by request id }
	 * @param requestId
	 * @return
	 */
	public List<RequestDetail> getByRequestId(int requestId) {
		return this.requestDetailRepository.getByRequestId(requestId);
	}
	/**
	 * @name getByRequestDetailId
	 * {@summary Method to get requestdetail by id }
	 * @param requestDetailId
	 * @return
	 */
	public RequestDetail getByRequestDetailId(int requestDetailId) {
		return this.requestDetailRepository.getByRequestDetailId(requestDetailId);
	}
	/**
	 * @name getByAsset
	 * {@summary Method to get by Asset }
	 * @param asset
	 * @return
	 */
	public RequestDetail getByAsset(String asset, int requestId) {
		return this.requestDetailRepository.getByAsset(asset, requestId);
	}
	/**
	 * @name save
	 * {@summary Method to save a request detail }
	 * @param requestDetail
	 * @return
	 */
	public void save(RequestDetail requestDetail) throws Exception {
		// Check if asset already exist
		if(this.requestDetailRepository.getByAsset(requestDetail.getAsset(), requestDetail.getRequestId()) != null)
			throw new ServiceException("Un bien con el mismo nombre ya ha sido agregado a la requisición");
		// Set default data
		requestDetail.setActive(1);
		// save the detail
		this.requestDetailRepository.save(requestDetail);
		
	}
	/**
	 * @name save
	 * {@summary Method to save a list of request details }
	 * @param requestDetails
	 * @return
	 */
	public void save(List<RequestDetail> requestDetails) throws Exception {
		// Loop to set default data
		for(RequestDetail requestDetail : requestDetails) {
			requestDetail.setActive(1);
		}
		// Save the list
		this.requestDetailRepository.save(requestDetails);
	}
	/**
	 * @name delete
	 * @summary Method to delete request detail id
	 * @param requestDetailId
	 * @return
	 */
	public void delete(int requestDetailId) throws Exception {
		// Check if asset already exist
		if(this.requestDetailRepository.getByRequestDetailId(requestDetailId) == null)
			throw new ServiceException("El bien a eliminar no ha sido encontrado");
		// Delete the detail
		this.requestDetailRepository.delete(requestDetailId);
	}
	/**
	 * @name deleteByRequestId
	 * {@summary Method to delete request details by requestId }
	 * @param requestId
	 * @return
	 */
	public void deleteByRequestId(int requestId) {
		// delete the elements
		this.requestDetailRepository.deleteByRequestId(requestId);
	}
}
