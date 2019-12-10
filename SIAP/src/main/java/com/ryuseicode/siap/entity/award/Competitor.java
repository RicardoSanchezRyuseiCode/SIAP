package com.ryuseicode.siap.entity.award;
/**
 * @name Competitor
 * {@summary Entity class to model Competitor }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
public class Competitor {
	/**
	 * CompetitorId
	 */
	private int competitorId;
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * SupplierId
	 */
	private int supplierId;
	/**
	 * OfficeNumber
	 */
	private String tradeNumber;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default constructor
	 * @param competitorId
	 * @param adjudicationId
	 * @param supplierId
	 * @param officeNumber
	 */
	public Competitor(int competitorId, int adjudicationId, int supplierId, String tradeNumber, int active) {
		this.setCompetitorId(competitorId);
		this.setAdjudicationId(adjudicationId);
		this.setSupplierId(supplierId);
		this.setTradeNumber(tradeNumber);
		this.setActive(active);
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
	 * @return the supplierId
	 */
	public int getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * @return the officeNumber
	 */
	public String getTradeNumber() {
		return tradeNumber;
	}
	/**
	 * @param officeNumber the officeNumber to set
	 */
	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
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
