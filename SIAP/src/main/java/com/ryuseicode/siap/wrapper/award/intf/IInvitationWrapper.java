package com.ryuseicode.siap.wrapper.award.intf;

import java.util.List;

/**
 * @name IInvitationWrapper
 * {@summary Interface to define the behavior of IInvitationWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 6, 2019
 */
public interface IInvitationWrapper {
	/**
	 * @name create
	 * {@summary Method to create invitation document }
	 * @param adjudicationId
	 * @return
	 */
	List<String> create(int adjudicationId) throws Exception;
}
