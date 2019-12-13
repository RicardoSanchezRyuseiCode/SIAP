package com.ryuseicode.siap.paraminput.award;

import java.util.List;

/**
 * @name ListProposalCreationParam
 * {@summary Parameter class to create a list of proposal creation param }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public class ListProposalCreationParam {
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * ProposalCreationParams
	 */
	private List<ProposalCreationParam> proposalCreationParams;
	/**
	 * Default constructor
	 * @param adjudicationId
	 * @param proposalCreationParams
	 */
	public ListProposalCreationParam(int adjudicationId, List<ProposalCreationParam> proposalCreationParams) {
		this.setAdjudicationId(adjudicationId);
		this.setProposalCreationParams(proposalCreationParams);
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
	 * @return the proposalCreationParams
	 */
	public List<ProposalCreationParam> getProposalCreationParams() {
		return proposalCreationParams;
	}
	/**
	 * @param proposalCreationParams the proposalCreationParams to set
	 */
	public void setProposalCreationParams(List<ProposalCreationParam> proposalCreationParams) {
		this.proposalCreationParams = proposalCreationParams;
	}
	
	
	
}
