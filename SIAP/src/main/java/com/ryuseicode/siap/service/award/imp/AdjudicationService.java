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
	public static final String STATUS_INVITATION = "Invitación";
	public static final String STATUS_ANNEX = "Anexo";
	public static final String STATUS_OPENING = "Apertura Técnica-Económica";
	public static final String STATUS_DETAIL = "Detalle Apertura Técnica-Económica";
	public static final String STATUS_QUOTATION = "Cuadro Comparativo";
	public static final String STATUS_FAILURE = "Fallo";
	public static final String STATUS_CONTRACT = "Contrato";
	public static final String STATUS_FINISHED = "Completada";
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
	 * @name getPending
	 * {@summary Method to get Pending }
	 * @return
	 */
	public List<Adjudication> getPending() {
		return this.adjudicationRepository.getPending();
	}
	/**
	 * @name getFinished
	 * {@summary Method to get finished }
	 * @return
	 */
	public List<Adjudication> getFinished() {
		return this.adjudicationRepository.getFinished();
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
		adjudication.setStatus(AdjudicationService.STATUS_INVITATION);
		adjudication.setActive(1);
		// Save adjudication
		this.adjudicationRepository.save(adjudication);
	}
	/**
	 * @name updateStatus
	 * {@summary Method to update status}
	 * @param adjudicationId
	 * @param status
	 * @return
	 */
	public void updateStatus(int adjudicationId, String status) throws Exception {
		// Check if procedure number not exit
		if(this.adjudicationRepository.getById(adjudicationId) == null) {
			throw new ServiceException("No se encontro la adjudicación a actualizar");
		}
		// Close adjudication
		this.adjudicationRepository.updateStatus(adjudicationId, status);
	}
	/**
	 * @name updateCloseDate
	 * {@summary Method to update close date }
	 * @param adjudicationId
	 * @param closeDate
	 * @return
	 */
	public void updateCloseDate(int adjudicationId, LocalDateTime closeDate) throws Exception {
		// Check if procedure number not exit
		if(this.adjudicationRepository.getById(adjudicationId) == null) {
			throw new ServiceException("No se encontro la adjudicación a actualizar");
		}
		// Close adjudication
		this.adjudicationRepository.updateCloseDate(adjudicationId, closeDate);
	}
	/**
	 * @name updateFinishDate
	 * {@summary Method to update finish date }
	 * @param adjudicationId
	 * @param finishDate
	 * @throws Exception
	 */
	public void updateFinishDate(int adjudicationId, LocalDateTime finishDate) throws Exception {
		// Check if procedure number not exit
		if(this.adjudicationRepository.getById(adjudicationId) == null) {
			throw new ServiceException("No se encontro la adjudicación a actualizar");
		}
		// Close adjudication
		this.adjudicationRepository.updateFinishDate(adjudicationId, finishDate);
	}
	
	/**
	 * @name Delete
	 * {@summary Method to delete adjudication}
	 * @param adjudicationId
	 * @throws Exception
	 */
	public void delete(int adjudicationId) throws Exception {
		// Check if procedure number not exit
		if(this.adjudicationRepository.getById(adjudicationId) == null) {
			throw new ServiceException("No se encontro la adjudicación a eliminar");
		}
		// Close adjudication
		this.adjudicationRepository.delete(adjudicationId);
	}
}
