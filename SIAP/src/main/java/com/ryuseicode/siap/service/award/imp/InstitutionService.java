package com.ryuseicode.siap.service.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Institution;
import com.ryuseicode.siap.repository.award.imp.InstitutionRepository;
import com.ryuseicode.siap.service.award.intf.IInstitutionService;
/**
 * @name InstitutionService
 * {@summary Service class to implement the behavior of IInstitutionService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
@Service
public class InstitutionService implements IInstitutionService {
	@Autowired
	private InstitutionRepository institutionRepository;
	/**
	 * @name Get
	 * {@summary Method to get a list of institution objects }
	 * @return
	 */
	public List<Institution> get() { 
		return this.institutionRepository.get();
	}
}
