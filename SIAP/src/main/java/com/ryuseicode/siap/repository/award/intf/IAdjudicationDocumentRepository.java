package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.AdjudicationDocument;

/**
 * @name IAdjudicationDocumentRepository
 * {@summary Interface to define the behavior of IAdjudicationDocumentRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public interface IAdjudicationDocumentRepository {
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of adjudication document }
	 * @param adjudicationId
	 * @return
	 */
	List<AdjudicationDocument> GetByAdjudicationId(int adjudicationId);
	/**
	 * @name GetByName
	 * {@summary Method to get adjudication document by name }
	 * @param name
	 * @return
	 */
	AdjudicationDocument GetByName(String name);
	/**
	 * @name Save
	 * {@summary Method to save an adjudicationDocument }
	 * @param adjudicationDocument
	 * @return
	 */
	int Save(AdjudicationDocument adjudicationDocument);
	/**
	 * @name Save
	 * {@summary Method to save a list of adjudicationDocuments}
	 * @param adjudicationDocuments
	 * @return
	 */
	int Save(List<AdjudicationDocument> adjudicationDocuments);
}
