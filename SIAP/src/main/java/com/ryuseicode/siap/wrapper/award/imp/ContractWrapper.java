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
import com.ryuseicode.siap.entity.award.Contract;
import com.ryuseicode.siap.entity.award.DocumentVariable;
import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.entity.award.Institution;
import com.ryuseicode.siap.entity.award.Opening;
import com.ryuseicode.siap.entity.award.Proposal;
import com.ryuseicode.siap.entity.award.Quotation;
import com.ryuseicode.siap.message.EmailMessage;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.properties.DistributionProperties;
import com.ryuseicode.siap.properties.DocumentProperties;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.AdjudicationStepService;
import com.ryuseicode.siap.service.award.imp.CompetitorService;
import com.ryuseicode.siap.service.award.imp.ContractService;
import com.ryuseicode.siap.service.award.imp.DocumentVariableService;
import com.ryuseicode.siap.service.award.imp.EmissionService;
import com.ryuseicode.siap.service.award.imp.InstitutionService;
import com.ryuseicode.siap.service.award.imp.OpeningService;
import com.ryuseicode.siap.service.award.imp.ProposalService;
import com.ryuseicode.siap.service.award.imp.QuotationService;
import com.ryuseicode.siap.wrapper.award.intf.IContractWrapper;
import com.ryuseicode.siap.wrapper.util.imp.NumberParserWrapper;

/**
 * @name ContractWrapper
 * {@summary Wrapper class for contract }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 16, 2019
 */
@Service
@Transactional
public class ContractWrapper extends WordWrapper implements IContractWrapper {
	/**
	 * QUOTATION
	 */
	private static final String IDENTIFIER = "CONTRACT";
	/**
	 * EMAIL_MESSAGE
	 */
	private static final String EMAIL_MESSAGE = "Estimado proveedor %s <br/> En este correo electronico encontrara anexo su contrato para la adjudicación número %s, para la instutición %s";
	/**
	 * ContractService
	 */
	@Autowired
	private ContractService contractService;
	/**
	 * AdjudicationService
	 */
	@Autowired
	private AdjudicationService adjudicationService;
	/**
	 * EmissionService
	 */
	@Autowired
	private EmissionService emissionService;
	/**
	 * Opening Service
	 */
	@Autowired
	private OpeningService openingService;
	/**
	 * CompetitorService
	 */
	@Autowired
	private CompetitorService competitorService;
	/**
	 * ProposalService
	 */
	@Autowired
	private ProposalService proposalService;
	/**
	 * QuotationService
	 */
	@Autowired
	private QuotationService quotationService;
	/**
	 * DocumentVariableService
	 */
	@Autowired
	private DocumentVariableService documentVariableService;
	/**
	 * FolderProperties
	 */
	@Autowired
	private FolderProperties folderProperties;
	/**
	 * DocumentProperties
	 */
	@Autowired
	private DocumentProperties documentProperties;
	/**
	 * AdjudicationDocumentService
	 */
	@Autowired
	private AdjudicationDocumentService adjudicationDocumentService;
	
	@Autowired
	private InstitutionService institutionService;
	/**
	 * AdjudicationStepService
	 */
	@Autowired
	private AdjudicationStepService adjudicationStepService;
	/**
	 * numberParserWrapper
	 */
	@Autowired 
	private NumberParserWrapper numberParserWrapper;
	/**
	 * DistributionProperties
	 */
	@Autowired
	private DistributionProperties distributionProperties;
	/**
	 * EmailMessage
	 */
	@Autowired
	private EmailMessage emailMessage;
	/**
	 * @name CreateDocument
	 * {@summary Method to create document }
	 * @return
	 */
	private String CreateDocument(int adjudicationId, int supplierId, String templateName) throws Exception {
		// Define timestamp
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// Define directory
		String directory = String.format("%s/%s", this.folderProperties.getDocuments(), new SimpleDateFormat("yyyyMM").format(new Date()));
		// Create directory
		if(Files.notExists(Paths.get(directory)))
			Files.createDirectory(Paths.get(directory));
		// Define new name for file
		String newName = String.format("%s_%s_%s_%s", String.valueOf(adjudicationId), String.valueOf(supplierId), timestamp, this.documentProperties.getContract());
		// Define original document path
		String documentPath = String.format("%s/%s", this.folderProperties.getTemplate(), templateName );
		// Define new document path
		String newDocumentPath = String.format("%s/%s", directory, newName);
		// Copy new file
		Files.copy(new File(documentPath).toPath(), new File(newDocumentPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
		// return new document path
		return newDocumentPath;
	}
	/**
	 * @name create
	 * {@summary Method to create contract }
	 * @param contract
	 * @return
	 */
	public String create(Contract contract) throws Exception {
		// Get text for max amount
		contract.setMaxAmountText(this.numberParserWrapper.doubleToCurrencyText(contract.getMaxAmount()));
		// Get text for min amount
		contract.setMinAmountText(this.numberParserWrapper.doubleToCurrencyText(contract.getMinAmount()));
		// Calculate max amount iva
		contract.setMaxAmountIva(contract.getMaxAmount() + (contract.getMaxAmount() * 0.16));
		// Get max amount iva text
		contract.setMaxAmountIvaText(this.numberParserWrapper.doubleToCurrencyText(contract.getMaxAmountIva()));
		// Calculate max amount iva
		contract.setMinAmountIva(contract.getMinAmount() + (contract.getMinAmount() * 0.16));
		// Get max amount iva text
		contract.setMinAmountIvaText(this.numberParserWrapper.doubleToCurrencyText(contract.getMinAmountIva()));
		// Get deposit text
		contract.setDepositText(this.numberParserWrapper.doubleToCurrencyText(contract.getDeposit()));
		// Get deposit percent text
		contract.setDepositPercentText(this.numberParserWrapper.doubleToCurrencyText(contract.getDepositPercent()));
		// Get deposit in advance text
		contract.setDepositInAdvanceText(this.numberParserWrapper.doubleToCurrencyText(contract.getDepositInAdvance()));
		// Save contract
		this.contractService.save(contract);
		// Get adjudication information
		Adjudication adjudication = this.adjudicationService.getById(contract.getAdjudicationId());
		// Get institution
		Institution institution = this.institutionService.get().get(0);
		// Get emission information
		Emission emission = this.emissionService.getByAdjudicationId(adjudication.getAdjudicationId());
		// Get opening information
		Opening opening = this.openingService.getByAdjudicationId(adjudication.getAdjudicationId());
		// Get competitor information
		CompetitorParamOutput competitorOutputParam = this.competitorService.getByCompetitorId(contract.getCompetitorId());
		// Get proposal
		Proposal proposal = this.proposalService.getByCompetitorId(competitorOutputParam.getCompetitor().getCompetitorId());
		// Get quotation
		Quotation quotation = this.quotationService.getByAdjudicationId(adjudication.getAdjudicationId());
		// Get document variables
		List<DocumentVariable> documentVariables = this.documentVariableService.Get();
		// Define template name
		String templateName = String.format("%s%s%s.docx", adjudication.getSourceOrigin(), adjudication.getContractType().replace("/", ""), adjudication.getAdjudicationType());
		// Create new document
		String newDocumentPath = CreateDocument(adjudication.getAdjudicationId(), competitorOutputParam.getSupplier().getSupplierId(), templateName);
		// Process document
		this.ProcessDocument(newDocumentPath, documentVariables, new Object[] { adjudication, institution, emission, opening, competitorOutputParam.getCompetitor(), competitorOutputParam.getSupplier(), proposal, contract, quotation });
		// Get file name
		String fileName = new File(newDocumentPath).getName();
		// Save document 
		this.adjudicationDocumentService.Save(new AdjudicationDocument(0, adjudication.getAdjudicationId(), IDENTIFIER, fileName, newDocumentPath));
		// Save step
 		this.adjudicationStepService.Save(new AdjudicationStep(0, adjudication.getAdjudicationId(), IDENTIFIER, LocalDateTime.now()));
 		// TODO send contract by email :) last sprint 
 		ArrayList<String> attachments = new ArrayList<String>();
 		attachments.add(newDocumentPath);
 		this.emailMessage.sendEmail(contract.getEmail(), this.distributionProperties.getCc(), "", attachments, String.format(EMAIL_MESSAGE, competitorOutputParam.getSupplier().getName(), adjudication.getProcedureNumber(), institution.getName()));
		// return 
		return fileName;
	}
	
}
