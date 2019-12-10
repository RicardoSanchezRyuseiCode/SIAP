package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Institution;

/**
 * @name IInstitutionService
 * {@summary Interface to define the behavior of Institution }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public interface IInstitutionService {
	/**
	 * @name Get
	 * {@summary Method to get a list of institution objects }
	 * @return
	 */
	List<Institution> get();
}
