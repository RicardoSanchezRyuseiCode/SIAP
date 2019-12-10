package com.ryuseicode.siap.service.award.intf;

import com.ryuseicode.siap.entity.award.Opening;

/**
 * @name IOpeningService
 * {@summary Interface to define the behavior of IOpening Service }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
public interface IOpeningService {
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a Opening data by adjudicatinId }
	 * @param adjudicationId
	 * @return
	 */
	Opening getByAdjudicationId(int adjudicationId);
	/**
	 * @name Save
	 * {@summary Method to save an opening object }
	 * @param opening
	 * @return
	 */
	void save(Opening opening) throws Exception;
}
