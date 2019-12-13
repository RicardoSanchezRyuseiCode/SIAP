package com.ryuseicode.siap.service.award.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Proposal;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.ProposalRepository;
import com.ryuseicode.siap.service.award.intf.IProposalService;

/**
 * @name ProposalService
 * {@summary Service class to implement the behavior of ProposalService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
@Service
public class ProposalService implements IProposalService {
	/**
	 * ProposalRepository
	 */
	@Autowired
	private ProposalRepository proposalRepository;
	/**
	 * @name getByCompetitorId
	 * {@summary Metho to get a Proposal by competitor id }
	 * @param competitorId
	 * @return
	 */
	public Proposal getByCompetitorId(int competitorId) {
		return this.proposalRepository.getByCompetitorId(competitorId);
	}
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get a list of Proposal by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	public List<Proposal> getByAdjudicationId(int adjudicationId) {
		return this.proposalRepository.getByAdjudicationId(adjudicationId);
	}
	/**
	 * @name save
	 * {@summary Method to save a proposal object to database}
	 * @param proposal
	 * @return
	 */
	public void save(Proposal proposal) throws Exception {
		// Check if there is a proposal linked to competitor
		if(this.proposalRepository.getByCompetitorId(proposal.getCompetitorId())   != null)
			throw new ServiceException("No se puede crear la propuesta ya que el competidor asociado ya tiene una propuesta asociada");
		// Set default data
		proposal.setStatus("Completa");
		proposal.setActive(1);
		// Save the proposal
		this.proposalRepository.save(proposal);
	}
	/**
	 * @name save
	 * {@summary Method to save a collection of proposal objects }
	 * @param proposals
	 * @return
	 */
	public void save(int adjudicationId, List<Proposal> proposals) throws Exception {
		// Get current proposals for adjudication
		List<Proposal> currentProposals = this.proposalRepository.getByAdjudicationId(adjudicationId);
		// Define map of current proposals
		Map<Integer, Proposal> dicCurrentProposal = new HashMap<Integer, Proposal>();
		// Loop in proposal
		for(Proposal currentProposal : currentProposals) {
			if(!dicCurrentProposal.containsKey(currentProposal.getCompetitorId()))
				dicCurrentProposal.put(currentProposal.getCompetitorId(), currentProposal);
		}
		// Define dictionary for distinct proposal
		Map<Integer, Proposal> dicNewProposal = new HashMap<Integer, Proposal>();
		for(Proposal proposal : proposals) {
			// Check if dictionary contains proposal
			if(!dicCurrentProposal.containsKey(proposal.getCompetitorId()))
				// Check if is distinct
				if(!dicNewProposal.containsKey(proposal.getCompetitorId())) {
					// Set default data
					proposal.setAdjudicationId(adjudicationId);
					// Add to dictionary
					dicNewProposal.put(proposal.getCompetitorId(), proposal);
				}
		}
		// Create array
		ArrayList<Proposal> newProposals = new ArrayList<Proposal>();
		dicNewProposal.forEach((competitorId, proposal) -> {
			proposal.setActive(1);
			newProposals.add(proposal);
		});
		// Save array
		this.proposalRepository.save(newProposals);
	}
	/**
	 * @name update
	 * {@summary Method to update proposal }
	 * @param proposal
	 */
	public void update(Proposal proposal) {
		this.proposalRepository.update(proposal);
	}
	/**
	 * @name update
	 * {@summary Method to save a list of proposals }
	 * @param proposals
	 */
	public void update(List<Proposal> proposals) {
		this.proposalRepository.update(proposals);
	}
}
