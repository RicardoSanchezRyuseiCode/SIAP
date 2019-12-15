package com.ryuseicode.siap.entity.award;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name Quotation
 * {@summary Entity to modal Quotation object }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
public class Quotation {
	/**
	 * quotationId
	 */
	private int quotationId;
	/**
	 * adjudicationId
	 */
	private int adjudicationId;
	/**
	 * demanding
	 */
	private String demanding;
	/**
	 * demandingJob
	 */
	private String demandingJob;
	/**
	 * authorizer
	 */
	private String authorizer;
	/**
	 * authorizerJob
	 */
	private String authorizerJob;
	/**
	 * elaborationDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate elaborationDate;
	/**
	 * elaborationDateText
	 */
	private String elaborationDateText;
	/**
	 * active
	 */
	private int active;
	/**
	 * Defeault constructor
	 * @param quotationId
	 * @param adjudicationId
	 * @param demading
	 * @param demandingJob
	 * @param authorizer
	 * @param authorizerJob
	 * @param elaborationDate
	 * @param elaborationDateText
	 * @param active
	 */
	public Quotation(int quotationId, int adjudicationId, String demanding, String demandingJob, String authorizer, String authorizerJob, LocalDate elaborationDate, String elaborationDateText, int active) {
		this.setQuotationId(quotationId);
		this.setAdjudicationId(adjudicationId);
		this.setDemanding(demanding);
		this.setDemandingJob(demandingJob);
		this.setAuthorizer(authorizer);
		this.setAuthorizerJob(authorizerJob);
		this.setElaborationDate(elaborationDate);
		this.setElaborationDateText(elaborationDateText);
		this.setActive(active);
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
	 * @return the demanding
	 */
	public String getDemanding() {
		return demanding;
	}
	/**
	 * @param demanding the demanding to set
	 */
	public void setDemanding(String demanding) {
		this.demanding = demanding;
	}
	/**
	 * @return the demandingJob
	 */
	public String getDemandingJob() {
		return demandingJob;
	}
	/**
	 * @param demandingJob the demandingJob to set
	 */
	public void setDemandingJob(String demandingJob) {
		this.demandingJob = demandingJob;
	}
	/**
	 * @return the authorizer
	 */
	public String getAuthorizer() {
		return authorizer;
	}
	/**
	 * @param authorizer the authorizer to set
	 */
	public void setAuthorizer(String authorizer) {
		this.authorizer = authorizer;
	}
	/**
	 * @return the authorizerJob
	 */
	public String getAuthorizerJob() {
		return authorizerJob;
	}
	/**
	 * @param authorizerJob the authorizerJob to set
	 */
	public void setAuthorizerJob(String authorizerJob) {
		this.authorizerJob = authorizerJob;
	}
	/**
	 * @return the elaborationDate
	 */
	public LocalDate getElaborationDate() {
		return elaborationDate;
	}
	/**
	 * @param elaborationDate the elaborationDate to set
	 */
	public void setElaborationDate(LocalDate elaborationDate) {
		this.elaborationDate = elaborationDate;
	}
	/**
	 * @return the elaborationDateText
	 */
	public String getElaborationDateText() {
		return elaborationDateText;
	}
	/**
	 * @param elaborationDateText the elaborationDateText to set
	 */
	public void setElaborationDateText(String elaborationDateText) {
		this.elaborationDateText = elaborationDateText;
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
