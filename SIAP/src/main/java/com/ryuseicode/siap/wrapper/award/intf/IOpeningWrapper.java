package com.ryuseicode.siap.wrapper.award.intf;

import com.ryuseicode.siap.entity.award.Opening;
/**
 * @name IOpeningWrapper
 * {@summary Interface to define the behavior of IOpeningWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
public interface IOpeningWrapper {
	/**
	 * @name Save
	 * {@summary Method to save an opening object }
	 * @param opening
	 * @return
	 */
	void save(Opening opening) throws Exception;
}
