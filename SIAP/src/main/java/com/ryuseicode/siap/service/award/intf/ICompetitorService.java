package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Competitor;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;

/**
 * @name ICompetitorRepository
 * {@summary Interface to define the behavior of ICompetitorRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
public interface ICompetitorService {
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of competitors }
	 * @param adjudicationId
	 * @return
	 */
	List<CompetitorParamOutput> getByAdjudicationId(int adjudicationId);
	/**
	 * @name Save
	 * {@summary Method to save a list of competitors }
	 * @param competitors
	 * @throws Exception
	 */
	void save(int adjudicationId, List<Competitor> competitors) throws Exception;	
}
