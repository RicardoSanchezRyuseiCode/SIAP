package com.ryuseicode.siap.repository.award.intf;

import com.ryuseicode.siap.entity.award.Opening;

/**
 * @name IOpeningRepository
 * {@summary Interface to define the behavior of IOpeningRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
public interface IOpeningRepository {
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
	int save(Opening opening);
}
