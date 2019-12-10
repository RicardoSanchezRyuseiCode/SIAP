package com.ryuseicode.siap.service.award.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Opening;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.OpeningRepository;
import com.ryuseicode.siap.service.award.intf.IOpeningService;
/**
 * @name OpeningService
 * {@summary Service class to implement the behavior of OpeningService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
@Service
public class OpeningService implements IOpeningService {
	/**
	 * Opening Repository
	 */
	@Autowired
	private OpeningRepository openingRepository;
	
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a Opening data by adjudicatinId }
	 * @param adjudicationId
	 * @return
	 */
	public Opening getByAdjudicationId(int adjudicationId) {
		return this.openingRepository.getByAdjudicationId(adjudicationId);
	}
	/**
	 * @name Save
	 * {@summary Method to save an opening object }
	 * @param opening
	 * @return
	 */
	public void save(Opening opening) throws Exception { 
		// Check if adjudication dont have already an opening defined
		if(this.openingRepository.getByAdjudicationId(opening.getAdjudicationId()) != null) 
			throw new ServiceException("La adjudicación ya cuenta con una apertura técnica-económica");
		// Set default data
		opening.setActive(1);
		// Save 
		this.openingRepository.save(opening);
	}
}