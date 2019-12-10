package com.ryuseicode.siap.service.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Competitor;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.repository.award.imp.CompetitorRepository;
import com.ryuseicode.siap.service.award.intf.ICompetitorService;

/**
 * @name CompetitorService
 * {@summary Service class to implement the behavior of ICompetitorService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
@Service
public class CompetitorService implements ICompetitorService {
	/**
	 * CompetitorRepository
	 */
	@Autowired
	private CompetitorRepository competitorRepository;
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of competitors }
	 * @param adjudicationId
	 * @return
	 */
	public List<CompetitorParamOutput> getByAdjudicationId(int adjudicationId) {
		return this.competitorRepository.getByAdjudicationId(adjudicationId);
	}
	/**
	 * @name Save
	 * {@summary Method to save a list of competitors }
	 * @param competitors
	 * @throws Exception
	 */
	public void save(int adjudicationId, List<Competitor> competitors) throws Exception { 
		// Get current competitors
		List<CompetitorParamOutput> currentCompetitors = this.competitorRepository.getByAdjudicationId(adjudicationId);
		// Loop to each
		boolean flag = false;
		for(Competitor newCompetitor : competitors) {
			for(CompetitorParamOutput currentCompetitor : currentCompetitors) {
				if(newCompetitor.getCompetitorId() == currentCompetitor.getCompetitor().getCompetitorId()) {
					flag = true;
					break;
				}	
			}
			// Check if was already assigned
			if(flag)
				throw new ServiceException("Algunos proveedores ya fueron agregados a la adjudicación");
			// if is not repeated set default data
			else {
				newCompetitor.setAdjudicationId(adjudicationId);
				newCompetitor.setActive(1);
			}
		}
		// Save the competitors
		this.competitorRepository.save(competitors);
	}
}
