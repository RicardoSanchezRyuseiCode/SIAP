package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Proposal;

/**
 * @name IProposalRepository
 * {@summary Interface to define the behavior of IProposalRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public interface IProposalRepository {
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
	int save(Proposal proposal);
	/**
	 * @name save
	 * {@summary Method to save a collection of proposal objects }
	 * @param proposals
	 * @return
	 */
	int save(List<Proposal> proposals);
	/**
	 * @name update
	 * {@summary Method to update a proposal }
	 * @param proposal
	 * @return
	 */
	int update(Proposal proposal);
	/**
	 * @name update
	 * {@summary Method to update a collection of proposals }
	 * @param proposals
	 * @return
	 */
	int update(List<Proposal> proposals);
}
