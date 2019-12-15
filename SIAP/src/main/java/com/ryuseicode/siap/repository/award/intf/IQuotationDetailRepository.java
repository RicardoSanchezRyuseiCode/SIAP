package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.QuotationDetail;

/**
 * @name IQuotationDetailRepository
 * {@summary Interface to define the behavior of IQuotationDetail }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
public interface IQuotationDetailRepository {
	/**
	 * @name getByQuotationId
	 * {@summary Method to get quotation detail by quotationid }
	 * @param quotationId
	 * @return
	 */
	List<QuotationDetail> getByQuotationId(int quotationId); 
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get quotation detail }
	 * @param competitorId
	 * @return
	 */
	QuotationDetail getByCompetitorId(int competitorId);
	/**
	 * @name save
	 * {@summary Method to save a collection of quotationDetails }
	 * @param quotationDetails
	 */
	int save(List<QuotationDetail> quotationDetails);
}
