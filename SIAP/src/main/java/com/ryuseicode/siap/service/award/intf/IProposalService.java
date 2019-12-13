package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Proposal;

/**
 * @name IProposalService
 * {@summary Interface to define the behavior of IProposalService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public interface IProposalService {
	/**
	 * @name getByCompetitorId
	 * {@summary Metho to get a Proposal by competitor id }
	 * @param competitorId
	 * @return
	 */
	Proposal getByCompetitorId(int competitorId);
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get a list of Proposal by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	List<Proposal> getByAdjudicationId(int adjudicationId);
	/**
	 * @name save
	 * {@summary Method to save a proposal object to database}
	 * @param proposal
	 * @return
	 */
	void save(Proposal proposal) throws Exception;
	/**
	 * @name save
	 * {@summary Method to save a collection of proposal objects }
	 * @param proposals
	 * @return
	 */
	void save(int adjudicationId, List<Proposal> proposals) throws Exception;
	/**
	 * @name update
	 * {@summary Method to update proposal }
	 * @param proposal
	 */
	void update(Proposal proposal);
	/**
	 * @name update
	 * {@summary Method to save a list of proposals }
	 * @param proposals
	 */
	void update(List<Proposal> proposals);
}