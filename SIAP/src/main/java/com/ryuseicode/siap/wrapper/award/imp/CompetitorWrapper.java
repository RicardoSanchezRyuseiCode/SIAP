package com.ryuseicode.siap.wrapper.award.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.entity.award.Contract;
import com.ryuseicode.siap.entity.award.Item;
import com.ryuseicode.siap.entity.award.Proposal;
import com.ryuseicode.siap.paramoutput.award.CompetitorContractStatusParamOutput;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.CompetitorService;
import com.ryuseicode.siap.service.award.imp.ContractService;
import com.ryuseicode.siap.service.award.imp.ItemService;
import com.ryuseicode.siap.service.award.imp.ProposalService;
import com.ryuseicode.siap.wrapper.award.intf.ICompetitorWrapper;

/**
 * @name CompetitorWrapper
 * {@summary Wrapper class to implement the behavior of CompetitorWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
@Service
@Transactional
public class CompetitorWrapper implements ICompetitorWrapper {
	/**
	 * CompetitorService
	 */
	@Autowired
	private CompetitorService competitorService;
	/**
	 * ProposalService
	 */
	@Autowired
	private ProposalService proposalService;
	/**
	 * Contract Service
	 */
	@Autowired
	private ContractService contractService;
	/**
	 * AdjudicationService
	 */
	@Autowired
	private AdjudicationService adjudicationService;
	/**
	 * ItemService
	 */
	@Autowired
	private ItemService itemService;
	/**
	 * @name getWinnersByAdjudicationId
	 * {@summary Method to get a list of CompetitorContractStatusParamOutput }
	 * @param adjudicationId
	 * @return
	 */
	public List<CompetitorContractStatusParamOutput> getWinnersByAdjudicationId(int adjudicationId) {
		Adjudication adjudication = this.adjudicationService.getById(adjudicationId);
		// Get competitors
		List<CompetitorParamOutput> competitorParamOutputs = this.competitorService.getByAdjudicationId(adjudicationId);
		// Get proposal
		List<Proposal> proposals = this.proposalService.getByAdjudicationId(adjudicationId);
		// Define dictionary
		Map<Integer, Proposal> dicProposalByCompetitorId = new HashMap<Integer, Proposal>();
		for(Proposal proposal : proposals) {
			if(!dicProposalByCompetitorId.containsKey(proposal.getCompetitorId()))
				dicProposalByCompetitorId.put(proposal.getCompetitorId(), proposal);
		}
		// Get contracts
		List<Contract> contracts = this.contractService.getByAdjudicationId(adjudicationId);
		Map<Integer, Contract> dicContractByCompetitorId = new HashMap<Integer, Contract>();
		for(Contract contract : contracts) {
			if(!dicContractByCompetitorId.containsKey(contract.getCompetitorId()))
				dicContractByCompetitorId.put(contract.getCompetitorId(), contract);
		}
		// Define result list
		ArrayList<CompetitorContractStatusParamOutput> result = new ArrayList<CompetitorContractStatusParamOutput>();
		for(CompetitorParamOutput competitorParamOutput : competitorParamOutputs) {
			// Get proposal
			Proposal proposal = dicProposalByCompetitorId.get(competitorParamOutput.getCompetitor().getCompetitorId());
			if(proposal.getWinner() == 1) {
				// Check if competitor have contract
				boolean haveContract = false;
				if(dicContractByCompetitorId.containsKey(competitorParamOutput.getCompetitor().getCompetitorId()))
					haveContract = true;
				// Calculate the cost of proposal
				List<Item> items = this.itemService.getByProposalId(proposal.getProposalId());
				double cost = 0;
				for(Item item : items) {
					if(item.getWinner() == 1) {
						cost += item.getTotalAmount();
					}
				}
				// Add to result list
				result.add(new CompetitorContractStatusParamOutput(competitorParamOutput, adjudication, haveContract, String.format("%.2f", cost)));
			}
		}
		return result;
	}
}
