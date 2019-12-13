package com.ryuseicode.siap.wrapper.award.intf;

import java.util.List;

import com.ryuseicode.siap.paraminput.award.ListProposalCreationParam;
import com.ryuseicode.siap.paramoutput.award.ItemParamOutput;
/**
 * @name IProposalWrapper
 * {@summary Interface to define the behavior of IProposalWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public interface IProposalWrapper {
	/**
	 * @name create
	 * {@summary Method to create a proposal with items }
	 * @param proposalCreationParams
	 * @return
	 */
	String create(ListProposalCreationParam listProposalCreationParams) throws Exception;
	/**
	 * @name importFile
	 * {@summary Method to import file }
	 * @param adjudicationId
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	List<ItemParamOutput> importFile(int adjudicationId, String filePath) throws Exception;
}
