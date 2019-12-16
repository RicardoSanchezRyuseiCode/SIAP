package com.ryuseicode.siap.wrapper.award.intf;

import java.time.LocalDateTime;
import java.util.List;

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
	 * {@summary Method to save an adjudication }
	 * @param adjudication
	 */
	String validateAmount(Adjudication adjudication) throws Exception;
	/**
	 * @name closeAdjudication
	 * {@summary Method to close adjudication }
	 * @param adjudicationId
	 * @param closeDate
	 * @return
	 */
	List<String> closeAdjudication(int adjudicationId, LocalDateTime closeDate) throws Exception;
}
