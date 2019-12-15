package com.ryuseicode.siap.repository.award.intf;

import com.ryuseicode.siap.entity.award.Quotation;

/**
 * @name IQuotationRepository
 * {@summary Interface to define the behavior of QuotationRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
public interface IQuotationRepository {
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
	int save(Quotation quotation);
}
