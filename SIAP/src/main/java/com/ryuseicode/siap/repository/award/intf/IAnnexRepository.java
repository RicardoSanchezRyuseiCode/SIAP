package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;

/**
 * @name IAnnexRepository
 * {@summary Interface to define the behavior of IAnnexRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public interface IAnnexRepository {
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of annex param output }
	 * @param adjudicationId
	 * @return
	 */
	List<AnnexParamOutput> GetByAdjudicationId(int adjudicationId);
	/**
	 * @name Save
	 * {@summary Method to save a list of annex }
	 * @param annexs
	 * @return
	 */
	int Save(List<Annex> annexs);
}
