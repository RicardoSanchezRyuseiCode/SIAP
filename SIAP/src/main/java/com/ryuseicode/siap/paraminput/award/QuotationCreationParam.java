package com.ryuseicode.siap.paraminput.award;

import java.util.List;

import com.ryuseicode.siap.entity.award.Quotation;
import com.ryuseicode.siap.entity.award.QuotationDetail;

/**
 * @name QuotationCreationParam
 * {@summary Creation Parameter class for QuotationCreationParam }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
public class QuotationCreationParam {
	/**
	 * Quotation
	 */
	private Quotation quotation;
	/**
	 * QuotationDetail
	 */
	private List<QuotationDetail> quotationDetails;
	/**
	 * @name QuotationCreationParam
	 * @param quotation
	 * @param quotationDetails
	 */
	public QuotationCreationParam(Quotation quotation, List<QuotationDetail> quotationDetails) {
		this.setQuotation(quotation);
		this.setQuotationDetails(quotationDetails);
	}
	/**
	 * @return the quotation
	 */
	public Quotation getQuotation() {
		return quotation;
	}
	/**
	 * @param quotation the quotation to set
	 */
	public void setQuotation(Quotation quotation) {
		this.quotation = quotation;
	}
	/**
	 * @return the quotationDetails
	 */
	public List<QuotationDetail> getQuotationDetails() {
		return quotationDetails;
	}
	/**
	 * @param quotationDetails the quotationDetails to set
	 */
	public void setQuotationDetails(List<QuotationDetail> quotationDetails) {
		this.quotationDetails = quotationDetails;
	}
}