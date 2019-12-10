package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.DocumentVariable;

/**
 * @name IDocumentVariableService
 * {@summary Interface to define the behavior of IDocumentVariableService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public interface IDocumentVariableService {
	/**
	 * @name Get
	 * {@summary Method to get a list of document variable }
	 * @return
	 */
	List<DocumentVariable> Get();
}
