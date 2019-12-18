package com.ryuseicode.siap.wrapper.award.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.entity.award.AdjudicationDocument;
import com.ryuseicode.siap.entity.award.AdjudicationStep;
import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.entity.award.Item;
import com.ryuseicode.siap.entity.award.Opening;
import com.ryuseicode.siap.entity.award.Proposal;
import com.ryuseicode.siap.entity.award.Quotation;
import com.ryuseicode.siap.entity.award.QuotationDetail;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.properties.DocumentProperties;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.AdjudicationStepService;
import com.ryuseicode.siap.service.award.imp.AnnexService;
import com.ryuseicode.siap.service.award.imp.CompetitorService;
import com.ryuseicode.siap.service.award.imp.EmissionService;
import com.ryuseicode.siap.service.award.imp.ItemService;
import com.ryuseicode.siap.service.award.imp.OpeningService;
import com.ryuseicode.siap.service.award.imp.ProposalService;
import com.ryuseicode.siap.service.award.imp.QuotationDetailService;
import com.ryuseicode.siap.service.award.imp.QuotationService;
import com.ryuseicode.siap.wrapper.award.intf.IQuotationWrapper;
/**
 * @name QuotationWrapper
 * {@summary Wrapper to implement the behavior of QuotationWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
@Service
@Transactional
public class QuotationWrapper implements IQuotationWrapper {
	/**
	 * QUOTATION
	 */
	private static final String IDENTIFIER = "QUOTATION";
	/**
	 * QuotationService
	 */
	@Autowired
	private QuotationService quotationService;
	/**
	 * QuotationDetailService
	 */
	@Autowired
	private QuotationDetailService quotationDetailService;	
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
	 * OpeningService
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
	 * AnnexService
	 */
	@Autowired 
	private AnnexService annexService;
	
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
	private String WriteDocument(HSSFWorkbook workbook, int adjudicationId, int supplierId) throws Exception {
		// Define timestamp
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// Define directory
		String directory = String.format("%s/%s", this.folderProperties.getDocuments(), new SimpleDateFormat("yyyyMM").format(new Date()));
		// Create directory
		if(Files.notExists(Paths.get(directory)))
			Files.createDirectory(Paths.get(directory));
		// Define new name for file
		String newName = String.format("%s_%s_%s_%s", String.valueOf(adjudicationId), String.valueOf(supplierId), timestamp, this.documentProperties.getComparative());
		// Define new document path
		String newDocumentPath = String.format("%s/%s", directory, newName);
		// Create file output stream
		FileOutputStream fileOutputStream = new FileOutputStream(newDocumentPath);
		// Write document
		workbook.write(fileOutputStream);
		// Close document
		workbook.close();
		// Close output stream
		fileOutputStream.close();
		// return new document path
		return newDocumentPath;
	}
	/**
	 * @name CreateDocuement
	 * {@summary Method to create documents of comparison for winner proposals }
	 * @param adjudication
	 * @param opening
	 * @param competitorParamOutputs
	 * @param dicAnnexByAnnexId
	 * @param dicProposalByCompetitorId
	 * @param dicItemsByProposalId
	 * @param quotation
	 * @param dicQuotationDetailByCompetitorId
	 * @return
	 */
	public List<String> CreateDocuments(Adjudication adjudication, Opening opening, List<CompetitorParamOutput> competitorParamOutputs, Map<Integer, AnnexParamOutput> dicAnnexByAnnexId, Map<Integer, Proposal> dicProposalByCompetitorId, Map<Integer, List<Item>> dicItemsByProposalId, Quotation quotation, Map<Integer, QuotationDetail> dicQuotationDetailByCompetitorId) throws Exception {		
		// Define list of documents
		ArrayList<String> fileNames = new ArrayList<String>();
		// Loop in competitor and check if is a winner competitor
		for(CompetitorParamOutput competitorParamOutput : competitorParamOutputs) {
			// Check if is a winner proposal, if not continue in next loop 
			Proposal proposal = dicProposalByCompetitorId.get(competitorParamOutput.getCompetitor().getCompetitorId());
			if( proposal.getWinner() == 1) {
				// if proposal is winner buil comparison document
				// Define row index
				int rowIndex = 0;
				// Create excel document
		        HSSFWorkbook workbook = new HSSFWorkbook();
				// Create sheet for excel document
		        HSSFSheet firstSheet = workbook.createSheet(adjudication.getProcedureNumber());
		        // Define the style of cells
		        HSSFFont workbookfont = workbook.createFont();
		        workbookfont.setBold(true);
		        workbookfont.setColor(IndexedColors.GREEN.getIndex());
		        CellStyle cellStyle = workbook.createCellStyle();
		        cellStyle.setFont(workbookfont);
		        // Create row and set columns headers
		        HSSFRow row01 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell01A = row01.createCell(0);
		        cell01A.setCellValue(new HSSFRichTextString("FECHA DE ELABORACIÓN:"));
		        HSSFCell cell01B = row01.createCell(1);
		        cell01B.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cell01C = row01.createCell(2);
		        cell01C.setCellValue(new HSSFRichTextString(quotation.getElaborationDateText()));
		        CellRangeAddress cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        // Create second row
		        HSSFRow row02 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell02A = row02.createCell(0);
		        cell02A.setCellValue(new HSSFRichTextString("MODALIDAD DE ADQUISICIÓN:"));
		        HSSFCell cell02B = row02.createCell(1);
		        cell02B.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cell02C = row02.createCell(2);
		        cell02C.setCellValue(new HSSFRichTextString(adjudication.getAdjudicationType()));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        // Create third row
		        HSSFRow row03 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell03A = row03.createCell(0);
		        cell03A.setCellValue(new HSSFRichTextString("DESCRIPCIÓN:"));
		        HSSFCell cell03B = row03.createCell(1);
		        cell03B.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cell03C = row03.createCell(2);
		        cell03C.setCellValue(new HSSFRichTextString(opening.getDenomination()));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        // Create fourth row
		        HSSFRow row04 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell04A = row04.createCell(0);
		        cell04A.setCellValue(new HSSFRichTextString("PROVEEDORES PARTICIPANTES"));
		        HSSFCell cell04B = row04.createCell(1);
		        cell04B.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        // Create fiveth row
		        HSSFRow row05 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell05A = row05.createCell(0);
		        cell05A.setCellValue(new HSSFRichTextString("NOMBRE, DENOMINACIÓN O RAZÓN SOCIAL:"));
		        HSSFCell cell05B = row05.createCell(1);
		        cell05B.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        // Loop in proposal to put the name of suppliers
		        int columnIndex = 2;
		        for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		// Add cell with supplier name
		        		HSSFCell cell1 = row05.createCell(columnIndex++);
				        cell1.setCellValue(new HSSFRichTextString(competitorSupplierName.getSupplier().getName()));
				        HSSFCell cell2 = row05.createCell(columnIndex++);
				        cell2.setCellValue(new HSSFRichTextString(""));
				        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, columnIndex - 2, columnIndex - 1);
				        firstSheet.addMergedRegion(cellRegion);
		        	}
		        }
		        // Create six row
		        HSSFRow row06 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell06A = row06.createCell(0);
		        cell06A.setCellValue(new HSSFRichTextString("FECHA DE LA COTIZACIÓN:"));
		        HSSFCell cell06B = row06.createCell(1);
		        cell06B.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        columnIndex = 2;
		        for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		// Add cell with quotation date
		        		HSSFCell cell1 = row06.createCell(columnIndex++);
				        cell1.setCellValue(new HSSFRichTextString(dicQuotationDetailByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId()).getQuotationDateText()));				        
				        HSSFCell cell2 = row06.createCell(columnIndex++);
				        cell2.setCellValue(new HSSFRichTextString(""));
				        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, columnIndex - 2, columnIndex - 1);
				        firstSheet.addMergedRegion(cellRegion);
				        
		        	}
		        }
		        // Create seventh row
		        HSSFRow row07 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell07A = row07.createCell(0);
		        cell07A.setCellValue(new HSSFRichTextString("CONDICIONES DE CRÉDITO:"));
		        HSSFCell cell07B = row07.createCell(1);
		        cell07B.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        columnIndex = 2;
		        for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		// Add cell with quotation date
		        		HSSFCell cell1 = row07.createCell(columnIndex++);
				        cell1.setCellValue(new HSSFRichTextString(dicQuotationDetailByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId()).getCreditCondition()));				        
				        HSSFCell cell2 = row07.createCell(columnIndex++);
				        cell2.setCellValue(new HSSFRichTextString(""));
				        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, columnIndex - 2, columnIndex - 1);
				        firstSheet.addMergedRegion(cellRegion);
		        	}
		        }
		        // Create eight row
		        HSSFRow row08 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell08A = row08.createCell(0);
		        cell08A.setCellValue(new HSSFRichTextString("PLAZO DE ENTREGA:"));
		        HSSFCell cell08B = row08.createCell(1);
		        cell08B.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 1);
		        firstSheet.addMergedRegion(cellRegion);
		        columnIndex = 2;
		        for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		// Add cell with quotation date
		        		HSSFCell cell1 = row08.createCell(columnIndex++);
				        cell1.setCellValue(new HSSFRichTextString(dicQuotationDetailByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId()).getDeliveryTerm()));				        
				        HSSFCell cell2 = row08.createCell(columnIndex++);
				        cell2.setCellValue(new HSSFRichTextString(""));
				        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, columnIndex - 2, columnIndex - 1);
				        firstSheet.addMergedRegion(cellRegion);
		        	}
		        }
		        // Create nineth row
		        HSSFRow row09 = firstSheet.createRow(rowIndex++);
		        HSSFCell cell09A = row09.createCell(0);
		        cell09A.setCellValue(new HSSFRichTextString("No. RENGLÓN"));
		        HSSFCell cell09B = row09.createCell(1);
		        cell09B.setCellValue(new HSSFRichTextString("CANTIDAD"));
		        columnIndex = 2;
		        for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		// Add cell with quotation date
		        		HSSFCell cell1 = row09.createCell(columnIndex++);
				        cell1.setCellValue(new HSSFRichTextString("PRECIO UNITARIO"));				        
				        HSSFCell cell2 = row09.createCell(columnIndex++);
				        cell2.setCellValue(new HSSFRichTextString("IMPORTE"));
		        	}
		        }
		        // Create dictionary of sum for prices
		        Map<Integer, ArrayList<Double>> dicItemsTotalByProposalId = new HashMap<Integer, ArrayList<Double>>();  
		        // Loop in proposal items to check if is winner item
		        for(Item item : dicItemsByProposalId.get(proposal.getProposalId())) {
		        	// Check if is winner item
		        	if(item.getWinner() == 1) {
		        		// if is winner item create row
		        		HSSFRow rowItem = firstSheet.createRow(rowIndex);
		        		// set column index
		        		columnIndex = 0;
		        		// Create cell of asset name
				        HSSFCell cell1 = rowItem.createCell(columnIndex++);
				        cell1.setCellValue(new HSSFRichTextString(String.valueOf(dicAnnexByAnnexId.get(item.getAnnexId()).getAsset().getName())));
				        // Create cell quantity
				        HSSFCell cell2 = rowItem.createCell(columnIndex++);
				        cell2.setCellValue(new HSSFRichTextString(String.valueOf(dicAnnexByAnnexId.get(item.getAnnexId()).getAnnex().getQuantity())));
				        // Loop in proposals to set the unit price and total
				        for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
				        	// Get proposal
				        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
				        	// Check if is valid
				        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
				        	{
				        		// Get items of proposal
				        		List<Item> itemsProposalSupplier = dicItemsByProposalId.get(proposalSupplier.getProposalId());
				        		// Loop in items to look for similar winner item
				        		for(Item itemProposalSupplier : itemsProposalSupplier) {
				        			if(item.getAnnexId() == itemProposalSupplier.getAnnexId()) {
				        				// Create cell of unit price
				        				HSSFCell cellUnitPrice = rowItem.createCell(columnIndex++);
				        				cellUnitPrice.setCellValue(new HSSFRichTextString(String.format("%.2f", itemProposalSupplier.getUnitPrice())));
				        				// Create cell of total amount
				        				HSSFCell cellTotalAmount = rowItem.createCell(columnIndex++);
				        				cellTotalAmount.setCellValue(new HSSFRichTextString(String.format("%.2f", itemProposalSupplier.getTotalAmount())));
				        				// Add total amount
				        				if(!dicItemsTotalByProposalId.containsKey(proposalSupplier.getProposalId()))
				        					dicItemsTotalByProposalId.put(proposalSupplier.getProposalId(), new ArrayList<Double>());
				        				dicItemsTotalByProposalId.get(proposalSupplier.getProposalId()).add(itemProposalSupplier.getTotalAmount());
				        				// Break  loop continue with next element				        				
				        				break;
				        			}
				        		} 	
				        	}
				        }
		        		// Increate index
		        		rowIndex++;
		        	}
		        }
		        // Create row of sum		     
        		HSSFRow rowSum = firstSheet.createRow(rowIndex);
        		// Set column index
        		columnIndex = 0;
		        // Set empty cells
        		HSSFCell cellSum1 = rowSum.createCell(columnIndex++);
        		cellSum1.setCellValue(new HSSFRichTextString(""));
        		HSSFCell cellSum2 = rowSum.createCell(columnIndex++);
        		cellSum2.setCellValue(new HSSFRichTextString(""));
        		// Loop in proposal to set the total
        		for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		HSSFCell cellSumText = rowSum.createCell(columnIndex++);
		        		cellSumText.setCellValue(new HSSFRichTextString("SUMA"));
		        		HSSFCell cellSumValue = rowSum.createCell(columnIndex++);
		        		cellSumValue.setCellValue(new HSSFRichTextString(String.format("%.2f", dicItemsTotalByProposalId.get(proposalSupplier.getProposalId()).stream().reduce((double)0, Double::sum))));
		        		if(proposalSupplier.getWinner() == 1)
		        			cellSumValue.setCellStyle(cellStyle);	
		        	}
		        }
        		rowIndex++;
        		// Create row of Iva		     
        		HSSFRow rowIva = firstSheet.createRow(rowIndex);
        		// Set column index
        		columnIndex = 0;
		        // Set empty cells
        		HSSFCell cellIva1 = rowIva.createCell(columnIndex++);
        		cellIva1.setCellValue(new HSSFRichTextString(""));
        		HSSFCell cellIva2 = rowIva.createCell(columnIndex++);
        		cellIva2.setCellValue(new HSSFRichTextString(""));
        		// Loop in proposal to set the total
        		for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		HSSFCell cellIvaText = rowIva.createCell(columnIndex++);
		        		cellIvaText.setCellValue(new HSSFRichTextString("IVA"));
		        		HSSFCell cellIvaValue = rowIva.createCell(columnIndex++);
		        		cellIvaValue.setCellValue(new HSSFRichTextString(String.format("%.2f", dicItemsTotalByProposalId.get(proposalSupplier.getProposalId()).stream().reduce((double)0, Double::sum) * 0.16)));
		        		if(proposalSupplier.getWinner() == 1)
		        			cellIvaValue.setCellStyle(cellStyle);	
		        	}
		        }
        		rowIndex++;
        		// Create row total
        		HSSFRow rowTotal = firstSheet.createRow(rowIndex);
        		// Set column index
        		columnIndex = 0;
		        // Set empty cells
        		HSSFCell cellTotal1 = rowTotal.createCell(columnIndex++);
        		cellTotal1.setCellValue(new HSSFRichTextString(""));
        		HSSFCell cellTotal2 = rowTotal.createCell(columnIndex++);
        		cellTotal2.setCellValue(new HSSFRichTextString(""));
        		// Loop in proposal to set the total
        		for(CompetitorParamOutput competitorSupplierName : competitorParamOutputs) {
		        	// Get proposal
		        	Proposal proposalSupplier = dicProposalByCompetitorId.get(competitorSupplierName.getCompetitor().getCompetitorId());
		        	// Check if is valid
		        	if(proposalSupplier.getTechnicalSubmitted().equals(Proposal.PROPOSAL_SUBMITTED) &&  proposalSupplier.getEconomicSubmitted().equals(Proposal.PROPOSAL_SUBMITTED))
		        	{
		        		HSSFCell cellTotalText = rowTotal.createCell(columnIndex++);
		        		cellTotalText.setCellValue(new HSSFRichTextString("Total"));
		        		double sum = dicItemsTotalByProposalId.get(proposalSupplier.getProposalId()).stream().reduce((double)0, Double::sum);
		        		double iva = dicItemsTotalByProposalId.get(proposalSupplier.getProposalId()).stream().reduce((double)0, Double::sum) * 0.16;
		        		HSSFCell cellTotalValue = rowTotal.createCell(columnIndex++);
		        		cellTotalValue.setCellValue(new HSSFRichTextString(String.format("%.2f",sum + iva)));
		        		if(proposalSupplier.getWinner() == 1)
		        			cellTotalValue.setCellStyle(cellStyle);
		        	}
		        }
        		rowIndex++;
		        // Set legend 1
		        HSSFRow rowSign01 = firstSheet.createRow(rowIndex++);
		        HSSFCell cellSign01A = rowSign01.createCell(0);
		        cellSign01A.setCellValue(new HSSFRichTextString("(   X   )	EFICACIA		(   X   ) EFICIENCIA			(    X   ) IMPARCIALIDAD			(    X   ) HONRADEZ"));
		        // Set legend 2
		        HSSFRow rowSign02 = firstSheet.createRow(rowIndex++);
		        HSSFCell cellSign02A = rowSign02.createCell(0);
		        cellSign02A.setCellValue(new HSSFRichTextString("AREA REQUIRENTE"));
		        HSSFCell cellSign02B = rowSign02.createCell(1);
		        cellSign02B.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign02C = rowSign02.createCell(2);
		        cellSign02C.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set signatures
		        HSSFCell cellSign02D = rowSign02.createCell(3);
		        cellSign02D.setCellValue(new HSSFRichTextString("Vo. Bo."));
		        HSSFCell cellSign02E = rowSign02.createCell(4);
		        cellSign02E.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 3, 4);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set aurhotizer
		        HSSFCell cellSign02F = rowSign02.createCell(5);
		        cellSign02F.setCellValue(new HSSFRichTextString("AUTORIZO"));
		        HSSFCell cellSign02G = rowSign02.createCell(6);
		        cellSign02G.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign02H = rowSign02.createCell(7);
		        cellSign02H.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 5, 7);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set signature line
		        HSSFRow rowSign03 = firstSheet.createRow(rowIndex++);
		        HSSFCell cellSign03A = rowSign03.createCell(0);
		        cellSign03A.setCellValue(new HSSFRichTextString("____________________________"));
		        HSSFCell cellSign03B = rowSign03.createCell(1);
		        cellSign03B.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign03C = rowSign03.createCell(2);
		        cellSign03C.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set signature line
		        HSSFCell cellSign03D = rowSign03.createCell(3);
		        cellSign03D.setCellValue(new HSSFRichTextString("____________________________"));
		        HSSFCell cellSign03E = rowSign03.createCell(4);
		        cellSign03E.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 3, 4);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set signature line
		        HSSFCell cellSign03F = rowSign03.createCell(5);
		        cellSign03F.setCellValue(new HSSFRichTextString("____________________________"));
		        HSSFCell cellSign03G = rowSign03.createCell(6);
		        cellSign03G.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign03H = rowSign03.createCell(7);
		        cellSign03H.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 5, 7);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set demanding
		        HSSFRow rowSign04 = firstSheet.createRow(rowIndex++);
		        HSSFCell cellSign04A = rowSign04.createCell(0);
		        cellSign04A.setCellValue(new HSSFRichTextString(quotation.getDemanding()));
		        HSSFCell cellSign04B = rowSign04.createCell(1);
		        cellSign04B.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign04C = rowSign04.createCell(2);
		        cellSign04C.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set authorizer
		        HSSFCell cellSign04D = rowSign04.createCell(3);
		        cellSign04D.setCellValue(new HSSFRichTextString(quotation.getAuthorizer()));
		        HSSFCell cellSign04E = rowSign04.createCell(4);
		        cellSign04E.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 3, 4);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set zzz
		        HSSFCell cellSign04F = rowSign04.createCell(5);
		        cellSign04F.setCellValue(new HSSFRichTextString("zzz"));
		        HSSFCell cellSign04G = rowSign04.createCell(6);
		        cellSign04G.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign04H = rowSign04.createCell(7);
		        cellSign04H.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 5, 7);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set demanding position
		        HSSFRow rowSign05 = firstSheet.createRow(rowIndex++);
		        HSSFCell cellSign05A = rowSign05.createCell(0);
		        cellSign05A.setCellValue(new HSSFRichTextString(quotation.getDemandingJob()));
		        HSSFCell cellSign05B = rowSign05.createCell(1);
		        cellSign05B.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign05C = rowSign05.createCell(2);
		        cellSign05C.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 2);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set authorized job
		        HSSFCell cellSign05D = rowSign05.createCell(3);
		        cellSign05D.setCellValue(new HSSFRichTextString(quotation.getAuthorizerJob()));
		        HSSFCell cellSign05E = rowSign05.createCell(4);
		        cellSign05E.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 3, 4);
		        firstSheet.addMergedRegion(cellRegion);
		        // Set rector
		        HSSFCell cellSign05F = rowSign05.createCell(5);
		        cellSign05F.setCellValue(new HSSFRichTextString("RECTOR"));
		        HSSFCell cellSign05G = rowSign05.createCell(6);
		        cellSign05G.setCellValue(new HSSFRichTextString(""));
		        HSSFCell cellSign05H = rowSign05.createCell(7);
		        cellSign05H.setCellValue(new HSSFRichTextString(""));
		        cellRegion = new CellRangeAddress(rowIndex - 1, rowIndex - 1, 5, 7);
		        firstSheet.addMergedRegion(cellRegion);
				// Write excel document
		        String documentPath = WriteDocument(workbook, competitorParamOutput.getCompetitor().getAdjudicationId(), competitorParamOutput.getSupplier().getSupplierId());
		        // Save document in adjudication document
         		this.adjudicationDocumentService.Save(new AdjudicationDocument(0, adjudication.getAdjudicationId(), IDENTIFIER, new File(documentPath).getName(), documentPath) );
		        // Set the file name
		        fileNames.add(new File(documentPath).getName());
			}
		}
		// Save step
 		this.adjudicationStepService.Save(new AdjudicationStep(0, adjudication.getAdjudicationId(), IDENTIFIER, LocalDateTime.now()));
 		// Update status
 	 	this.adjudicationService.updateStatus(adjudication.getAdjudicationId(), AdjudicationService.STATUS_FAILURE);
 		// return filename
		return fileNames;
	}
	
	/**
	 * @name create
	 * {@summary Method to create quotation  }
	 * @param quotation
	 * @param quotationDetails
	 * @return
	 */
	public List<String> create(Quotation quotation, List<QuotationDetail> quotationDetails) throws Exception {
		// Save the quotation information
		this.quotationService.save(quotation);
		// Get the quotation id by adjudication
		quotation = this.quotationService.getByAdjudicationId(quotation.getAdjudicationId());
		// Loop in quotation details to set the quotation id
		for(QuotationDetail quotationDetail :  quotationDetails) {
			// Set quotation id
			quotationDetail.setQuotationId(quotation.getQuotationId());
		}
		// Save list of quotations
		this.quotationDetailService.save(quotation.getQuotationId(), quotationDetails);
		// Get adjudication information to create excel document
		Adjudication adjudication = this.adjudicationService.getById(quotation.getAdjudicationId());
		// Get competitors
		List<CompetitorParamOutput> competitorParamOutputs = this.competitorService.getByAdjudicationId(adjudication.getAdjudicationId());
		// Get opening
		Opening opening = this.openingService.getByAdjudicationId(adjudication.getAdjudicationId());
		// Get annex of adjudication
		List<AnnexParamOutput> annexs = this.annexService.GetByAdjudicationId(adjudication.getAdjudicationId());
		Map<Integer, AnnexParamOutput> dicAnnexByAnnexId = new HashMap<Integer, AnnexParamOutput>();
		for(AnnexParamOutput annexParamOutput : annexs) { 
			if(!dicAnnexByAnnexId.containsKey(annexParamOutput.getAnnex().getAnnexId()))
				dicAnnexByAnnexId.put(annexParamOutput.getAnnex().getAnnexId(), annexParamOutput);
		}
		// Get proposals and items
		List<Proposal> proposals = this.proposalService.getByAdjudicationId(adjudication.getAdjudicationId());
		Map<Integer, Proposal> dicProposalByCompetitorId = new HashMap<Integer, Proposal>();
		Map<Integer, List<Item>> dicItemsByProposalId = new HashMap<Integer, List<Item>>();
		for(Proposal proposal : proposals) {
			// Add proposal to dictionary
			if(!dicProposalByCompetitorId.containsKey(proposal.getCompetitorId()))
				dicProposalByCompetitorId.put(proposal.getCompetitorId(), proposal);
			// Get items of proposal
			List<Item> items = this.itemService.getByProposalId(proposal.getProposalId()); 
			if(!dicItemsByProposalId.containsKey(proposal.getProposalId()))
				dicItemsByProposalId.put(proposal.getProposalId(), items);
		}
		// Create dictionary of quoatationDetails
		Map<Integer, QuotationDetail> dicQuotationDetailByCompetitorId = new HashMap<Integer, QuotationDetail>();
		for(QuotationDetail quotationDetail : quotationDetails) {
			if(!dicQuotationDetailByCompetitorId.containsKey(quotationDetail.getCompetitorId()))
				dicQuotationDetailByCompetitorId.put(quotationDetail.getCompetitorId(), quotationDetail);
		}
		// Create documents
		return CreateDocuments(adjudication, opening, competitorParamOutputs, dicAnnexByAnnexId, dicProposalByCompetitorId, dicItemsByProposalId, quotation, dicQuotationDetailByCompetitorId);
	}
	/**
	 * @name ValidateQuotationDate
	 * {@summary Method to validate quotation date }
	 * @param adjudicationId
	 * @param quotationDate
	 * @return
	 * @throws Exception
	 */
	public String validateQuotationDate(int adjudicationId, LocalDate quotationDate) throws Exception {
		//  Get adjudication by id
		Adjudication adjudication = this.adjudicationService.getById(adjudicationId);
		// Get emission by adjudicationId
		Emission emission = this.emissionService.getByAdjudicationId(adjudicationId);
		// Check type of adjudication
		if( adjudication.getAdjudicationType().equals(Adjudication.ADJUDICATION_TYPE_DIRECT)) {
			// Compare dates
			if(quotationDate.isBefore(emission.getEmissionDate().toLocalDate()) && Math.abs(ChronoUnit.DAYS.between(quotationDate, emission.getEmissionDate().toLocalDate())) > 30){
				throw new ServiceException( String.format("La fecha de cotización no debe ser anterior a 30 días naturales antes de la fecha en que se efectua la invitación :%s", emission.getEmissionDateText()));
			}
		}
		else {
			if(quotationDate.isAfter(emission.getEmissionDate().toLocalDate()) &&  Math.abs(ChronoUnit.DAYS.between(quotationDate, emission.getEmissionDate().toLocalDate())) < 5) {
				throw new ServiceException( String.format("La fecha de cotización deberá ser posterior al menos 5 días a la fecha en que se efectúa la invitación :%s", emission.getEmissionDateText()));
			}
		}
		return "";
	}
	
	
}
