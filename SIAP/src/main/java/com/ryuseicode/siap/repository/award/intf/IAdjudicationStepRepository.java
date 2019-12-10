package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.AdjudicationStep;

/**
 * @name IAdjudicationStepRepository
 * {@summary Interface to define the behavior of IAdjudicationStepRepository  }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public interface IAdjudicationStepRepository {
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
	int Save(AdjudicationStep adjudicationStep);
}
