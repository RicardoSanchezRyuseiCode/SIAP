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
	 * @name updateCloseDate
	 * {@summary Method to update close date }
	 * @param adjudicationId
	 * @param closeDate
	 * @return
	 */
	void updateCloseDate(int adjudicationId, LocalDateTime closeDate) throws Exception;
}
