package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Competitor;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;

/**
 * @name ICompetitorRepository
 * {@summary Interface to define the behavior of CompetitorRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
public interface ICompetitorRepository {
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of competitor }
	 * @param adjudicationId
	 * @return
	 */
	List<CompetitorParamOutput> getByAdjudicationId(int adjudicationId);
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get by competitorId }
	 * @param competitorId
	 * @return
	 */
	CompetitorParamOutput getByCompetitorId(int competitorId);
	/**
	 * @name Save
	 * {@summary Method to save a competitor }
	 * @param competitor
	 */
	int save(List<Competitor> competitors);
}
