package com.ryuseicode.siap.entity.award;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name Contract
 * {@summary Entity class to model contract }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
public class Contract {
	/**
	 * ContractId
	 */
	private int contractId;
	/**
	 * CompetitorId
	 */
	private int competitorId;
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * TaxId
	 */
	private String taxId;
	/**
	 * SourceResource
	 */
	private String sourceResource;
	/**
	 * NumberMatch
	 */
	private String numberMatch;
	/**
	 * Email
	 */
	private String email;
	/**
	 * MaxAmount
	 */
	private double maxAmount;
	/**
	 * MinAmount
	 */
	private double minAmount;
	/**
	 * PhysicalPerson
	 */
	private int physicalPerson;
	/**
	 * PhysicalPersonData
	 */
	private String physicalPersonData;
	/**
	 * LegalDeedNumber
	 */
	private String legalDeedNumber;
	/**
	 * LegalNotaryNumber
	 */
	private String legalNotaryNumber;
	/**
	 * LegalState
	 */
	private String legalState;
	/**
	 * LegalCity
	 */
	private String legalCity;
	/**
	 * LegalDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate legalDate;
	/**
	 * LegalDateText
	 */
	private String legalDateText;
	/**
	 * LegalAgentDeedNumber
	 */
	private String legalAgentDeedNumber;
	/**
	 * LegalAgentNotaryNumber
	 */
	private String legalAgentNotaryNumber;
	/**
	 * LegalAgentState
	 */
	private String legalAgentState;
	/**
	 * LegalAgentCity
	 */
	private String legalAgentCity;
	/**
	 * LegalAgentDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate legalAgentDate;
	/**
	 * LegalAgentDateText
	 */
	private String legalAgentDateText;
	/**
	 * Deposit
	 */
	private double deposit;
	/**
	 * DueDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dueDate;
	/**
	 * DueDateText
	 */
	private String dueDateText;
	/**
	 * WinnerName
	 */
	private String winnerName;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default Constructor
	 * @param contractId
	 * @param competitorId
	 * @param adjudicationId
	 * @param taxId
	 * @param sourceResource
	 * @param numberMatch
	 * @param email
	 * @param maxAmount
	 * @param minAmount
	 * @param physicalPerson
	 * @param physicalPersonData
	 * @param legalDeedNumber
	 * @param legalNotaryNumber
	 * @param legalState
	 * @param legalCity
	 * @param legalDate
	 * @param legalDateText
	 * @param legalAgentDeedNumber
	 * @param legalAgentNotaryNumber
	 * @param legalAgentState
	 * @param legalAgentCity
	 * @param legalAgentDate
	 * @param legalAgentDateText
	 * @param deposit
	 * @param dueDate
	 * @param dueDateText
	 * @param winnerName
	 * @param active
	 */
	public Contract(int contractId, int competitorId, int adjudicationId, String taxId, String sourceResource,
			String numberMatch, String email, double maxAmount, double minAmount, int physicalPerson,
			String physicalPersonData, String legalDeedNumber, String legalNotaryNumber, String legalState,
			String legalCity, LocalDate legalDate, String legalDateText, String legalAgentDeedNumber,
			String legalAgentNotaryNumber, String legalAgentState, String legalAgentCity, LocalDate legalAgentDate,
			String legalAgentDateText, double deposit, LocalDate dueDate, String dueDateText, String winnerName,
			int active) {
		this.setContractId(contractId);
		this.setCompetitorId(competitorId);
		this.setAdjudicationId(adjudicationId);
		this.setTaxId(taxId);
		this.setSourceResource(sourceResource);
		this.setNumberMatch(numberMatch);
		this.setEmail(email);
		this.setMaxAmount(maxAmount);
		this.setMinAmount(minAmount);
		this.setPhysicalPerson(physicalPerson);
		this.setPhysicalPersonData(physicalPersonData);
		this.setLegalDeedNumber(legalDeedNumber);
		this.setLegalNotaryNumber(legalNotaryNumber);
		this.setLegalState(legalState);
		this.setLegalCity(legalCity);
		this.setLegalDate(legalDate);
		this.setLegalDateText(legalDateText);
		this.setLegalAgentDeedNumber(legalAgentDeedNumber);
		this.setLegalAgentNotaryNumber(legalAgentNotaryNumber);
		this.setLegalAgentState(legalAgentState);
		this.setLegalAgentCity(legalAgentCity);
		this.setLegalAgentDate(legalAgentDate);
		this.setLegalAgentDateText(legalAgentDateText);
		this.setDeposit(deposit);
		this.setDueDate(dueDate);
		this.setDueDateText(dueDateText);
		this.setWinnerName(winnerName);
		this.setActive(active);
	}

	/**
	 * @return the contractId
	 */
	public int getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(int contractId) {
		this.contractId = contractId;
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
	 * @return the taxId
	 */
	public String getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return the sourceResource
	 */
	public String getSourceResource() {
		return sourceResource;
	}

	/**
	 * @param sourceResource the sourceResource to set
	 */
	public void setSourceResource(String sourceResource) {
		this.sourceResource = sourceResource;
	}

	/**
	 * @return the numberMatch
	 */
	public String getNumberMatch() {
		return numberMatch;
	}

	/**
	 * @param numberMatch the numberMatch to set
	 */
	public void setNumberMatch(String numberMatch) {
		this.numberMatch = numberMatch;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the maxAmount
	 */
	public double getMaxAmount() {
		return maxAmount;
	}

	/**
	 * @param maxAmount the maxAmount to set
	 */
	public void setMaxAmount(double maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * @return the minAmount
	 */
	public double getMinAmount() {
		return minAmount;
	}

	/**
	 * @param minAmount the minAmount to set
	 */
	public void setMinAmount(double minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * @return the physicalPerson
	 */
	public int getPhysicalPerson() {
		return physicalPerson;
	}

	/**
	 * @param physicalPerson the physicalPerson to set
	 */
	public void setPhysicalPerson(int physicalPerson) {
		this.physicalPerson = physicalPerson;
	}

	/**
	 * @return the physicalPersonData
	 */
	public String getPhysicalPersonData() {
		return physicalPersonData;
	}

	/**
	 * @param physicalPersonData the physicalPersonData to set
	 */
	public void setPhysicalPersonData(String physicalPersonData) {
		this.physicalPersonData = physicalPersonData;
	}

	/**
	 * @return the legalDeedNumber
	 */
	public String getLegalDeedNumber() {
		return legalDeedNumber;
	}

	/**
	 * @param legalDeedNumber the legalDeedNumber to set
	 */
	public void setLegalDeedNumber(String legalDeedNumber) {
		this.legalDeedNumber = legalDeedNumber;
	}

	/**
	 * @return the legalNotaryNumber
	 */
	public String getLegalNotaryNumber() {
		return legalNotaryNumber;
	}

	/**
	 * @param legalNotaryNumber the legalNotaryNumber to set
	 */
	public void setLegalNotaryNumber(String legalNotaryNumber) {
		this.legalNotaryNumber = legalNotaryNumber;
	}

	/**
	 * @return the legalState
	 */
	public String getLegalState() {
		return legalState;
	}

	/**
	 * @param legalState the legalState to set
	 */
	public void setLegalState(String legalState) {
		this.legalState = legalState;
	}

	/**
	 * @return the legalCity
	 */
	public String getLegalCity() {
		return legalCity;
	}

	/**
	 * @param legalCity the legalCity to set
	 */
	public void setLegalCity(String legalCity) {
		this.legalCity = legalCity;
	}

	/**
	 * @return the legalDate
	 */
	public LocalDate getLegalDate() {
		return legalDate;
	}

	/**
	 * @param legalDate the legalDate to set
	 */
	public void setLegalDate(LocalDate legalDate) {
		this.legalDate = legalDate;
	}

	/**
	 * @return the legalDateText
	 */
	public String getLegalDateText() {
		return legalDateText;
	}

	/**
	 * @param legalDateText the legalDateText to set
	 */
	public void setLegalDateText(String legalDateText) {
		this.legalDateText = legalDateText;
	}

	/**
	 * @return the legalAgentDeedNumber
	 */
	public String getLegalAgentDeedNumber() {
		return legalAgentDeedNumber;
	}

	/**
	 * @param legalAgentDeedNumber the legalAgentDeedNumber to set
	 */
	public void setLegalAgentDeedNumber(String legalAgentDeedNumber) {
		this.legalAgentDeedNumber = legalAgentDeedNumber;
	}

	/**
	 * @return the legalAgentNotaryNumber
	 */
	public String getLegalAgentNotaryNumber() {
		return legalAgentNotaryNumber;
	}

	/**
	 * @param legalAgentNotaryNumber the legalAgentNotaryNumber to set
	 */
	public void setLegalAgentNotaryNumber(String legalAgentNotaryNumber) {
		this.legalAgentNotaryNumber = legalAgentNotaryNumber;
	}

	/**
	 * @return the legalAgentState
	 */
	public String getLegalAgentState() {
		return legalAgentState;
	}

	/**
	 * @param legalAgentState the legalAgentState to set
	 */
	public void setLegalAgentState(String legalAgentState) {
		this.legalAgentState = legalAgentState;
	}

	/**
	 * @return the legalAgentCity
	 */
	public String getLegalAgentCity() {
		return legalAgentCity;
	}

	/**
	 * @param legalAgentCity the legalAgentCity to set
	 */
	public void setLegalAgentCity(String legalAgentCity) {
		this.legalAgentCity = legalAgentCity;
	}

	/**
	 * @return the legalAgentDate
	 */
	public LocalDate getLegalAgentDate() {
		return legalAgentDate;
	}

	/**
	 * @param legalAgentDate the legalAgentDate to set
	 */
	public void setLegalAgentDate(LocalDate legalAgentDate) {
		this.legalAgentDate = legalAgentDate;
	}

	/**
	 * @return the legalAgentDateText
	 */
	public String getLegalAgentDateText() {
		return legalAgentDateText;
	}

	/**
	 * @param legalAgentDateText the legalAgentDateText to set
	 */
	public void setLegalAgentDateText(String legalAgentDateText) {
		this.legalAgentDateText = legalAgentDateText;
	}

	/**
	 * @return the deposit
	 */
	public double getDeposit() {
		return deposit;
	}

	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	/**
	 * @return the dueDate
	 */
	public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the dueDateText
	 */
	public String getDueDateText() {
		return dueDateText;
	}

	/**
	 * @param dueDateText the dueDateText to set
	 */
	public void setDueDateText(String dueDateText) {
		this.dueDateText = dueDateText;
	}

	/**
	 * @return the winnerName
	 */
	public String getWinnerName() {
		return winnerName;
	}

	/**
	 * @param winnerName the winnerName to set
	 */
	public void setWinnerName(String winnerName) {
		this.winnerName = winnerName;
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
