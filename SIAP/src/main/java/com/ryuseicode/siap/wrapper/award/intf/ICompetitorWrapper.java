package com.ryuseicode.siap.wrapper.award.intf;

import java.util.List;

import com.ryuseicode.siap.paramoutput.award.CompetitorContractStatusParamOutput;

/**
 * @name ICompetitorWrapper
 * {@summary Interface to define the behavior of ICompetitorWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
public interface ICompetitorWrapper {
	/**
	 * @name getWinnersByAdjudicationId
	 * {@summary Method to get a list of CompetitorContractStatusParamOutput }
	 * @param adjudicationId
	 * @return
	 */
	List<CompetitorContractStatusParamOutput> getWinnersByAdjudicationId(int adjudicationId);
}
