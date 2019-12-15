package com.ryuseicode.siap.service.award.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Quotation;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.QuotationRepository;
import com.ryuseicode.siap.service.award.intf.IQuotationService;

/**
 * @name QuotationService
 * {@summary Service class to implement the behavior of QuotationService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
@Service
public class QuotationService implements IQuotationService {
	/**
	 * QuotationRepository
	 */
	@Autowired
	private QuotationRepository quotationRepository;
	
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get Quotation by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	public Quotation getByAdjudicationId(int adjudicationId) {
		return this.quotationRepository.getByAdjudicationId(adjudicationId);
	}
	/**
	 * @name save
	 * {@summary Method to save a quotation }
	 * @param quotation
	 * @return
	 */
	public void save(Quotation quotation) throws Exception {
		// Check if adjudication have already a quotation
		if(this.quotationRepository.getByAdjudicationId(quotation.getAdjudicationId()) != null)
			throw new ServiceException("La adjudicación ya cuenta con información de cotiazación");
		// Set the default data
		quotation.setActive(1);
		// Save the quotation
		this.quotationRepository.save(quotation);
	}
}