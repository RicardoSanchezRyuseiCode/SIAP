package com.ryuseicode.siap.wrapper.award.imp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.entity.award.AdjudicationDocument;
import com.ryuseicode.siap.entity.award.AdjudicationStep;
import com.ryuseicode.siap.entity.award.DocumentVariable;
import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.entity.award.Institution;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.properties.DocumentProperties;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.AdjudicationStepService;
import com.ryuseicode.siap.service.award.imp.CompetitorService;
import com.ryuseicode.siap.service.award.imp.DocumentVariableService;
import com.ryuseicode.siap.service.award.imp.EmissionService;
import com.ryuseicode.siap.service.award.imp.InstitutionService;
import com.ryuseicode.siap.wrapper.award.intf.IInvitationWrapper;
/**
 * @name InvitationWrapper
 * {@summary }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 6, 2019
 */
@Service
@Transactional
public class InvitationWrapper extends WordWrapper implements IInvitationWrapper {
	/**
	 * Identifier
	 */
	private static final String IDENTIFIER = "INVITATION";
	/**
	 * adjudicationService
	 */
	@Autowired
	private AdjudicationService adjudicationService;
	/**
	 * emissionService
	 */
	@Autowired
	private EmissionService emissionService;
	/**
	 * competitorService
	 */
	@Autowired
	private CompetitorService competitorService;
	/**
	 * institutionService
	 */
	@Autowired
	private InstitutionService institutionService;
	/**
	 * DocumentVariableService
	 */
	@Autowired
	private DocumentVariableService documentVariableService;	
	/**
	 * AdjudicationDocumentService
	 */
	@Autowired
	private AdjudicationDocumentService adjudicationDocumentService;
	/**
	 * AdjudicationStepService
	 */
	@Autowired
	private AdjudicationStepService adjudicationStepService;
	/**
	 * folderProperties
	 */
	@Autowired
	private FolderProperties folderProperties;
	/**
	 * documentProperties
	 */
	@Autowired
	private DocumentProperties documentProperties;
	/**
	 * Default constructor
	 */
	public InvitationWrapper()  { super(); }
	/**
	 * @name CreateDocument
	 * {@summary Method to create document }
	 * @return
	 */
	private String CreateDocument(int supplierId) throws Exception {
		// Define timestamp
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// Define directory
		String directory = String.format("%s/%s", this.folderProperties.getDocuments(), new SimpleDateFormat("yyyyMM").format(new Date()));
		// Create directory
		if(Files.notExists(Paths.get(directory)))
			Files.createDirectory(Paths.get(directory));
		// Define new name for file
		String newName = String.format("%s_%s_%s", String.valueOf(supplierId), timestamp, this.documentProperties.getInvitation());
		// Define original document path
		String documentPath = String.format("%s/%s", this.folderProperties.getTemplate(), this.documentProperties.getInvitation());
		// Define new document path
		String newDocumentPath = String.format("%s/%s", directory, newName);
		// Copy new file
		Files.copy(new File(documentPath).toPath(), new File(newDocumentPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
		// return new document path
		return newDocumentPath;
	}
	/**
	 * @name create
	 * {@summary Method to create invitation document }
	 * @param adjudicationId
	 * @return
	 */
	public List<String> create(int adjudicationId) throws Exception {
		// Get the adjudication information
		Adjudication adjudication = this.adjudicationService.getById(adjudicationId);
		// Get the emission information
		Emission emission = this.emissionService.getByAdjudicationId(adjudicationId);
		// Get the competitors information
		List<CompetitorParamOutput> competitors = this.competitorService.getByAdjudicationId(adjudicationId);
		// Get institution elements
		Institution institution = this.institutionService.get().get(0);
		// Get document variable
		List<DocumentVariable> documentVariables = this.documentVariableService.Get();
		// Define array list of documents
		ArrayList<AdjudicationDocument> adjudicationDocuments = new ArrayList<AdjudicationDocument>();
		ArrayList<String> documentPaths = new ArrayList<String>();
		// Loop in competitor to create document
		for(CompetitorParamOutput competitorParamOutput : competitors) {
			// Create new document
			String newDocumentPath = CreateDocument(competitorParamOutput.getSupplier().getSupplierId());
			// Process document
			this.ProcessDocument(newDocumentPath, documentVariables, new Object[] {  adjudication, emission, competitorParamOutput.getCompetitor(), competitorParamOutput.getSupplier() , institution });
			// Add path to list
			String fileName = new File(newDocumentPath).getName();
			adjudicationDocuments.add(new AdjudicationDocument(0, adjudication.getAdjudicationId(), IDENTIFIER, fileName, newDocumentPath));
			documentPaths.add(fileName);
		}		
		// Save the adjudication document
		this.adjudicationDocumentService.Save(adjudicationDocuments);
		// Save step
		this.adjudicationStepService.Save(new AdjudicationStep(0, adjudication.getAdjudicationId(), IDENTIFIER, LocalDateTime.now()));
		// Update status
 		this.adjudicationService.updateStatus(adjudication.getAdjudicationId(), AdjudicationService.STATUS_ANNEX);
		// return path of document
		return documentPaths;
	}
}
