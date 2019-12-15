package com.ryuseicode.siap.service.award.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.QuotationDetail;
import com.ryuseicode.siap.repository.award.imp.QuotationDetailRepository;
import com.ryuseicode.siap.service.award.intf.IQuotationDetailService;

/**
 * @name QuotationDetailService
 * {@summary Service class to implement the behavior of IQuotationDetailService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
@Service
public class QuotationDetailService implements IQuotationDetailService {
	/**
	 * QuotationDetailRepository
	 */
	@Autowired
	private QuotationDetailRepository quotationDetailRepository; 
	/**
	 * @name getByQuotationId
	 * {@summary Method to get quotation detail by quotationid }
	 * @param quotationId
	 * @return
	 */
	public List<QuotationDetail> getByQuotationId(int quotationId) {
		return this.quotationDetailRepository.getByQuotationId(quotationId);
	} 
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get quotation detail }
	 * @param competitorId
	 * @return
	 */
	public QuotationDetail getByCompetitorId(int competitorId) {
		return this.quotationDetailRepository.getByCompetitorId(competitorId);
	}
	/**
	 * @name save
	 * {@summary Method to save a collection of quotationDetails }
	 * @param quotationDetails
	 */
	public void save(int quotationId, List<QuotationDetail> quotationDetails) {
		// Get current quotation details
		List<QuotationDetail> currentQuotationDetails = this.quotationDetailRepository.getByQuotationId(quotationId);
		// Define map of current proposals
		Map<Integer, QuotationDetail> dicCurrentQuotationDetails = new HashMap<Integer, QuotationDetail>();
		// Loop in proposal
		for(QuotationDetail currentQuotationDetail : currentQuotationDetails) {
			if(!dicCurrentQuotationDetails.containsKey(currentQuotationDetail.getCompetitorId()))
				dicCurrentQuotationDetails.put(currentQuotationDetail.getCompetitorId(), currentQuotationDetail);
		}
		// Define dictionary for distinct proposal
		Map<Integer, QuotationDetail> dicNewQuotationDetail = new HashMap<Integer, QuotationDetail>();
		for(QuotationDetail quotationDetail : quotationDetails) {
			// Check if dictionary contains proposal
			if(!dicCurrentQuotationDetails.containsKey(quotationDetail.getCompetitorId()))
				// Check if is distinct
				if(!dicNewQuotationDetail.containsKey(quotationDetail.getCompetitorId())) {
					// Set default data
					quotationDetail.setQuotationId(quotationId);
					// Add to dictionary
					dicNewQuotationDetail.put(quotationDetail.getCompetitorId(), quotationDetail);
				}
		}
		// Create array
		ArrayList<QuotationDetail> newQuotationDetails = new ArrayList<QuotationDetail>();
		dicNewQuotationDetail.forEach((competitorId, quotationDetail) -> {
			quotationDetail.setActive(1);
			newQuotationDetails.add(quotationDetail);
		});
		// save distinct values
		this.quotationDetailRepository.save(newQuotationDetails);
	}
}