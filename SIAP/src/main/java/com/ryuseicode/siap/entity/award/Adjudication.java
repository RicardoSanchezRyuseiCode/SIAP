package com.ryuseicode.siap.entity.award;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name Adjudication
 * {@summary Entity class to module Adjudication }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public class Adjudication {	
	/**
	 * SOURCE_FEDERAL
	 */
    public static final String SOURCE_FEDERAL = "Federal";
    /**
     * SOURCE_STATE
     */
    public static final String SOURCE_STATE = "Estatal";
    /**
     * SOURCE_MIXED
     */
    public static final String SOURCE_MIXED = "Mixto";	
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * ProcedureNumber
	 */
	private String procedureNumber;
	/**
	 * ContractType
	 */
	private String contractType;
	/**
	 * Modality
	 */
	private String modality;
	/**
	 * SourceOrigin
	 */
	private String sourceOrigin;
	/**
	 * AdjudicationType
	 */
	private String adjudicationType;
	/**
	 * Amount
	 */
	private double amount;
	/**
	 * Status
	 */
	private String status;
	/**
	 * CreationDate
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime  creationDate;
	/**
	 * CloseDate
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime  closeDate;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @name Adjudication
	 * {@summary Default constructor }
	 * @param adjudicationId
	 * @param procedureNumber
	 * @param contractType
	 * @param modality
	 * @param sourceOrigin
	 * @param adjudicationType
	 * @param amount
	 * @param creationDate
	 * @param active
	 */
	public Adjudication(int adjudicationId, String procedureNumber, String contractType, String modality, String sourceOrigin, String adjudicationType, double amount, String status, LocalDateTime  creationDate, LocalDateTime  closeDate, int active) {
		this.setAdjudicationId(adjudicationId);
		this.setProcedureNumber(procedureNumber);
		this.setContractType(contractType);
		this.setModality(modality);
		this.setSourceOrigin(sourceOrigin);
		this.setAdjudicationType(adjudicationType);
		this.setAmount(amount);
		this.setStatus(status);
		this.setCreationDate(creationDate);
		this.setCloseDate(closeDate);
		this.setActive(active);
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
	 * @return the procedureNumber
	 */
	public String getProcedureNumber() {
		return procedureNumber;
	}
	/**
	 * @param procedureNumber the procedureNumber to set
	 */
	public void setProcedureNumber(String procedureNumber) {
		this.procedureNumber = procedureNumber;
	}
	/**
	 * @return the contractType
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * @param contractType the contractType to set
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * @return the modality
	 */
	public String getModality() {
		return modality;
	}
	/**
	 * @param modality the modality to set
	 */
	public void setModality(String modality) {
		this.modality = modality;
	}
	/**
	 * @return the sourceOrigin
	 */
	public String getSourceOrigin() {
		return sourceOrigin;
	}
	/**
	 * @param sourceOrigin the sourceOrigin to set
	 */
	public void setSourceOrigin(String sourceOrigin) {
		this.sourceOrigin = sourceOrigin;
	}
	/**
	 * @return the adjudicationType
	 */
	public String getAdjudicationType() {
		return adjudicationType;
	}
	/**
	 * @param adjudicationType the adjudicationType to set
	 */
	public void setAdjudicationType(String adjudicationType) {
		this.adjudicationType = adjudicationType;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the creationDate
	 */
	public LocalDateTime  getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDateTime  creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * @return the closeDate
	 */
	public LocalDateTime  getCloseDate() {
		return closeDate;
	}
	/**
	 * @param closeDate the closeDate to set
	 */
	public void setCloseDate(LocalDateTime  closeDate) {
		this.closeDate = closeDate;
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
