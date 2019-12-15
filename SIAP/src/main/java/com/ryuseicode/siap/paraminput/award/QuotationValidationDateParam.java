package com.ryuseicode.siap.paraminput.award;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name QuotationValidationDateParam
 * {@summary Parameter class to validate the quotation date  }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 14, 2019
 */
public class QuotationValidationDateParam {
	/**
	 * adjudicationId
	 */
	private int adjudicationId;
	/**
	 * quotationDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate quotationDate;
	/**
	 * Default constructor
	 * @param adjudicationId
	 * @param quotationDate
	 */
	public QuotationValidationDateParam(int adjudicationId, LocalDate quotationDate) {
		this.setAdjudicationId(adjudicationId);
		this.setQuotationDate(quotationDate);
	}
	/**
	 * @return the adjudicationId
	 */
	public int getAdjudicationId() {
		return adjudicationId;
	}

	/**
	 * @param adjudicationId the adjudicationId to set
	 */
	public void setAdjudicationId(int adjudicationId) {
		this.adjudicationId = adjudicationId;
	}

	/**
	 * @return the quotationDate
	 */
	public LocalDate getQuotationDate() {
		return quotationDate;
	}

	/**
	 * @param quotationDate the quotationDate to set
	 */
	public void setQuotationDate(LocalDate quotationDate) {
		this.quotationDate = quotationDate;
	}
}
