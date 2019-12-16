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

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.properties.DocumentProperties;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.AdjudicationStepService;
import com.ryuseicode.siap.service.award.imp.CompetitorService;
import com.ryuseicode.siap.service.award.imp.EmissionService;
import com.ryuseicode.siap.service.award.imp.InstitutionService;
import com.ryuseicode.siap.service.award.imp.ItemService;
import com.ryuseicode.siap.service.award.imp.OpeningService;
import com.ryuseicode.siap.service.award.imp.ProposalService;
import com.ryuseicode.siap.wrapper.award.intf.IAdjudicationWrapper;
/**
 * @name AdjudicationWrapper
 * {@summary Wrapper class to implement the behavior }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 2, 2019
 */
@Service
@Transactional
public class AdjudicationWrapper extends WordWrapper implements IAdjudicationWrapper {
	/**
	 * IDENTIFIER
	 */
	private static final String IDENTIFIER = "JUDGMENT";
	/**
	 * LIMIT_FEDERAL_DIRECT
	 */
	private static final double LIMIT_FEDERAL_DIRECT  = 179000;
	/**
	 * LIMIT_FEDERAL_GUEST
	 */
    private static final double LIMIT_FEDERAL_GUEST = 616000;
    /**
     * LIMIT_STATE_DIRECT
     */
    private static final double LIMIT_STATE_DIRECT = 29900;
    /**
     * LIMIT_STATE_GUEST
     */
    private static final double LIMIT_STATE_GUEST = 133300;
    /**
     * AdjudicationService
     */
    @Autowired
    private AdjudicationService adjudicationService;
    /**
     * InstitutionService
     */
    @Autowired 
    private InstitutionService institutionService;
    /**
     * EmissionService
     */
    @Autowired
    private EmissionService emissionService;
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
     * ItemService
     */
    @Autowired
    private ItemService itemService;
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
     * FolderProperties
     */
    @Autowired
    private FolderProperties folderProperties;
    
    @Autowired
    private DocumentProperties documentProperties;
    /**
     * OpeningService
     */
    @Autowired
    private OpeningService openingService;
	/**
	 * @name ValidateAmount
	 * {@abstract Method to save an adjudication }
	 * @param adjudication
	 */
	public String validateAmount(Adjudication adjudication) throws Exception {
		if(adjudication.getSourceOrigin().equals(Adjudication.SOURCE_FEDERAL) || adjudication.getSourceOrigin().equals(Adjudication.SOURCE_MIXED)) {
			if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_DIRECT)) {
				if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_FEDERAL_GUEST) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
				else if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_FEDERAL_DIRECT) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Invitación a cuando menos 3 personas.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PERSONS)) {
				if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_FEDERAL_DIRECT) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Adjudicación Directa.¿Desea continuar?";
				}
				else if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_FEDERAL_GUEST) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PUBLIC)) {
				if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_FEDERAL_DIRECT) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Adjudicación Directa.¿Desea continuar?";
				}
				else if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_FEDERAL_GUEST) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Invitación a cuando menos 3 personas.¿Desea continuar?";
				}
			}
		}
		else if(adjudication.getSourceOrigin().equals(Adjudication.SOURCE_STATE)) {
			if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_DIRECT)) {
				if( adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_GUEST) { 
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
				else if(adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_DIRECT) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Invitación a cuando menos 3 personas.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PERSONS)) {
				if( adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_GUEST) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Licitación Pública.¿Desea continuar?";
				}
				else if(adjudication.getAmount() <= AdjudicationWrapper.LIMIT_STATE_DIRECT) {
					return "En razón de la fuente del recurso y el monto señalado procede la adquisición a través de un procedimiento de Adjudicación Directa.¿Desea continuar?";
				}
			}
			else if(adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_PUBLIC)) {
				if (adjudication.getAmount() > AdjudicationWrapper.LIMIT_STATE_GUEST) { 
					throw new ServiceException("Verificar contenido del artículo 52 fracción I de la ley de adquisiciones, arrendamientos y servicios del sector público estatal y municipal.");
				}
				else if (adjudication.getAmount() <= AdjudicationWrapper.LIMIT_STATE_DIRECT) {
					throw new ServiceException("Verificar contenido del artículo 52 fracción I de la ley de adquisiciones, arrendamientos y servicios del sector público estatal y municipal.");
				}
				else if (adjudication.getAmount() <= AdjudicationWrapper.LIMIT_STATE_GUEST) {
					throw new ServiceException("Verificar contenido del artículo 52 fracción I de la ley de adquisiciones, arrendamientos y servicios del sector público estatal y municipal.");
				}
			}
		}
		return "";
	}
	/**
	 * @name WriteDocument
	 * {@summary Method to write document to disk }
	 * @param document
	 * @param adjudication
	 * @return
	 * @throws Exception 
	 */
	private String WriteDocument(XWPFDocument document, int adjudicationId, int supplierid) throws Exception {
		// Define timestamp
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// Define directory
		String directory = String.format("%s/%s", this.folderProperties.getDocuments(), new SimpleDateFormat("yyyyMM").format(new Date()));
		// Create directory
		if(Files.notExists(Paths.get(directory)))
			Files.createDirectory(Paths.get(directory));
		// Define new name for file
		String newName = String.format("%s_%s_%s_%s", String.valueOf(adjudicationId), String.valueOf(supplierid), timestamp, this.documentProperties.getJudgment());
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
	 * @name createDocument
	 * {@summary Method to create document for winner proposals }
	 * @param adjudication
	 * @param institution
	 * @param emission
	 * @param opening
	 * @param competitorParamOutputs
	 * @param dicProposalByCompetitorId
	 * @return
	 * @throws Exception
	 */
	private List<String> createDocument(Adjudication adjudication, Institution institution, Emission emission, Opening opening, List<CompetitorParamOutput> competitorParamOutputs, Map<Integer, Proposal> dicProposalByCompetitorId, Map<Integer, List<Item>> dicItemsByProposalId) throws Exception {
		// Define result list
		ArrayList<String> documentNames = new ArrayList<String>();
		// Loop in competitors to identify winners proposals
		for(CompetitorParamOutput competitorParamOutput : competitorParamOutputs) {
			// Check if proposal is winner
			Proposal winnerProposal = dicProposalByCompetitorId.get(competitorParamOutput.getCompetitor().getCompetitorId());
			if(winnerProposal.getWinner() == 1) {
				// If proposal is winner create document for winner
				XWPFDocument document = new XWPFDocument();
				// Create first paragraph
				this.createParagraph(document, ParagraphAlignment.BOTH , opening.getDenomination());
				// Create second paragraph
				this.createParagraph(document, ParagraphAlignment.CENTER, "ACTA DE FALLO");
				// Create table
				ArrayList<String> tableHeaders = new ArrayList<String>();
				tableHeaders.add(String.format("ADJUDICACION %s", adjudication.getAdjudicationType()));
				tableHeaders.add(adjudication.getProcedureNumber());
				ArrayList<String> tableRow = new ArrayList<String>();
				tableRow.add(opening.getDenomination());
				tableRow.add("");
				ArrayList<List<String>> tableBody = new ArrayList<List<String>>();
				tableBody.add(tableRow);
				this.createTable(document, tableHeaders, tableBody);
				// Create third paragraph
				this.createParagraph(document, ParagraphAlignment.LEFT , 
						String.format("Siendo las %s  , del %s, estando presentes en el Organismo Público Descentralizado %s con domicilio en  %s, se reunieron los servidores públicos"
								    + " cuyos nombres y firmas aparecen al final de la presente Acta, con objeto de llevar a cabo el Fallo del %s número %s , para la adquisición de %s, de conformidad "
								    + "con los artículos 36, 36 Bis, y 37 de la Ley de Adquisiciones, Arrendamientos y Servicios del Sector Público, en adelante, la Ley; 39 fracción VIII, 47 y 77 de su"
								    + " Reglamento.", opening.getEventStartHourText(), opening.getEventDateText(), institution.getName(), institution.getAddress(), adjudication.getAdjudicationType(), 
								    adjudication.getProcedureNumber(), opening.getDenomination()));
				// Create fourth paragraph
				this.createParagraph(document, ParagraphAlignment.LEFT, 
						String.format("El acto es presidido por el (la) C. %s  servidor(a) público(a) designada por la convocante; además asisten los CC %s, %s, testigos.", 
									opening.getReponsable(), opening.getFirstWitness(), opening.getSecondWitness()));
				// Create fiveth paragraph
				this.createParagraph(document, ParagraphAlignment.LEFT, 
						String.format("DICTAMEN GENERAL DE LAS PROPUESTAS DE LA ADJUDICACIÓN POR INVITACIÓN A CUANDO MENOS TRES PERSONAS NÚMERO %s", 
									adjudication.getProcedureNumber()));
				// Create sixth paragrpah
				this.createParagraph(document, ParagraphAlignment.LEFT, 
						String.format("De conformidad con lo establecido en las Invitaciones del Procedimiento de Adjudicación %s  número  %s, se realizó un análisis detallado de cada una de las propuestas"
								   + " Técnicas de los participantes. Derivado de esta revisión, se determina que las propuestas presentadas cumplen técnicamente con la documentación solicitada, motivo por"
								   + " el cual las proposiciones son técnicamente validas, declarándose en consecuencia, susceptibles de ser evaluadas económicamente, lo que se hace a continuación: ", 
									adjudication.getAdjudicationType(), adjudication.getProcedureNumber()));
				// Create seventh paragraph
				this.createParagraph(document, ParagraphAlignment.LEFT, "Con fundamento en el artículo 37 de la Ley de Adquisiciones, Arrendamientos y Servicios del Sector Público, y en razón del Cuadro "
									+ "Comparativo que se anexa a esta Acta, el cual forma parte integrante de la misma, procede a dar lectura al importe total de cada proposición en términos de precios "
									+ "unitarios sin incluir el Impuesto al Valor Agregado IVA, montos que se consignan a continuación:");
				// Create table of proposals with the total of winner items
				// First identify the winner items
				ArrayList<Item> winnerItems = new ArrayList<Item>();
				for(Item item : dicItemsByProposalId.get(winnerProposal.getProposalId())) {
					// Check if item is winner
					if(item.getWinner() == 1)
						winnerItems.add(item);
				}
				// Define headers
				ArrayList<String> competitorTableHeader = new ArrayList<String>();
				competitorTableHeader.add("No");
				competitorTableHeader.add("PERSONAS FÍSICAS O JURÍDICAS QUE PRESENTARON PROPOSICIONES");
				competitorTableHeader.add("IMPORTE TOTAL PRECIOS UNITARIOS");
				ArrayList<CompetitorParamOutput> validCompetitors = new ArrayList<CompetitorParamOutput>(); 
				// Define table body
				ArrayList<List<String>> competitorTableBody = new ArrayList<List<String>>();
				// Loop in proposal to make the sum
				int rowNumber = 1;
				for(CompetitorParamOutput competitorTotalAmount : competitorParamOutputs) {
					// Get proposal
					Proposal proposalAmount = dicProposalByCompetitorId.get(competitorTotalAmount.getCompetitor().getCompetitorId());
					// Check if is valid proposal
					if(proposalAmount.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) && proposalAmount.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED)) {
						// Get items and then create a dictionary to loop in winner items and the amount to save
						List<Item> itemsAmount = dicItemsByProposalId.get(proposalAmount.getProposalId());
						Map<Integer, Item> dicItemByAnnexId = new HashMap<Integer, Item>();
						for(Item itemAmount : itemsAmount) {
							if(!dicItemByAnnexId.containsKey(itemAmount.getAnnexId()))
								dicItemByAnnexId.put(itemAmount.getAnnexId(),itemAmount);
						}
						// Loop in winner items to get amount
						double totalAmount = 0;
						for(Item winnerItem : winnerItems) {
							// Get item
							Item itemAmount = dicItemByAnnexId.get(winnerItem.getAnnexId());
							totalAmount += itemAmount.getTotalAmount();
						}
						// Create row content
						ArrayList<String> rowContent = new ArrayList<String>();
						rowContent.add(String.valueOf(rowNumber));
						rowContent.add(competitorTotalAmount.getSupplier().getName());
						rowContent.add(String.format("%.2f", totalAmount));
						// Add to table body
						competitorTableBody.add(rowContent);
						// Increate number
						rowNumber++;
						// Set valid competitor
						validCompetitors.add(competitorTotalAmount);
					}
				}
				// Create table
				this.createTable(document, competitorTableHeader, competitorTableBody);
				// Create eighth paragraph
				this.createParagraph(document, ParagraphAlignment.LEFT, 
						String.format("Del cuadro anterior, se concluye que la propuesta efectuadas por %s , es la propuesta económica solvente más baja en términos de precios unitarios, en virtud de"
								   + " contener un importe aceptable para la convocante y cumplir con las especificaciones técnicas contenidas en las invitaciones del Procedimiento; dentro del tiempo "
								   + "de entrega establecido y garantizar el cumplimiento de las obligaciones respectivas, motivo por el cual se adjudica a su favor este procedimiento consistente en la "
								   + "adquisición de %s, hasta por la cantidad puesta en su propuesta económica", competitorParamOutput.getSupplier().getName(), opening.getDenomination()));
				// Create nineth paragraph
				this.createParagraph(document, ParagraphAlignment.LEFT, 
						String.format("Después de dar lectura a la presente Acta, se dio por terminado este acto, siendo las %s, del mismo día de su inicio.", opening.getEventEndHourText()));
				// Create tenth paragraph
				this.createParagraph(document, ParagraphAlignment.CENTER,"POR LOS PARTICIPANTES");
				// Create table
				ArrayList<String> competitorSignTableHeader = new ArrayList<String>();
				competitorSignTableHeader.add("NOMBRE");
				competitorSignTableHeader.add("FIRMA");
				ArrayList<List<String>> competitorSignTableBody = new ArrayList<List<String>>();
				for(CompetitorParamOutput validCompetitor : validCompetitors) {
					// Define row content
					ArrayList<String> rowContent = new ArrayList<String>();
					rowContent.add(validCompetitor.getSupplier().getName());
					rowContent.add("______________________________");
					competitorSignTableBody.add(rowContent);
				}
				this.createTable(document, competitorSignTableHeader, competitorSignTableBody);
				// Create eleventh paragraph
				this.createParagraph(document, ParagraphAlignment.CENTER,"POR EL ORGANISMO");
				// Create table
				ArrayList<String> institutionTableHeader = new ArrayList<String>();
				institutionTableHeader.add("NOMBRE");
				institutionTableHeader.add("FIRMA");
				ArrayList<List<String>> institutionSignTableBody = new ArrayList<List<String>>();
				ArrayList<String> rowContent1 = new ArrayList<String>();
				rowContent1.add(opening.getReponsable());
				rowContent1.add("______________________________");
				institutionSignTableBody.add(rowContent1);
				ArrayList<String> rowContent2 = new ArrayList<String>();
				rowContent2.add(opening.getFirstWitness());
				rowContent2.add("______________________________");
				institutionSignTableBody.add(rowContent2);
				ArrayList<String> rowContent3 = new ArrayList<String>();
				rowContent3.add(opening.getSecondWitness());
				rowContent3.add("______________________________");
				institutionSignTableBody.add(rowContent3);
				this.createTable(document, institutionTableHeader, institutionSignTableBody);
				// Create twelveth paragraph
				this.createParagraph(document, ParagraphAlignment.CENTER,"---------------------------------------------------- FIN DEL ACTA -----------------------------------------------------");
				// Write document
		        String pathDocument = WriteDocument(document, adjudication.getAdjudicationId(), competitorParamOutput.getSupplier().getSupplierId());
		        // Get filenma
		        String fileName = new File(pathDocument).getName();
				// Save the adjudication document
		 		this.adjudicationDocumentService.Save(new AdjudicationDocument(0, adjudication.getAdjudicationId(), IDENTIFIER, fileName, pathDocument));
		 		// Add document name to collection
		 		documentNames.add(fileName);
			}
		}
		// Save step
		this.adjudicationStepService.Save(new AdjudicationStep(0, adjudication.getAdjudicationId(), IDENTIFIER, LocalDateTime.now()) );
		// return document names
		return documentNames;
	}
	/**
	 * @name closeAdjudication
	 * {@summary Method to close adjudication }
	 * @param adjudicationId
	 * @param closeDate
	 * @return
	 */
	public List<String> closeAdjudication(int adjudicationId, LocalDateTime closeDate) throws Exception {
		// Close adjudication
		this.adjudicationService.updateCloseDate(adjudicationId, closeDate);
		// Get adjudication service
		Adjudication adjudication = this.adjudicationService.getById(adjudicationId);
		// Get Institution data
		Institution institution = this.institutionService.get().get(0);
		// Get emission data
		Emission emission = this.emissionService.getByAdjudicationId(adjudicationId);
		// Get opening data
		Opening opening = this.openingService.getByAdjudicationId(adjudicationId);
		// Get competitor data
		List<CompetitorParamOutput> competitorOutputParams = this.competitorService.getByAdjudicationId(adjudicationId);
		// Get proposal data
		List<Proposal> proposals = this.proposalService.getByAdjudicationId(adjudicationId);
		Map<Integer, Proposal> dicProposalByCompetitorId = new HashMap<Integer, Proposal>();
		Map<Integer, List<Item>> dicItemsByProposalId = new HashMap<Integer, List<Item>>();
		for(Proposal proposal : proposals) {
			if(!dicProposalByCompetitorId.containsKey(proposal.getCompetitorId()));
				dicProposalByCompetitorId.put(proposal.getCompetitorId(), proposal);
			// Add items
			List<Item> items = this.itemService.getByProposalId(proposal.getProposalId());
			if(!dicItemsByProposalId.containsKey(proposal.getProposalId()))
				dicItemsByProposalId.put(proposal.getProposalId(), items);
		}
		// Create document
		return createDocument(adjudication, institution, emission, opening,  competitorOutputParams, dicProposalByCompetitorId, dicItemsByProposalId);
	}
}