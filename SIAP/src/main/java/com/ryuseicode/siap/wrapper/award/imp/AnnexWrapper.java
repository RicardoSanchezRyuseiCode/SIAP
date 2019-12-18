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
import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.entity.award.Asset;
import com.ryuseicode.siap.entity.award.Institution;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.oxml.ExcelParser;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;
import com.ryuseicode.siap.properties.DocumentProperties;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AdjudicationDocumentService;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.AdjudicationStepService;
import com.ryuseicode.siap.service.award.imp.AnnexService;
import com.ryuseicode.siap.service.award.imp.AssetService;
import com.ryuseicode.siap.service.award.imp.InstitutionService;
import com.ryuseicode.siap.wrapper.award.intf.IAnnexWrapper;
/**
 * @name AnnexWrapper
 * {@summary Wrapper to implement the behavior of IAnnexWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
@Service
@Transactional
public class AnnexWrapper extends WordWrapper implements IAnnexWrapper  {
	/**
	 * IDENTIFIER
	 */
	private static final String IDENTIFIER = "ANNEX";
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
	private static final int ASSET_QUANTITY_POSITION = 1;
	/**
	 * AnnexService
	 */
	@Autowired
	private AnnexService annexService;
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
	 * assetService
	 */
	@Autowired
	private AssetService assetService;
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
	 * @name CreateDocument
	 * {@summary Method to create the new document }
	 * @param document
	 * @param adjudicationId
	 * @return
	 * @throws Exception
	 */
	private String CreateDocument(XWPFDocument document, int adjudicationId) throws Exception {
		// Define timestamp
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// Define directory
		String directory = String.format("%s/%s", this.folderProperties.getDocuments(), new SimpleDateFormat("yyyyMM").format(new Date()));
		// Create directory
		if(Files.notExists(Paths.get(directory)))
			Files.createDirectory(Paths.get(directory));
		// Define new name for file
		String newName = String.format("%s_%s_%s", String.valueOf(adjudicationId), timestamp, this.documentProperties.getAnnex());
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
	 * @name Create
	 * {@summary Method to create a list of annex and generate the document }
	 * @param adjudicationId
	 * @param annexs
	 */
	public String create(int adjudicationId, List<Annex> annexs) throws Exception {
		// Save annex
		this.annexService.Save(adjudicationId, annexs);
		// Get adjudication information
		Adjudication adjudication = this.adjudicationService.getById(adjudicationId);
		// Get institution data
		Institution institution = this.institutionService.get().get(0);
		// Get assets
		List<Asset> assets = this.assetService.get();
		// Create asset dictionary
		Map<Integer, Asset> dicAsset = new HashMap<Integer, Asset>();
		for(Asset asset : assets) {
			if(!dicAsset.containsKey(asset.getAssetId()))
				dicAsset.put(asset.getAssetId(), asset);
		}
		// Create document
		XWPFDocument annexDocument = new XWPFDocument();
		// Create paragraph
		XWPFParagraph paragraphHeader = annexDocument.createParagraph(); 
		paragraphHeader.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun runParagraphHeader = paragraphHeader.createRun();
        // Set text to paragraph
        runParagraphHeader.setText(String.format("ANEXO UNO DEL PROCEDIMIENTO DE ADQUISCIÓN %s NÚMERO %s CELEBRADO POR EL ORGANISMO PÚBLICO DESCENTRALIZADO DENOMINADO %s", 
        		adjudication.getAdjudicationType(), adjudication.getProcedureNumber(), institution.getName()
		));
        // Create table
        XWPFTable tableBody = annexDocument.createTable();
        // Create table header
        XWPFTableRow tableBodyHeader = tableBody.getRow(0);
        tableBodyHeader.getCell(0).setText("Renglón");
        tableBodyHeader.addNewTableCell().setText("Descripción");
        tableBodyHeader.addNewTableCell().setText("Cantidad");
        tableBodyHeader.addNewTableCell().setText("Plazo entrega");
        tableBodyHeader.addNewTableCell().setText("Lugar entrega");
        // Loop in annex to add rows to document
        int row = 1;
        for(Annex annex : annexs) {	
        	XWPFTableRow tableRow = tableBody.createRow();
        	tableRow.getCell(0).setText(String.valueOf(row));
        	tableRow.getCell(1).setText(dicAsset.get(annex.getAssetId()).getName());
        	tableRow.getCell(2).setText(String.format("%.2f", annex.getQuantity()));
        	tableRow.getCell(3).setText(annex.getDeliveryTerm());
        	tableRow.getCell(4).setText(annex.getDeliveryPlace());
        	row++;
        }
        // Add paragraph space
        XWPFParagraph paragraphSpace = annexDocument.createParagraph();
        paragraphSpace.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun runParagraphSpace = paragraphSpace.createRun();
        runParagraphSpace.setText(" ");
        // Create signatures table
        XWPFTable tableSignatures= annexDocument.createTable();
        //create first row
        XWPFTableRow tableSignaturesRowOne = tableSignatures.getRow(0);
        tableSignaturesRowOne.getCell(0).setText("PUESTO");
        tableSignaturesRowOne.addNewTableCell().setText("FIRMA");
        //create second row
        XWPFTableRow tableSignaturesRowTwo = tableSignatures.createRow();
        tableSignaturesRowTwo.getCell(0).setText(institution.getRepresentative());
        tableSignaturesRowTwo.getCell(1).setText(" ");
        //create third row
        XWPFTableRow tableSignaturesRowThree = tableSignatures.createRow();
        tableSignaturesRowThree.getCell(0).setText("Responsable del procedimiento de adjudicación");
        tableSignaturesRowThree.getCell(1).setText(" ");
		// Create document
        String pathDocument = CreateDocument(annexDocument,adjudication.getAdjudicationId());
		// Save the adjudication document
 		this.adjudicationDocumentService.Save(new AdjudicationDocument(0, adjudication.getAdjudicationId(), IDENTIFIER, new File(pathDocument).getName(), pathDocument) );
 		// Save step
 		this.adjudicationStepService.Save(new AdjudicationStep(0, adjudication.getAdjudicationId(), IDENTIFIER, LocalDateTime.now()));
 		// Update status
 		this.adjudicationService.updateStatus(adjudication.getAdjudicationId(), AdjudicationService.STATUS_OPENING);
        // Return Path
		return new File(pathDocument).getName();
	}
	/**
	 * @name Import
	 * {@summary Method to import data to return client }
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	public List<AnnexParamOutput> importFile(String filePath) throws Exception{
		try {
			// Get current assets
			List<Asset> assets = this.assetService.get();
			// Define dictionaty for assets
			Map<String, Asset> dicAssets = new HashMap<String, Asset>();
			for(Asset asset : assets) {
				if(!dicAssets.containsKey(asset.getName().toLowerCase()));
					dicAssets.put(asset.getName().toLowerCase(), asset);
			}
			// Define distinct dictionary
			Map<Integer, AnnexParamOutput> distinct = new HashMap<Integer, AnnexParamOutput>();
			// Get content of file
			List<Object[]> excelContent = ExcelParser.GetMatrixContent(filePath, SHEET_NAME, true);
			// Loop in excel content
			for(Object[] content : excelContent) {
				if(content[ASSET_QUANTITY_POSITION] != null && !content[ASSET_QUANTITY_POSITION].toString().isEmpty() &&  content[ASSET_NAME_POSITION] != null && !content[ASSET_NAME_POSITION].toString().isEmpty()) {
					// Check if dictionary contains asset, if asset not exist will be ignored in the import
					if(dicAssets.containsKey(content[ASSET_NAME_POSITION].toString().toLowerCase())) {
						// Get asset from dictionary by name
						Asset asset = dicAssets.get(content[ASSET_NAME_POSITION].toString().toLowerCase());
						// Check if dictionary contains the current asset
						if(!distinct.containsKey(asset.getAssetId()))
							distinct.put(asset.getAssetId(), new AnnexParamOutput(new Annex(0, 0, asset.getAssetId(), Double.parseDouble(content[ASSET_QUANTITY_POSITION].toString()), "", "", 1), asset));
					}
				}
			}
			// Define result list
			ArrayList<AnnexParamOutput> annexParamOutputs = new ArrayList<AnnexParamOutput>();
			// Loop to get distinct values
			distinct.forEach((assetId, annexParamOutput) -> {
				annexParamOutputs.add(annexParamOutput);
			});
			// Delete file
			Files.deleteIfExists(Paths.get(filePath));
			// Return the result
			return annexParamOutputs;
		}
		catch(Exception ex) {
			// Delete file
			Files.deleteIfExists(Paths.get(filePath));
			// Throw exception
			throw new ServiceException(String.format("Ha ocurrido un error en la importación de anexos: %s", ex.getMessage()));
		}
	} 
}