package com.ryuseicode.siap.service.award.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.EmissionRepository;
import com.ryuseicode.siap.service.award.intf.IEmissionService;

/**
 * @name EmissionService
 * {@summary Service to implement the behavior of IEmissionService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
@Service
public class EmissionService implements IEmissionService {
	/**
	 * EmissionRepository
	 */
	@Autowired
	private EmissionRepository emissionRepository;
	/**
	 * @name GetByAdjudicationId
	 * {@sumamry Method to get an Emission by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	public Emission getByAdjudicationId(int adjudicationId) { 
		return this.emissionRepository.GetByAdjudicationId(adjudicationId);
	}
	/**
	 * @name Save
	 * {@summary Method to save an emission }
	 * @param emission
	 */
	public void save(Emission emission) throws Exception {
		// check if adjudication already have an emission
		if(this.emissionRepository.GetByAdjudicationId(emission.getAdjudicationId()) != null) 
			throw new ServiceException("La adjudication ya cuenta con datos de emisión");
		// Set default data
		emission.setActive(1);
		// Save
		this.emissionRepository.Save(emission);
	}
}