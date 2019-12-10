package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;

/**
 * @name IAnnexService
 * {@summary Interface to define the behavior of IAnnexServce }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public interface IAnnexService {
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
	void Save(int adjudicationId, List<Annex> annexs);
}
