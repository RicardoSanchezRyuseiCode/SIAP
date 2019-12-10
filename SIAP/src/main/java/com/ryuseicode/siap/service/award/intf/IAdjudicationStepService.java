package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.AdjudicationStep;
/**
 * @name IAdjudicationStepService
 * {@summary Interface to define the behavior of IAdjudicationStepService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public interface IAdjudicationStepService {
	/**
	 * @name GetByAdjudicationId
	 * {@summary MEthod to get by adjudicationId}
	 * @param adjudicationId
	 * @return
	 */
	List<AdjudicationStep> GetByAdjudicationId(int adjudicationId);
	/**
	 * @name Save
	 * {@summary Method to save an adjudication step}
	 * @param adjudicationStep
	 */
	void Save(AdjudicationStep adjudicationStep);
}
