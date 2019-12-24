package com.ryuseicode.siap.wrapper.requisition.imp;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;
import com.ryuseicode.siap.entity.requisition.Entry;
import com.ryuseicode.siap.entity.requisition.Request;
import com.ryuseicode.siap.entity.requisition.RequestDetail;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.message.EmailMessage;
import com.ryuseicode.siap.properties.DistributionProperties;
import com.ryuseicode.siap.properties.DocumentProperties;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.admin.imp.AdministrativeUnitService;
import com.ryuseicode.siap.service.requisition.imp.EntryService;
import com.ryuseicode.siap.service.requisition.imp.RequestDetailService;
import com.ryuseicode.siap.service.requisition.imp.RequestService;
import com.ryuseicode.siap.wrapper.requisition.intf.IRequestWrapper;
/**
 * @name RequestWrapper
 * {@summary Wrapper for request }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 23, 2019
 */
@Service
@Transactional
public class RequestWrapper implements IRequestWrapper {
	/**
	 * EMAIL_MESSAGE
	 */
	private static final String EMAIL_MESSAGE = "Estimado usuario <br/> Anexo a este correo encontrara el listado de bienes o servicios para la requicisión %s";
	/**
	 * Bienes
	 */
	private static final String SHEET_NAME = "Bienes";
	/**
	 * EntryService
	 */
	@Autowired
	private EntryService entryService;
	/**
	 * RequestService
	 */
	@Autowired
	private RequestService requestService;
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
	 * RequestDetailService
	 */
	@Autowired
	private RequestDetailService requestDetailService;
	/**
	 * DistributionProperties
	 */
	@Autowired
	private DistributionProperties distributionProperties;
	/**
	 * AdministrativeUnitSerivce
	 */
	@Autowired
	private AdministrativeUnitService administrativeUnitService;
	/**
	 * EmailMessage
	 */
	@Autowired
	private EmailMessage emailMessage;
	/**
	 * @name WriteDocument
	 * {@summary Method to write document }
	 * @param workbook
	 * @param code
	 * @return
	 * @throws Exception
	 */
	private String WriteDocument(HSSFWorkbook workbook, String code) throws Exception {
		// Define timestamp
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// Define directory
		String directory = String.format("%s/%s", this.folderProperties.getDocuments(), new SimpleDateFormat("yyyyMM").format(new Date()));
		// Create directory
		if(Files.notExists(Paths.get(directory)))
			Files.createDirectory(Paths.get(directory));
		// Define new name for file
		String newName = String.format("%s_%s_%s", code, timestamp, this.documentProperties.getRequisition());
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
	 * @name CreateExcelFile
	 * @param request
	 * @param requestDetails
	 * @return
	 */
	public String CreateExcelFile(Request request,List<RequestDetail> requestDetails) throws Exception {
		// Create excel document
        HSSFWorkbook workbook = new HSSFWorkbook();
		// Create sheet for excel document
        HSSFSheet firstSheet = workbook.createSheet(SHEET_NAME);
        // Create row and set columns headers
        int rowIndex = 0;
        HSSFRow row = firstSheet.createRow(rowIndex++);
        // Create header celled
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(new HSSFRichTextString("No."));
        cell = row.createCell(1);
        cell.setCellValue(new HSSFRichTextString("CANTIDAD"));
        cell = row.createCell(2);
        cell.setCellValue(new HSSFRichTextString("DESCRIPCIÓN DEL BIEN O SERVICIO"));
        cell = row.createCell(3);
        cell.setCellValue(new HSSFRichTextString("PRECIO UNITARIO"));
        // Loop in request details to create rows of files
        int number = 1;
        for(RequestDetail requestDetail : requestDetails) {
        	// Create new row
        	row = firstSheet.createRow(rowIndex++);
        	// Create cells
        	cell = row.createCell(0);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(number++)));
            cell = row.createCell(1);
            cell.setCellValue(new HSSFRichTextString(String.valueOf(requestDetail.getQuantity())));
            cell = row.createCell(2);
            cell.setCellValue(new HSSFRichTextString(requestDetail.getAsset()));
            cell = row.createCell(3);
            cell.setCellValue(new HSSFRichTextString(String.format("%.2f", requestDetail.getUnitPrice())));
        }
        return this.WriteDocument(workbook, request.getCode());
	}
	
	/**
	 * @name close
	 * {@summary Method to close request }
	 * @param requestId
	 * @throws Exception
	 */
	public void close(int requestId) throws Exception {
		// Get request
		Request request = this.requestService.getByRequestId(requestId);
		// Get entry
		Entry entry = this.entryService.getByEntryId(request.getEntryId());
		// Check if we have enought space
		if(entry.getAmountAllocated() < entry.getAmountUsed() + request.getAmount())
			throw new ServiceException(String.format("La partida %s, no cuenta con fondos suficientes (%.2f) para la creación de la requisición con monto %s ",  
					entry.getDescription(), entry.getAmountAllocated() - entry.getAmountUsed(), request.getAmount()));
		// Get details
		List<RequestDetail> requestDetails = this.requestDetailService.getByRequestId(requestId);
		// Create excel file
		String newDocumentPath = CreateExcelFile(request, requestDetails);
		// update status
		this.requestService.updateStatus(requestId, Request.STATUS_COMPLETE);
		// update close
		this.requestService.updateCloseDate(requestId, LocalDateTime.now());
		// update amount used
		this.entryService.updateAmountUsed(entry.getEntryId(), entry.getAmountUsed() + request.getAmount());
		// Send email
		ArrayList<String> attachments = new ArrayList<String>();
 		attachments.add(newDocumentPath);
 		AdministrativeUnit administrativeUnit = this.administrativeUnitService.GetByAuthorizer();
 		this.emailMessage.sendEmail(administrativeUnit.getEmail(), this.distributionProperties.getCc(), "", attachments, String.format(EMAIL_MESSAGE, request.getCode()));
	}
}
