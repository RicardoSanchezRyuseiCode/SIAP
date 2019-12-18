package com.ryuseicode.siap.service.award.intf;

import java.time.LocalDateTime;
import java.util.List;

import com.ryuseicode.siap.entity.award.Adjudication;

public interface IAdjudicationService {
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
	 * @name getByProcedureNumber
	 * @param procedureNumber
	 * @return
	 */
	Adjudication getByProcedureNumber(String procedureNumber);	
	/**
	 * @name Save
	 * {@summary Method to save an adjudication }
	 * @param adjudication
	 */
	void save(Adjudication adjudication) throws Exception;
	/**
	 * @name updateStatus
	 * {@summary Method to update status}
	 * @param adjudicationId
	 * @param status
	 * @return
	 */
	void updateStatus(int adjudicationId, String status) throws Exception;
	/**
	 * @name updateCloseDate
	 * {@summary Method to update close date }
	 * @param adjudicationId
	 * @param closeDate
	 * @return
	 */
	void updateCloseDate(int adjudicationId, LocalDateTime closeDate) throws Exception;
	/**
	 * @name updateFinishDate
	 * {@summary Method to update finish date }
	 * @param adjudicationId
	 * @param finishDate
	 * @throws Exception
	 */
	void updateFinishDate(int adjudicationId, LocalDateTime finishDate) throws Exception;
	/**
	 * @name Delete
	 * {@summary Method to delete adjudication}
	 * @param adjudicationId
	 * @throws Exception
	 */
	void delete(int adjudicationId) throws Exception;
}
