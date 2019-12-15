package com.ryuseicode.siap.entity.award;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name QuotationDetail
 * {@summary Entity class to model Quotation }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
public class QuotationDetail {
	/**
	 * quotationDetailId
	 */
	private int quotationDetailId;
	/**
	 * quotationId
	 */
	private int quotationId;
	/**
	 * competitorId
	 */
	private int competitorId;
	/**
	 * quotationDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate quotationDate;
	/**
	 * quotationDateText
	 */
	private String quotationDateText;
	/**
	 * creditCondition
	 */
	private String creditCondition;
	/**
	 * deliveryTerm
	 */
	private String deliveryTerm;
	/**
	 * active
	 */
	private int active;
	/**
	 * Default constructor
	 * @param quotationDetailId
	 * @param quotationId
	 * @param competitorId
	 * @param quotationDate
	 * @param quotationDateText
	 * @param creditCondition
	 * @param deliveryTerm
	 * @param active
	 */
	public QuotationDetail(int quotationDetailId, int quotationId, int competitorId, LocalDate quotationDate, String quotationDateText, String creditCondition, String deliveryTerm, int active) {
		this.setQuotationDetailId(quotationDetailId);
		this.setQuotationId(quotationId);
		this.setCompetitorId(competitorId);
		this.setQuotationDate(quotationDate);
		this.setQuotationDateText(quotationDateText);
		this.setCreditCondition(creditCondition);
		this.setDeliveryTerm(deliveryTerm);
		this.setActive(active);
	}
	/**
	 * @return the quotationDetailId
	 */
	public int getQuotationDetailId() {
		return quotationDetailId;
	}
	/**
	 * @param quotationDetailId the quotationDetailId to set
	 */
	public void setQuotationDetailId(int quotationDetailId) {
		this.quotationDetailId = quotationDetailId;
	}
	/**
	 * @return the quotationId
	 */
	public int getQuotationId() {
		return quotationId;
	}
	/**
	 * @param quotationId the quotationId to set
	 */
	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}
	/**
	 * @return the competitorId
	 */
	public int getCompetitorId() {
		return competitorId;
	}
	/**
	 * @param competitorId the competitorId to set
	 */
	public void setCompetitorId(int competitorId) {
		this.competitorId = competitorId;
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
	/**
	 * @return the quotationDateText
	 */
	public String getQuotationDateText() {
		return quotationDateText;
	}
	/**
	 * @param quotationDateText the quotationDateText to set
	 */
	public void setQuotationDateText(String quotationDateText) {
		this.quotationDateText = quotationDateText;
	}
	/**
	 * @return the creditCondition
	 */
	public String getCreditCondition() {
		return creditCondition;
	}
	/**
	 * @param creditCondition the creditCondition to set
	 */
	public void setCreditCondition(String creditCondition) {
		this.creditCondition = creditCondition;
	}
	/**
	 * @return the deliveryTerm
	 */
	public String getDeliveryTerm() {
		return deliveryTerm;
	}
	/**
	 * @param deliveryTerm the deliveryTerm to set
	 */
	public void setDeliveryTerm(String deliveryTerm) {
		this.deliveryTerm = deliveryTerm;
	}
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
}