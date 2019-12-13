package com.ryuseicode.siap.paraminput.award;

import java.util.List;

import com.ryuseicode.siap.entity.award.Item;
import com.ryuseicode.siap.entity.award.Proposal;

/**
 * @name ProposalCreationParam
 * {@summary Input parameter to create a proposal }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public class ProposalCreationParam {
	/**
	 * Proposal
	 */
	private Proposal proposal;
	/**
	 * Items
	 */
	private List<Item> items;
	/**
	 * Default constructor
	 * @param proposal
	 * @param items
	 */
	public ProposalCreationParam(Proposal proposal, List<Item> items) {
		this.setProposal(proposal);
		this.setItems(items);
	}
	/**
	 * @return the proposal
	 */
	public Proposal getProposal() {
		return proposal;
	}
	/**
	 * @param proposal the proposal to set
	 */
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
