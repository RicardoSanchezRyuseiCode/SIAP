package com.ryuseicode.siap.wrapper.award.intf;
/**
 * @name IEmissionWrapper
 * {@summary Interface to define the behavior of IEmissionWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 5, 2019
 */
public interface IEmissionWrapper {
	/**
	 * @name ValidateDays
	 * {@summary Method to validate days }
	 * @param sourceOrigin
	 * @param adjudicationId
	 * @return message of validation
	 */
	String ValidateDays(String sourceOrigin, int daysForPayment) throws Exception;
}
