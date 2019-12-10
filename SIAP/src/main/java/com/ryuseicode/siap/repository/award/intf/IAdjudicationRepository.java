package com.ryuseicode.siap.repository.award.intf;

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
}
