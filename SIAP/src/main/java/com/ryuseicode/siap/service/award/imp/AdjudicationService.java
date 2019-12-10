package com.ryuseicode.siap.service.award.imp;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.AdjudicationRepository;
import com.ryuseicode.siap.service.award.intf.IAdjudicationService;

/**
 * @name AdjudicationRepository
 * {@summary Service class to implement the behavior of IAdjudicationRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
@Service
public class AdjudicationService implements IAdjudicationService {
	/**
	 * Status In Process
	 */
	private static final String STATUS_IN_PROCESS = "En proceso";
	/**
	 * Status Complete
	 */
	//private static final String STATUS_COMPLETE = "Completado";
	
	/**
	 * AdjudicationRepository
	 */
	@Autowired
	private AdjudicationRepository adjudicationRepository;
	
	/**
	 * @name Get
	 * {@summary Method to get a collection of adjudication }
	 * @return
	 */
	public List<Adjudication> get() {
		return this.adjudicationRepository.get();
	}
	/**
	 * @name GetById
	 * {@summary Method to get an adjudication by id }
	 * @param adjudicationId
	 * @return
	 */
	public Adjudication getById(int adjudicationId) {
		return this.adjudicationRepository.getById(adjudicationId);
	}
	/**
	 * @name getByProcedureNumber
	 * {@summary Method to get an adjudication by procedure number }
	 * @param procedureNumber
	 * @return
	 */
	public Adjudication getByProcedureNumber(String procedureNumber) {
		return this.adjudicationRepository.getByProcedureNumber(procedureNumber);
	}	
	/**
	 * @name Save
	 * {@summary Method to save an adjudication }
	 * @param adjudication
	 */
	public void save(Adjudication adjudication) throws Exception {
		// Check if procedure number not exit
		if(this.adjudicationRepository.getByProcedureNumber(adjudication.getProcedureNumber()) != null) {
			throw new ServiceException("Ya existe una adjudicación con el mismo numero de procedimiento");
		}
		// Set default data
		adjudication.setCreationDate(LocalDateTime.now());
		adjudication.setStatus(AdjudicationService.STATUS_IN_PROCESS);
		adjudication.setActive(1);
		// Save adjudication
		this.adjudicationRepository.save(adjudication);
	}
}
