package com.ryuseicode.siap.entity.award;
/**
 * @name Proposal
 * {@summary Entity class to model proposal }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public class Proposal {
	/**
	 * PROPOSAL_SUBMITTED
	 */
	public static final String PROPOSAL_SUBMITTED = "Si Presentó";
	/**
	 * PROPOSAL_NOT_SUBMITTED
	 */
	public static final String PROPOSAL_NOT_SUBMITTED = "No Presentó";
	
	/**
	 * ProposalID
	 */
	private int proposalId;
	/**
	 * CompetitorId
	 */
	private int competitorId;
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * TechnicalSubmitted
	 */
	private String technicalSubmitted;
	/**
	 * TechnicalRemarks
	 */
	private String technicalRemark;
	/**
	 * EconomicSubmitted
	 */
	private String economicSubmitted;
	/**
	 * EconomicRemarks
	 */
	private String economicRemark;
	/**
	 * Total Amount
	 */
	private double totalAmount;
	/**
	 * Status
	 */
	private String status;
	/**
	 * Winner
	 */
	private int winner;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Defualt Constructor
	 * @param proposalId
	 * @param competitorId
	 * @param adjudicationId
	 * @param technicalSubmitted
	 * @param technicalRemark
	 * @param economicSubmitted
	 * @param economicRemark
	 * @param totalAmount
	 * @param status
	 * @param active
	 */
	public Proposal(int proposalId, int competitorId, int adjudicationId, String technicalSubmitted, String technicalRemark, String economicSubmitted, String economicRemark, double totalAmount, String status, int winner, int active) {
		this.setProposalId(proposalId);
		this.setCompetitorId(competitorId);
		this.setAdjudicationId(adjudicationId);
		this.setTechnicalSubmitted(technicalSubmitted);
		this.setTechnicalRemark(technicalRemark);
		this.setEconomicSubmitted(economicSubmitted);
		this.setEconomicRemark(economicRemark);
		this.setTotalAmount(totalAmount);
		this.setStatus(status);
		this.setWinner(winner);
		this.setActive(active);
	}
	/**
	 * @return the proposalId
	 */
	public int getProposalId() {
		return proposalId;
	}
	/**
	 * @param proposalId the proposalId to set
	 */
	public void setProposalId(int proposalId) {
		this.proposalId = proposalId;
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
	 * @return the technicalSubmitted
	 */
	public String getTechnicalSubmitted() {
		return technicalSubmitted;
	}
	/**
	 * @param technicalSubmitted the technicalSubmitted to set
	 */
	public void setTechnicalSubmitted(String technicalSubmitted) {
		this.technicalSubmitted = technicalSubmitted;
	}
	/**
	 * @return the technicalRemark
	 */
	public String getTechnicalRemark() {
		return technicalRemark;
	}
	/**
	 * @param technicalRemark the technicalRemark to set
	 */
	public void setTechnicalRemark(String technicalRemark) {
		this.technicalRemark = technicalRemark;
	}
	/**
	 * @return the economicSubmitted
	 */
	public String getEconomicSubmitted() {
		return economicSubmitted;
	}
	/**
	 * @param economicSubmitted the economicSubmitted to set
	 */
	public void setEconomicSubmitted(String economicSubmitted) {
		this.economicSubmitted = economicSubmitted;
	}
	/**
	 * @return the economicRemark
	 */
	public String getEconomicRemark() {
		return economicRemark;
	}
	/**
	 * @param economicRemark the economicRemark to set
	 */
	public void setEconomicRemark(String economicRemark) {
		this.economicRemark = economicRemark;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
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
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}
	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
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
