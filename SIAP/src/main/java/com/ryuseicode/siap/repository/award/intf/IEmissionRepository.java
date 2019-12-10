package com.ryuseicode.siap.repository.award.intf;

import com.ryuseicode.siap.entity.award.Emission;

/**
 * @name IEmissionRepository
 * {@summary Interface to define the behavior of Emission Repository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
public interface IEmissionRepository {
	/**
	 * @name Emission
	 * {@summary Method to get a list of emission objects }
	 * @return
	 */
	Emission GetByAdjudicationId(int adjudicationId);
	/**
	 * Save
	 * {@summary Method to save an emission object }
	 * @param emission
	 */
	int Save(Emission emission);
}
