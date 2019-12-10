package com.ryuseicode.siap.service.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.DocumentVariable;
import com.ryuseicode.siap.repository.award.imp.DocumentVariableRepository;
import com.ryuseicode.siap.service.award.intf.IDocumentVariableService;

/**
 * @name DocumentVariableService
 * {@summary Service class to define the behavior of DocumentVariableService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
@Service
public class DocumentVariableService implements IDocumentVariableService {
	/**
	 * DocumentVariableRepository
	 */
	@Autowired
	private DocumentVariableRepository documentVariableRepository;
	/**
	 * @name Get
	 * {@summary Method to get a list of document variable }
	 * @return
	 */
	public List<DocumentVariable> Get() {
		return this.documentVariableRepository.Get();
	}
}
