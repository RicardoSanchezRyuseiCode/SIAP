package com.ryuseicode.siap.paramoutput.award;

import com.ryuseicode.siap.entity.award.Adjudication;

/**
 * @name CompetitorContractStatusParamOutput
 * {@summary Parameter class for competitor }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
public class CompetitorContractStatusParamOutput extends CompetitorParamOutput {
	/**
	 * haveContract
	 */
	private boolean haveContract;
	/**
	 * Cost
	 */
	private String cost;
	/**
	 * Adjudication
	 */
	private Adjudication adjudication;
	/**
	 * Default constructor
	 * @param competitorParamOutput
	 * @param haveContract
	 */
	public CompetitorContractStatusParamOutput (CompetitorParamOutput competitorParamOutput, Adjudication adjudication, boolean haveContract, String cost) {
		super(competitorParamOutput.getCompetitor(), competitorParamOutput.getSupplier());
		this.setHaveContract(haveContract);
		this.setCost(cost);
		this.setAdjudication(adjudication);
	}

	/**
	 * @return the haveContract
	 */
	public boolean isHaveContract() {
		return haveContract;
	}

	/**
	 * @param haveContract the haveContract to set
	 */
	public void setHaveContract(boolean haveContract) {
		this.haveContract = haveContract;
	}

	/**
	 * @return the cost
	 */
	public String getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}

	/**
	 * @return the adjudication
	 */
	public Adjudication getAdjudication() {
		return adjudication;
	}

	/**
	 * @param adjudication the adjudication to set
	 */
	public void setAdjudication(Adjudication adjudication) {
		this.adjudication = adjudication;
	}
}
