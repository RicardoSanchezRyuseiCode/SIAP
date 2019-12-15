package com.ryuseicode.siap.service.award.intf;

import com.ryuseicode.siap.entity.award.Quotation;

/**
 * @name IQuotationService
 * {@summary Interface to define the behavior of IQuotationService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
public interface IQuotationService {
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get Quotation by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	Quotation getByAdjudicationId(int adjudicationId);
	/**
	 * @name save
	 * {@summary Method to save a quotation }
	 * @param quotation
	 * @return
	 */
	void save(Quotation quotation) throws Exception;
}
