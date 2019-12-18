package com.ryuseicode.siap.wrapper.award.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.entity.award.AdjudicationDocument;
import com.ryuseicode.siap.entity.award.AdjudicationStep;
import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.entity.award.Institution;
import com.ryuseicode.siap.entity.award.Item;
import com.ryuseicode.siap.entity.award.Opening;
import com.ryuseicode.siap.entity.award.Proposal;
import com.ryuseicode.siap.entity.award.Supplier;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.oxml.ExcelParser;
import com.ryuseicode.siap.paraminput.award.ListProposalCreationParam;
import com.ryuseicode.siap.paraminput.award.ProposalCreationParam;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.paramoutput.award.ItemParamOutput;
import com.ryuseicode.siap.properties.DocumentProperties;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.AdjudicationStepService;
import com.ryuseicode.siap.service.award.imp.AnnexService;
import com.ryuseicode.siap.service.award.imp.CompetitorService;
import com.ryuseicode.siap.service.award.imp.EmissionService;
import com.ryuseicode.siap.service.award.imp.InstitutionService;
import com.ryuseicode.siap.service.award.imp.ItemService;
import com.ryuseicode.siap.service.award.imp.OpeningService;
import com.ryuseicode.siap.service.award.imp.ProposalService;
import com.ryuseicode.siap.wrapper.award.intf.IProposalWrapper;

/**
 * @name IProposalWrapper
 * {@summary Wrapper class to implement the behavior of Proposal Wrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
@Service
@Transactional
public class ProposalWrapper implements IProposalWrapper {
	/**
	 * IDENTIFIER
	 */
	private static final String IDENTIFIER = "PROPOSAL";
	/**
	 * Bienes
	 */
	private static final String SHEET_NAME = "Bienes";
	/**
	 * Name Position
	 */
	private static final int ASSET_NAME_POSITION = 2;
	/**
	 * Asset Quantity Position
	 */
	private static final int ASSET_UNIT_AMOUNT_POSITION = 3;
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
	 * AnnexService
	 */
	@Autowired
	private AnnexService annexService;
	/**
	 * ProposalService
	 */
	@Autowired
	private ProposalService proposalService;
	/**
	 * ItemService
	 */
	@Autowired
	private ItemService itemService;
	/**
	 * OpeningService
	 */
	@Autowired
	private OpeningService openingService;
	/**
	 * InstitutionService
	 */
	@Autowired
	private InstitutionService institutionService;
	/**
	 * CompetitorService
	 */
	@Autowired
	private CompetitorService competitorService;
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
	 * @name WriteDocument
	 * {@summary Method to write document to disk }
	 * @param document
	 * @param adjudication
	 * @return
	 * @throws Exception 
	 */
	private String WriteDocument(XWPFDocument document, int adjudicationId) throws Exception {
		// Define timestamp
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// Define directory
		String directory = String.format("%s/%s", this.folderProperties.getDocuments(), new SimpleDateFormat("yyyyMM").format(new Date()));
		// Create directory
		if(Files.notExists(Paths.get(directory)))
			Files.createDirectory(Paths.get(directory));
		// Define new name for file
		String newName = String.format("%s_%s_%s", String.valueOf(adjudicationId), timestamp, this.documentProperties.getOpening());
		// Define new document path
		String newDocumentPath = String.format("%s/%s", directory, newName);
		// Create file output stream
		FileOutputStream fileOutputStream = new FileOutputStream(newDocumentPath);
		// Write document
		document.write(fileOutputStream);
		// Close document
		document.close();
		// Close output stream
		fileOutputStream.close();
		// return new document path
		return newDocumentPath;
	}
	/**
	 * @name CreateDocument
	 * {@summary Method to CreateDocument }
	 * @param adjudication
	 * @param institution
	 * @param opening
	 * @param dicCompetitor
	 * @param proposals
	 * @return
	 */
	private String CreateDocument(Adjudication adjudication, Institution institution, Emission emission, Opening opening, Map<Integer, CompetitorParamOutput> dicCompetitor, Map<Integer, Supplier> dicSupplier, List<Proposal> proposals) throws Exception {
		// Create document
		XWPFDocument openingDocument = new XWPFDocument();
		// Create first paragraph
		XWPFParagraph pOne = openingDocument.createParagraph(); 
		pOne.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rOne = pOne.createRun();
        rOne.setText(String.format("En el Municipio de %s siendo %s del %s estando presentes en el Organismo Público Descentralizado ubicado en %s, se reunieron los servidores públicos cuyos nombres y "
        						 + "firmas aparecen al final de ésta Acta, con objeto de llevar a cabo el acto de Presentación y Apertura de Proposiciones Técnicas y Económicas, del Procedimiento de "
        						 + "Adjudicación %s número %s, para la adquisición de %s, de conformidad con los artículos 34, 35, y 43 fracción IV de la Ley de Adquisiciones, Arrendamientos y Servicios "
        						 + "del Sector Público, en adelante, la Ley; 39 fracción VIII, 47 y 48 de su Reglamento.",
        						 emission.getPlace(), opening.getEventStartHour(), opening.getEventDateText(),  institution.getName(), institution.getAddress(), adjudication.getAdjudicationType(), 
        						 adjudication.getProcedureNumber(), opening.getDenomination()
					 ));
        
        // Create second paragraph
        XWPFParagraph pTwo = openingDocument.createParagraph(); 
		pTwo.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rTwo = pTwo.createRun();
        rTwo.setText(String.format("El acto es presidido por el (la) C. %s, servidor(a) público(a) designada por la convocante; además asisten los CC. %s, %s testigos.",
        						 opening.getReponsable(), opening.getFirstWitness(), opening.getSecondWitness()
					 ));
        // Create third paragraph
        XWPFParagraph pThree = openingDocument.createParagraph(); 
		pThree.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rThree = pThree.createRun();
        rThree.setText("Los sobres de las proposiciones presentadas en forma presencial en este acto, por los siguientes participantes, se recibieron conforme a lo establecido en la invitación.");
        // Create first table
        XWPFTable tOne = openingDocument.createTable();
        XWPFTableRow tOneBody = tOne.getRow(0);
        tOneBody.getCell(0).setText("No");
        tOneBody.addNewTableCell().setText("PERSONAS FÍSICAS O JURÍDICAS QUE PRESENTAN SUS PROPOSICIONES");
        int index = 1;
        for(Proposal proposal : proposals) {
    		XWPFTableRow tableRow = tOne.createRow();
    		tableRow.getCell(0).setText(String.valueOf(index));
    		tableRow.getCell(1).setText(dicCompetitor.get(proposal.getCompetitorId()).getSupplier().getName());
    		index++;
        }
        // Create fourth paragraph
        XWPFParagraph pFour = openingDocument.createParagraph(); 
		pFour.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rFour = pThree.createRun();
        rFour.setText("Se procedió a la apertura de las proposiciones recibidas en forma presencial en este acto, revisando la documentación presentada, sin entrar al análisis detallado de su contenido. De lo anterior se hace constar lo siguiente:");
        // Create second table
        XWPFTable tTwo = openingDocument.createTable();
        XWPFTableRow tTwoBody = tTwo.getRow(0);
        tTwoBody.getCell(0).setText("No");
        tTwoBody.addNewTableCell().setText("PERSONAS FÍSICAS O JURÍDICAS QUE PRESENTAN SUS PROPOSICIONES");
        tTwoBody.addNewTableCell().setText("PROPUESTA TÉCNICA ANEXO UNO DE INVITACIÓN");
        tTwoBody.addNewTableCell().setText("OBSERVACIONES");
        index = 1;
        for(Proposal proposal : proposals) {
    		XWPFTableRow tableRow = tTwo.createRow();
    		tableRow.getCell(0).setText(String.valueOf(index));
    		tableRow.getCell(1).setText(dicCompetitor.get(proposal.getCompetitorId()).getSupplier().getName());
    		tableRow.getCell(2).setText(proposal.getTechnicalSubmitted());
    		tableRow.getCell(3).setText(proposal.getTechnicalRemark() != null ? proposal.getTechnicalRemark() : " ");
    		index++;
        }
        // Create fourth paragraph
        XWPFParagraph pFourth = openingDocument.createParagraph(); 
        pFourth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rFourth = pFourth.createRun();
        rFourth.setText(" ");
        // Create third table
        XWPFTable tThird = openingDocument.createTable();
        XWPFTableRow tThirdBody = tThird.getRow(0);
        tThirdBody.getCell(0).setText("No");
        tThirdBody.addNewTableCell().setText("PERSONAS FÍSICAS O JURÍDICAS QUE PRESENTAN SUS PROPOSICIONES");
        tThirdBody.addNewTableCell().setText("PROPUESTA ECONÓMICA ANEXO UNO DE INVITACIÓN");
        tThirdBody.addNewTableCell().setText("OBSERVACIONES");
        index = 1;
        for(Proposal proposal : proposals) {
    		XWPFTableRow tableRow = tThird.createRow();
    		tableRow.getCell(0).setText(String.valueOf(index));
    		tableRow.getCell(1).setText(dicCompetitor.get(proposal.getCompetitorId()).getSupplier().getName());
    		tableRow.getCell(2).setText(proposal.getEconomicSubmitted());
    		tableRow.getCell(3).setText(proposal.getEconomicRemark() != null ? proposal.getEconomicRemark() : " ");
    		index++;
        }
        // Create fiveth paragraph
        XWPFParagraph pFiveth = openingDocument.createParagraph(); 
        pFiveth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rFiveth = pFiveth.createRun();
        rFiveth.setText("Después de registrar la recepción de la documentación presentada por los participantes, la presente Acta cumple con lo dispuesto en el artículo 47 párrafo sexto y 48 del Reglamento de la Ley de Adquisiciones, Arrendamientos y Servicios del Sector Público.");
        // Create sixth paragraph
        XWPFParagraph pSixth = openingDocument.createParagraph(); 
        pSixth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rSixth = pSixth.createRun();
        rSixth.setText(String.format("Acto seguido, y con fundamento en el artículo 47 penúltimo párrafo del Reglamento de la Ley de Adquisiciones, Arrendamientos y Servicios del Sector Público, "
        		                   + "el  (la) C. %s, servidor(a) público(a) designado(a) por la convocante, anexa copia de la propuesta económica de los licitantes al Acta respectiva, mismas que "
        		                   + "servirán de base en la elaboración del Cuadro Comparativo, procediendo a dar lectura al importe total de cada proposición en términos de precios unitarios sin "
        		                   + "incluir el Impuesto al Valor Agregado IVA, de la totalidad de los renglones ofertados y que forman parte de este procedimiento, cuyos montos totales se consignan a "
        		                   + "continuación: ", opening.getReponsable()));
        // Create fourth table
        XWPFTable tFourth = openingDocument.createTable();
        XWPFTableRow tFourthBody = tFourth.getRow(0);
        tFourthBody.getCell(0).setText("No");
        tFourthBody.addNewTableCell().setText("PERSONAS FÍSICAS O JURÍDICAS QUE PRESENTAN SUS PROPOSICIONES");
        tFourthBody.addNewTableCell().setText("IMPORTE TOTAL PRECIOS UNITARIOS");
        index = 1;
        for(Proposal proposal : proposals) {
        	// Check if proposal is valid
        	if( proposal.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) && proposal.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED)) {
				XWPFTableRow tableRow = tFourth.createRow();
				tableRow.getCell(0).setText(String.valueOf(index));
				tableRow.getCell(1).setText(dicCompetitor.get(proposal.getCompetitorId()).getSupplier().getName());
				tableRow.getCell(2).setText(String.format("%.2f", proposal.getTotalAmount()));
				index++;
        	}
        }
        // Create seventh paragraph
        XWPFParagraph pSeventh = openingDocument.createParagraph(); 
        pSeventh.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rSeventh = pSeventh.createRun();
        rSeventh.setText(String.format("Con fundamento en el artículo 35 fracción II de la Ley, las proposiciones fueron rubricadas por el (la) C. %s, y el servidor público designado por la Convocante.",
        								dicSupplier.get(opening.getSupplierId()).getName()));
        // Create eighth paragraph
        XWPFParagraph pEighth = openingDocument.createParagraph(); 
        pEighth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rEighth = pEighth.createRun();
        rEighth.setText(String.format("De conformidad con el artículo 35 fracción III de la Ley, las proposiciones se recibieron para su evaluación y con base en ella, se emitirá el Fallo correspondiente, "
        							+ "el cual será dado a conocer el día %s %s, , mismo que podrá ser diferido, siempre y cuando, el nuevo plazo no exceda de 20 (veinte) días naturales contados a partir "
        							+ "del plazo establecido originalmente para el fallo. ",
        							opening.getFailureIssuanceDateText(), opening.getFailureIssuanceHourText()));
        // Create nineth paragraph
        XWPFParagraph pNineth = openingDocument.createParagraph(); 
        pNineth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rNineth = pNineth.createRun();
        rNineth.setText(String.format("Para efectos de la notificación y en términos del artículo 37 Bis de la Ley de Adquisiciones, Arrendamientos y Servicios del Sector Público, a partir de esta fecha se "
        							+ "pone a disposición de los participantes que no hayan asistido a este acto, copia de esta Acta en %s domicilio de la unidad administrativa responsable de la adjudicación, "
        							+ "lugar  en donde se fijará copia de la carátula del Acta o un ejemplar o el aviso del lugar donde se encuentra disponible, por un término no menor de cinco días hábiles, "
        							+ "siendo de la exclusiva responsabilidad de los participantes, acudir a enterarse de su contenido y obtener copia de la misma. Este procedimiento sustituye a la notificación personal.",
        							institution.getAddress()));
        // Create tenth paragraph
        XWPFParagraph pTenth = openingDocument.createParagraph(); 
        pTenth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rTenth = pTenth.createRun();
        rTenth.setText("En este acto se preguntó a los participantes si deseaban manifestar alguna observación u objeción al mismo, a lo que respondieron no tener ninguna.");
        // Create eleventh paragraph
        XWPFParagraph pEleventh = openingDocument.createParagraph(); 
        pEleventh.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rEleventh = pEleventh.createRun();
        rEleventh.setText(String.format("Después de dar lectura a la presente Acta, se dio por terminado este acto, siendo las %s, del mismo día de su inicio.",
        							     opening.getEventEndHourText()));
        // Create twelveth paragraph
        XWPFParagraph pTwelveth = openingDocument.createParagraph(); 
        pTwelveth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rTwelveth = pTwelveth.createRun();
        rTwelveth.setText("POR LOS PARTICIPANTES");
        // Create fiveth table
        XWPFTable tFiveth = openingDocument.createTable();
        XWPFTableRow tFivethBody = tFiveth.getRow(0);
        tFivethBody.getCell(0).setText("No");
        tFivethBody.addNewTableCell().setText("PERSONAS FÍSICAS O JURÍDICAS QUE PRESENTAN SUS PROPOSICIONES");
        tFivethBody.addNewTableCell().setText("FIRMA");
        index = 1;
        for(Proposal proposal : proposals) {
    		XWPFTableRow tableRow = tFiveth.createRow();
    		tableRow.getCell(0).setText(String.valueOf(index));
    		tableRow.getCell(1).setText(dicCompetitor.get(proposal.getCompetitorId()).getSupplier().getName());
    		tableRow.getCell(2).setText(" ");
    		index++;
        }
        // Create thirteenth paragraph
        XWPFParagraph pThirteenth = openingDocument.createParagraph(); 
        pThirteenth.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rThirteenth = pThirteenth.createRun();
        rThirteenth.setText("POR EL ORGANISMO");
        // Create sixth table
        XWPFTable tSixth = openingDocument.createTable();
        XWPFTableRow tSixthBody = tSixth.getRow(0);
        tSixthBody.getCell(0).setText("Puesto");
        tSixthBody.addNewTableCell().setText("FIRMA");
		XWPFTableRow tableRow1 = tSixth.createRow();
		tableRow1.getCell(0).setText(institution.getRepresentative());
		tableRow1.getCell(1).setText(" ");
		XWPFTableRow tableRow2 = tSixth.createRow();
		tableRow2.getCell(0).setText(opening.getFirstWitness());
		tableRow2.getCell(1).setText(" ");
		XWPFTableRow tableRow3 = tSixth.createRow();
		tableRow3.getCell(0).setText(opening.getSecondWitness());
		tableRow3.getCell(1).setText(" ");
    	// Final paragraph
		XWPFParagraph pFinal = openingDocument.createParagraph(); 
        pFinal.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun rFinal = pFinal.createRun();
        rFinal.setText("  ");
		// Write document
        String pathDocument = WriteDocument(openingDocument, adjudication.getAdjudicationId());		
		// Save the adjudication document
 		this.adjudicationDocumentService.Save(new AdjudicationDocument(0, adjudication.getAdjudicationId(), IDENTIFIER, new File(pathDocument).getName(), pathDocument) );
 		// Save step
 		this.adjudicationStepService.Save(new AdjudicationStep(0, adjudication.getAdjudicationId(), IDENTIFIER, LocalDateTime.now()));
 		// Update status
 		this.adjudicationService.updateStatus(opening.getAdjudicationId(), AdjudicationService.STATUS_QUOTATION);
		// Return file name
		return new File(pathDocument).getName();
	}
	/**
	 * @name create
	 * {@summary Method to create a proposal with items }
	 * @param proposalCreationParams
	 * @return
	 * @throws Exception 
	 */
	public String create(ListProposalCreationParam listProposalCreationParams) throws Exception {
		// Get adjudication information
		Adjudication adjudication = this.adjudicationService.getById(listProposalCreationParams.getAdjudicationId());
		// Save proposals from list
		List<Proposal> proposalsToSave = listProposalCreationParams.getProposalCreationParams().stream().map(ProposalCreationParam::getProposal).collect(Collectors.toList());
		// Save the proposals
		this.proposalService.save(listProposalCreationParams.getAdjudicationId(), proposalsToSave);
		// Get the saved proposals to assign to lists
		List<Proposal> proposals = this.proposalService.getByAdjudicationId(listProposalCreationParams.getAdjudicationId());
		// Create dictionary with competitorid as key
		Map<Integer, Proposal> dicProposals = new HashMap<Integer, Proposal>();
		for(Proposal proposal : proposals) {
			if(!dicProposals.containsKey(proposal.getCompetitorId()))
				dicProposals.put(proposal.getCompetitorId(), proposal);
		}
		// Get annex to have the quantity available
		List<AnnexParamOutput> annexs = this.annexService.GetByAdjudicationId(listProposalCreationParams.getAdjudicationId());
		// Create dictionary of annex
		Map<Integer, AnnexParamOutput> dicAnnexs = new HashMap<Integer, AnnexParamOutput>();
		for(AnnexParamOutput annex : annexs) {
			if(!dicAnnexs.containsKey(annex.getAnnex().getAnnexId()))
				dicAnnexs.put(annex.getAnnex().getAnnexId(), annex);
		}	
		// Loop in list of proposal creation params to assign the proposal id to items, and calculates sub total and total 
		for(ProposalCreationParam proposalCreationParam : listProposalCreationParams.getProposalCreationParams()) {
			// Define proposal total amount
			double proposalTotalAmount = 0;
			// Get proposal
			Proposal proposal = dicProposals.get(proposalCreationParam.getProposal().getCompetitorId());
			// Assign id
			proposalCreationParam.getProposal().setProposalId(proposal.getProposalId());
			// Loop n items
			for(Item item : proposalCreationParam.getItems())
			{
				// Set proposal id
				item.setProposalId(proposal.getProposalId());
				// Set total
				item.setTotalAmount(item.getUnitPrice() * dicAnnexs.get(item.getAnnexId()).getAnnex().getQuantity());
				// Sum total
				proposalTotalAmount += item.getTotalAmount();
			}
			// Set proposal total amount
			proposal.setTotalAmount(proposalTotalAmount);
			// Set winner 0
			proposal.setWinner(0);
		}
		// Check adjudication modality
		if(adjudication.getModality().equals(Adjudication.ADJUDICATION_MODALITY_BATCH)) {
			// Define proposal winner
			Proposal winnerProposal = null;
			// Loop to define the proposal winner
			for(ProposalCreationParam proposalCreationParam : listProposalCreationParams.getProposalCreationParams()) {
				// Get proposal
				Proposal proposal = dicProposals.get(proposalCreationParam.getProposal().getCompetitorId());
				// Check if is valid proposal
				if(proposal.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) && proposal.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED)) {
					if(winnerProposal == null) {
						winnerProposal = proposal;
					}
					else if(proposal.getTotalAmount() < winnerProposal.getTotalAmount())   {
						// Set winner proposal
						winnerProposal = proposal;
					}
				}
			}
			// Set winner proposal 
			winnerProposal.setWinner(1);
			// Loop to get the items of proposal and set as winners
			for(ProposalCreationParam proposalCreationParam : listProposalCreationParams.getProposalCreationParams()) {
				if(proposalCreationParam.getProposal().getProposalId() == winnerProposal.getProposalId()) {
					// Loop in items to set winner
					for(Item item : proposalCreationParam.getItems()) 
						item.setWinner(1);
					break;
				}
			}
		}
		// if adjudication modality is multiple, we need to select item winner and then the proposal
		else {
			// We need to select the item with minimal price in each asset 
			// Create dictionary of of assets to get split and select min of each one
			Map<Integer, ArrayList<Item>> dicItems = new HashMap<Integer, ArrayList<Item>>();
			// Loop in assets to build dictionary
			for(AnnexParamOutput annex : annexs) {
				// Check if annex exist in dicitonary
				if(!dicItems.containsKey(annex.getAnnex().getAnnexId())) 
					dicItems.put(annex.getAnnex().getAnnexId(), new ArrayList<Item>());
			}
			// Create dictionary of proposals
			Map<Integer, Proposal> dicProposalId = new HashMap<Integer,Proposal>();
			for(Proposal proposal : proposals) {
				if(!dicProposalId.containsKey(proposal.getProposalId()))
					dicProposalId.put(proposal.getProposalId(), proposal);
			}
			// Loop in proposals to get items
			for(ProposalCreationParam proposalCreationParam : listProposalCreationParams.getProposalCreationParams()) {
				// Check if proposal is valid
				if( proposalCreationParam.getProposal().getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalCreationParam.getProposal().getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED)) {
					// Loop in items to split in dictionary
					for(Item item : proposalCreationParam.getItems()) {
						// Add item to dictionary
						dicItems.get(item.getAnnexId()).add(item);
					}
				}
			}
			// Loop in dictionary items array to select the minimal in each annex
			for(AnnexParamOutput annex : annexs)
			{
				// Define winner item
				Item winnerItem = null;
				// Loop in dictionary items to select the minimal
				for(Item item : dicItems.get(annex.getAnnex().getAnnexId())) {
					// Check if winner item is null
					if(winnerItem == null) {
						winnerItem = item;
					}
					else if (item.getTotalAmount() < winnerItem.getTotalAmount()) {
						winnerItem = item;
					} 
				}
				// Set winner as item
				winnerItem.setWinner(1);
				// Set proposal winner
				dicProposalId.get(winnerItem.getProposalId()).setWinner(1);
			}
		}
		// update proposals with winners
		this.proposalService.update(proposals);
		// create array items
		ArrayList<Item> items = new ArrayList<Item>();
		// loop in proposals
		for(ProposalCreationParam proposalCreationParam : listProposalCreationParams.getProposalCreationParams()) {
			// loop in items
			for(Item item : proposalCreationParam.getItems())
				// add item
				items.add(item);
		}
		this.itemService.save(items);
		// Get institution data
		Institution institution = this.institutionService.get().get(0);
		// Get emission
		Emission emission = this.emissionService.getByAdjudicationId(adjudication.getAdjudicationId());
		// Get opening information for document
		Opening opening = this.openingService.getByAdjudicationId(adjudication.getAdjudicationId());
		// Get competitor information 
		List<CompetitorParamOutput> competitorOutputParams = this.competitorService.getByAdjudicationId(adjudication.getAdjudicationId());
		Map<Integer, CompetitorParamOutput> dicCompetitor = new HashMap<Integer, CompetitorParamOutput>();
		Map<Integer, Supplier> dicSupplier = new HashMap<Integer, Supplier>();
		for(CompetitorParamOutput competitorParamOutput : competitorOutputParams) {
			if(!dicCompetitor.containsKey(competitorParamOutput.getCompetitor().getCompetitorId()))
				dicCompetitor.put(competitorParamOutput.getCompetitor().getCompetitorId(), competitorParamOutput);
			if(!dicSupplier.containsKey(competitorParamOutput.getSupplier().getSupplierId()))
				dicSupplier.put(competitorParamOutput.getSupplier().getSupplierId(), competitorParamOutput.getSupplier());
		}
		// Create document
		String documentName = CreateDocument(adjudication, institution, emission, opening, dicCompetitor, dicSupplier, proposals);
		// Return name of file
		return documentName;
	}
	/**
	 * importFile
	 * {@summary Method to import file }
	 * @param adjudicationId
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public List<ItemParamOutput> importFile(int adjudicationId, String filePath) throws Exception {
		try {
			// Get content of file
			List<Object[]> excelContent = ExcelParser.GetMatrixContent(filePath, SHEET_NAME, true);
			// Get annex information
			List<AnnexParamOutput> annexs = this.annexService.GetByAdjudicationId(adjudicationId);
			// Create annex dictionary by asset name
			Map<String, AnnexParamOutput> dicAnnexs = new HashMap<String, AnnexParamOutput>();
			// Create dictionary of distinct elements for result
			Map<Integer, ItemParamOutput> dicItems = new HashMap<Integer, ItemParamOutput>();
			// Build dictionary of assets
			for(AnnexParamOutput annex : annexs) {
				if(!dicAnnexs.containsKey(annex.getAsset().getName()))
					dicAnnexs.put(annex.getAsset().getName().toLowerCase(), annex);
			}
			// Loop in excel file content
			for(Object[] content : excelContent) {
				// Check if row containd the requires data
				if(content[ASSET_UNIT_AMOUNT_POSITION] != null && !content[ASSET_UNIT_AMOUNT_POSITION].toString().isEmpty() &&  content[ASSET_NAME_POSITION] != null && !content[ASSET_NAME_POSITION].toString().isEmpty()) {
					// Check if the asset is present in the list of annex
					if(dicAnnexs.containsKey(content[ASSET_NAME_POSITION].toString().toLowerCase())) {
						// Get the annex
						AnnexParamOutput annex = dicAnnexs.get(content[ASSET_NAME_POSITION].toString().toLowerCase());
						// Check if dictionary contains the asset
						if(!dicItems.containsKey(annex.getAnnex().getAnnexId())) {
							// Add the item to 
							dicItems.put(annex.getAnnex().getAnnexId(),  new ItemParamOutput(new Item(0, 0, annex.getAnnex().getAnnexId(), Double.parseDouble(content[ASSET_UNIT_AMOUNT_POSITION].toString()), 0, 0, 1),annex.getAnnex()));
						}
					}
				}
			}
			// Get the values from dictionary
			ArrayList<ItemParamOutput> result = new ArrayList<ItemParamOutput>();
			dicItems.forEach((annexId, itemParamOutput) -> {
				result.add(itemParamOutput);
			});
			// return the result
			return result;
		}
		catch(Exception ex) {
			// Delete file
			Files.deleteIfExists(Paths.get(filePath));
			// Throw exception
			throw new ServiceException(String.format("Ha ocurrido un error en la importación de precios: %s", ex.getMessage()));
		}
	}
}