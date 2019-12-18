package com.ryuseicode.siap.service.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Contract;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.ContractRepository;
import com.ryuseicode.siap.service.award.intf.IContractService;
/**
 * @name 
 * @name ContractService
 * {@summary }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
@Service
public class ContractService implements IContractService {
	/**
	 * ContractRepository
	 */
	@Autowired
	private ContractRepository contractRepository;
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get a list of contracts by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	public List<Contract> getByAdjudicationId(int adjudicationId) {
		return this.contractRepository.getByAdjudicationId(adjudicationId);
	}
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get contract by competitorId }
	 * @param competitorId
	 * @return
	 */
	public Contract getByCompetitorId(int competitorId) {
		return this.contractRepository.getByCompetitorId(competitorId);
	}
	/**
	 * @name getByContractId
	 * {@summary Method to get a contract by contract id }
	 * @param contractId
	 * @return
	 */
	public Contract getByContractId(int contractId) {
		return this.contractRepository.getByContractId(contractId);
	}
	/**
	 * @name save
	 * {@summary Method to save a contract }
	 * @param contract
	 * @return
	 */
	public void save(Contract contract) throws Exception {
		// Check if competitor have already a contract
		if( this.getByCompetitorId(contract.getCompetitorId()) != null)
			throw new ServiceException("El competidor ya ha generado su contrato");
		// Save the contract
		this.contractRepository.save(contract);		
	}
}
