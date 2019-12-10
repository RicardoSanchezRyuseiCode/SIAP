package com.ryuseicode.siap.service.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.AdjudicationDocument;
import com.ryuseicode.siap.repository.award.imp.AdjudicationDocumentRepository;
import com.ryuseicode.siap.service.award.intf.IAdjudicationDocumentService;

/**
 * @name AdjudicationDocumentService
 * {@summary Service class to implement the behavior of AdjudicationDocumentService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
@Service
public class AdjudicationDocumentService implements IAdjudicationDocumentService {
	/**
	 * adjudicationDocumentRepository
	 */
	@Autowired
	private AdjudicationDocumentRepository adjudicationDocumentRepository;
	
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of adjudication document }
	 * @param adjudicationId
	 * @return
	 */
	public List<AdjudicationDocument> GetByAdjudicationId(int adjudicationId) {
		return this.adjudicationDocumentRepository.GetByAdjudicationId(adjudicationId);
	}
	/**
	 * @name GetByName
	 * {@summary Method to get adjudication document by name }
	 * @param name
	 * @return
	 */
	public AdjudicationDocument GetByName(String name) {
		return this.adjudicationDocumentRepository.GetByName(name);
	}
	/**
	 * @name Save
	 * {@summary Method to save an adjudicationDocument }
	 * @param adjudicationDocument
	 * @return
	 */
	public void Save(AdjudicationDocument adjudicationDocument) {
		this.adjudicationDocumentRepository.Save(adjudicationDocument);
	}
	/**
	 * @name Save
	 * {@summary Method to save a list of adjudicationDocuments}
	 * @param adjudicationDocuments
	 * @return
	 */
	public void Save(List<AdjudicationDocument> adjudicationDocuments) {
		this.adjudicationDocumentRepository.Save(adjudicationDocuments);
	}
	
}
