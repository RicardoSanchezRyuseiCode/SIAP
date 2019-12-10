package com.ryuseicode.siap.service.award.intf;

import com.ryuseicode.siap.entity.award.Emission;

/**
 * @name IEmissionService
 * {@summary Interface to define the behavior of IEmissionService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
public interface IEmissionService {
	/**
	 * @name GetByAdjudicationId
	 * {@sumamry Method to get an Emission by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	Emission getByAdjudicationId(int adjudicationId);
	/**
	 * @name Save
	 * {@summary Method to save an emission }
	 * @param emission
	 */
	void save(Emission emission) throws Exception;
}
