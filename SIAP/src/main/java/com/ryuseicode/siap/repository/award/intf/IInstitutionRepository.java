package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Institution;

/**
 * @name IInstitutionRepository
 * {@summary Interface to define the behavior of InstitutionRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public interface IInstitutionRepository {
	/**
	 * @name Get
	 * {@summary Method to get a list of Institution objects}
	 * @return
	 */
	List<Institution> get();
}
