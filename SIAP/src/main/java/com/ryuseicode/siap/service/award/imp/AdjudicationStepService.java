package com.ryuseicode.siap.service.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.AdjudicationStep;
import com.ryuseicode.siap.repository.award.imp.AdjudicationStepRepository;
import com.ryuseicode.siap.service.award.intf.IAdjudicationStepService;

/**
 * @name IAdjudicationStepService
 * {@summary Class to define te behavior of IAdjudicationService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
@Service
public class AdjudicationStepService implements IAdjudicationStepService {
	/**
	 * AjudicationStepRepository
	 */
	@Autowired
	private AdjudicationStepRepository adjudicationStepRepository;
	/**
	 * @name GetByAdjudicationId
	 * {@summary MEthod to get by adjudicationId}
	 * @param adjudicationId
	 * @return
	 */
	public List<AdjudicationStep> GetByAdjudicationId(int adjudicationId){
		return this.adjudicationStepRepository.GetByAdjudicationId(adjudicationId);
	}
	/**
	 * @name Save
	 * {@summary Method to save an adjudication step}
	 * @param adjudicationStep
	 */
	public void Save(AdjudicationStep adjudicationStep) {
		this.adjudicationStepRepository.Save(adjudicationStep);
	}
	
}
