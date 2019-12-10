package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.DocumentVariable;

/**
 * @name IDocumentVariableRepository
 * {@summary Interface to define the behavior of IDocumentVariable }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public interface IDocumentVariableRepository {
	/**
	 * @name Get
	 * {@summary Method to get a list of document variable }
	 * @return
	 */
	List<DocumentVariable> Get();
}
