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
	 * MaxAmountText
	 */
	private String maxAmountText;
	/**
	 * MinAmount
	 */
	private double minAmount;
	/**
	 * MaxAmountText
	 */
	private String minAmountText;
	/**
	 * MaxAmountIva
	 */
	private double maxAmountIva;
	/**
	 * MaxAmountIvaText
	 */
	private String maxAmountIvaText;
	/**
	 * MinAmountIva
	 */
	private double minAmountIva;
	/**
	 * MinAmountIvaText
	 */
	private String minAmountIvaText;
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
	 * DepositText
	 */
	private String depositText;
	/**
	 * DepositPercent
	 */
	private double depositPercent;
	/**
	 * DepositPercentText
	 */
	private String depositPercentText;
	/**
	 * DepositInAdvance
	 */
	private double depositInAdvance;
	/**
	 * depositInAdvanceText
	 */
	private String depositInAdvanceText;
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
	 * Default constructor
	 * @param contractId
	 * @param competitorId
	 * @param adjudicationId
	 * @param taxId
	 * @param sourceResource
	 * @param numberMatch
	 * @param email
	 * @param maxAmount
	 * @param maxAmountText
	 * @param minAmount
	 * @param minAmountText
	 * @param maxAmountIva
	 * @param maxAmountIvaText
	 * @param minAmountIva
	 * @param minAmountIvaText
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
	 * @param depositText
	 * @param depositPercent
	 * @param depositPercentText
	 * @param depositInAdvance
	 * @param depositInAdvanceText
	 * @param dueDate
	 * @param dueDateText
	 * @param winnerName
	 * @param active
	 */
	public Contract(int contractId, int competitorId, int adjudicationId, String taxId, String sourceResource,
			String numberMatch, String email, double maxAmount, String maxAmountText, double minAmount,
			String minAmountText, double maxAmountIva, String maxAmountIvaText, double minAmountIva,
			String minAmountIvaText, int physicalPerson, String physicalPersonData, String legalDeedNumber,
			String legalNotaryNumber, String legalState, String legalCity, LocalDate legalDate, String legalDateText,
			String legalAgentDeedNumber, String legalAgentNotaryNumber, String legalAgentState, String legalAgentCity,
			LocalDate legalAgentDate, String legalAgentDateText, double deposit, String depositText,
			double depositPercent, String depositPercentText, double depositInAdvance, String depositInAdvanceText,
			LocalDate dueDate, String dueDateText, String winnerName) {
		super();
		this.contractId = contractId;
		this.competitorId = competitorId;
		this.adjudicationId = adjudicationId;
		this.taxId = taxId;
		this.sourceResource = sourceResource;
		this.numberMatch = numberMatch;
		this.email = email;
		this.maxAmount = maxAmount;
		this.maxAmountText = maxAmountText;
		this.minAmount = minAmount;
		this.minAmountText = minAmountText;
		this.maxAmountIva = maxAmountIva;
		this.maxAmountIvaText = maxAmountIvaText;
		this.minAmountIva = minAmountIva;
		this.minAmountIvaText = minAmountIvaText;
		this.physicalPerson = physicalPerson;
		this.physicalPersonData = physicalPersonData;
		this.legalDeedNumber = legalDeedNumber;
		this.legalNotaryNumber = legalNotaryNumber;
		this.legalState = legalState;
		this.legalCity = legalCity;
		this.legalDate = legalDate;
		this.legalDateText = legalDateText;
		this.legalAgentDeedNumber = legalAgentDeedNumber;
		this.legalAgentNotaryNumber = legalAgentNotaryNumber;
		this.legalAgentState = legalAgentState;
		this.legalAgentCity = legalAgentCity;
		this.legalAgentDate = legalAgentDate;
		this.legalAgentDateText = legalAgentDateText;
		this.deposit = deposit;
		this.depositText = depositText;
		this.depositPercent = depositPercent;
		this.depositPercentText = depositPercentText;
		this.depositInAdvance = depositInAdvance;
		this.depositInAdvanceText = depositInAdvanceText;
		this.dueDate = dueDate;
		this.dueDateText = dueDateText;
		this.winnerName = winnerName;
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
	 * @return the maxAmountText
	 */
	public String getMaxAmountText() {
		return maxAmountText;
	}
	/**
	 * @param maxAmountText the maxAmountText to set
	 */
	public void setMaxAmountText(String maxAmountText) {
		this.maxAmountText = maxAmountText;
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
	 * @return the minAmountText
	 */
	public String getMinAmountText() {
		return minAmountText;
	}
	/**
	 * @param minAmountText the minAmountText to set
	 */
	public void setMinAmountText(String minAmountText) {
		this.minAmountText = minAmountText;
	}
	/**
	 * @return the maxAmountIva
	 */
	public double getMaxAmountIva() {
		return maxAmountIva;
	}
	/**
	 * @param maxAmountIva the maxAmountIva to set
	 */
	public void setMaxAmountIva(double maxAmountIva) {
		this.maxAmountIva = maxAmountIva;
	}
	/**
	 * @return the maxAmountIvaText
	 */
	public String getMaxAmountIvaText() {
		return maxAmountIvaText;
	}
	/**
	 * @param maxAmountIvaText the maxAmountIvaText to set
	 */
	public void setMaxAmountIvaText(String maxAmountIvaText) {
		this.maxAmountIvaText = maxAmountIvaText;
	}
	/**
	 * @return the minAmountIva
	 */
	public double getMinAmountIva() {
		return minAmountIva;
	}
	/**
	 * @param minAmountIva the minAmountIva to set
	 */
	public void setMinAmountIva(double minAmountIva) {
		this.minAmountIva = minAmountIva;
	}
	/**
	 * @return the minAmountIvaText
	 */
	public String getMinAmountIvaText() {
		return minAmountIvaText;
	}
	/**
	 * @param minAmountIvaText the minAmountIvaText to set
	 */
	public void setMinAmountIvaText(String minAmountIvaText) {
		this.minAmountIvaText = minAmountIvaText;
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
	 * @return the depositText
	 */
	public String getDepositText() {
		return depositText;
	}
	/**
	 * @param depositText the depositText to set
	 */
	public void setDepositText(String depositText) {
		this.depositText = depositText;
	}
	/**
	 * @return the depositPercent
	 */
	public double getDepositPercent() {
		return depositPercent;
	}
	/**
	 * @param depositPercent the depositPercent to set
	 */
	public void setDepositPercent(double depositPercent) {
		this.depositPercent = depositPercent;
	}
	/**
	 * @return the depositPercentText
	 */
	public String getDepositPercentText() {
		return depositPercentText;
	}
	/**
	 * @param depositPercentText the depositPercentText to set
	 */
	public void setDepositPercentText(String depositPercentText) {
		this.depositPercentText = depositPercentText;
	}
	/**
	 * @return the depositInAdvance
	 */
	public double getDepositInAdvance() {
		return depositInAdvance;
	}
	/**
	 * @param depositInAdvance the depositInAdvance to set
	 */
	public void setDepositInAdvance(double depositInAdvance) {
		this.depositInAdvance = depositInAdvance;
	}
	/**
	 * @return the depositInAdvanceText
	 */
	public String getDepositInAdvanceText() {
		return depositInAdvanceText;
	}
	/**
	 * @param depositInAdvanceText the depositInAdvanceText to set
	 */
	public void setDepositInAdvanceText(String depositInAdvanceText) {
		this.depositInAdvanceText = depositInAdvanceText;
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
}
