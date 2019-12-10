package com.ryuseicode.siap.wrapper.award.intf;

import com.ryuseicode.siap.entity.award.Adjudication;

/**
 * @name IAdjudicationWrapper
 * {@summary Wrapper class to implement the behavior of IAdjudicationWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 2, 2019
 */
public interface IAdjudicationWrapper {
	/**
	 * @name Save
	 * {@abstract Method to save an adjudication }
	 * @param adjudication
	 */
	String ValidateAmount(Adjudication adjudication) throws Exception;
}
