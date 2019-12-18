package com.ryuseicode.siap.repository.award.intf;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuseicode.siap.entity.award.Adjudication;

/**
 * @name IAdjudicationRepository
 * {@summary Interface to define the behavior of IAdjudication }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public interface IAdjudicationRepository {
	/**
	 * @name Get
	 * {@summary Method to get a collection of adjudication }
	 * @return
	 */
	List<Adjudication> get();
	/**
	 * @name getPending
	 * {@summary Method to get Pending }
	 * @return
	 */
	List<Adjudication> getPending();
	/**
	 * @name getFinished
	 * {@summary Method to get finished }
	 * @return
	 */
	List<Adjudication> getFinished();
	/**
	 * @name GetById
	 * {@summary Method to get an adjudication by id }
	 * @param adjudicationId
	 * @return
	 */
	Adjudication getById(int adjudicationId);
	/**
	 * @name GetByProcedureNumber
	 * {@summary Method to get an adjudication by procedure number }
	 * @param procedureNumber
	 * @return
	 */
	Adjudication getByProcedureNumber(String procedureNumber);
	/**
	 * @name Save
	 * {@summary Method to save an adjudication }
	 * @param adjudication
	 */
	int save(Adjudication adjudication);
	/**
	 * @name updateStatus
	 * {@summary Method to update status}
	 * @param adjudicationId
	 * @param status
	 * @return
	 */
	int updateStatus(int adjudicationId, String status);
	/**
	 * @name updateCloseDate
	 * {@summary Method to update close date }
	 * @param adjudicationId
	 * @param closeDate
	 * @return
	 */
	int updateCloseDate(int adjudicationId, LocalDateTime closeDate);
	/**
	 * @name updateEndDate
	 * {@summary Method to update end date }
	 * @param adjudicationId
	 * @param endDate
	 * @return
	 */
	int updateFinishDate(int adjudicationId, LocalDateTime finishDate);	
	/**
	 * @name Delete
	 * {@summary Method to delete adjudication}
	 * @param adjudicationId
	 * @throws Exception
	 */
	int delete(int adjudicationId);
}
