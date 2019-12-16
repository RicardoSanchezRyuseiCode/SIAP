package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Contract;

/**
 * @name IContractRepository
 * {@summary Interface to define the behavior of IContractRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
public interface IContractRepository {
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get a list of contracts by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	List<Contract> getByAdjudicationId(int adjudicationId);
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get contract by competitorId }
	 * @param competitorId
	 * @return
	 */
	Contract getByCompetitorId(int competitorId);
	/**
	 * @name getByContractId
	 * {@summary Method to get a contract by contract id }
	 * @param contractId
	 * @return
	 */
	Contract getByContractId(int contractId);
	/**
	 * @name save
	 * {@summary Method to save a contract }
	 * @param contract
	 * @return
	 */
	int save(Contract contract);
}