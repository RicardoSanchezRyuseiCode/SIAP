package com.ryuseicode.siap.wrapper.award.intf;

import com.ryuseicode.siap.entity.award.Contract;

/**
 * @name ContractWrapper
 * {@summary Interface to define the behavior of Contract Wrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
public interface IContractWrapper {
	/**
	 * @name create
	 * {@summary Method to create contract }
	 * @param contract
	 * @return
	 */
	String create(Contract contract) throws Exception;
	
}
