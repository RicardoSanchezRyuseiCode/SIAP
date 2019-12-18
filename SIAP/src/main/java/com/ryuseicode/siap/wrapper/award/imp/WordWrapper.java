package com.ryuseicode.siap.wrapper.award.imp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.ryuseicode.siap.entity.award.DocumentVariable;
/**
 * @name DocumentWrapper
 * {@summary Wrapper class to process word documents }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public class WordWrapper {
	/**
	 * RETURN_TYPE_STRING
	 */
	private static final String RETURN_TYPE_STRING = "String";
	/**
	 * RETURN_TYPE_INT
	 */
	private static final String RETURN_TYPE_INT = "int";
	/**
	 * RETURN_TYPE_DOUBLE
	 */
	private static final String RETURN_TYPE_DOUBLE = "double";
	/**
	 * RETURN_TYPE_LOCALDATE_TIME
	 */
	private static final String RETURN_TYPE_LOCALDATETIME = "LocalDateTime";
	/**
	 * Default value
	 */
	private static final String DEFAULT_VALUE = "NO DISPONIBLE";
	/**
	 * Default constructor
	 */
	public WordWrapper() { }
	/**
	 * @name ReplaceString
	 * {@summary Method to replace String }
	 * @param text
	 * @param variable
	 * @param value
	 * @return
	 */
	private String ReplaceString(String text, String variable, Object value) {
		// Cast value to string
		String newValue = (String)value;
		// Check if is empty
		if(newValue == null || newValue.isEmpty())
			newValue = DEFAULT_VALUE;
		// Replace value
		return text.replace(variable, newValue);
	}
	/**
	 * @name ReplaceInt
	 * {@summary Method to replace Int value }
	 * @param text
	 * @param variable
	 * @param value
	 * @return
	 */
	private String ReplaceInt(String text, String variable, Object value) {
		// Cast value to int
		int newValue = (int)value;
		// Replace value
		return text.replace(variable, String.valueOf(newValue));
	}
	/**
	 * @name ReplaceDouble
	 * @param text
	 * @param variable
	 * @param value
	 * @return
	 */
	private String ReplaceDouble(String text, String variable, Object value) {
		// Cast value to int
		double newValue = (double)value;
		// Replace value
		return text.replace(variable, String.format("%.2f",newValue));
	}
	/**
	 * @name ReplaceLocalDateTime
	 * @param text
	 * @param variable
	 * @param value
	 * @return
	 */
	private String ReplaceLocalDateTime(String text, String variable, Object value) {
		// Cast value to int
		LocalDateTime newValue = (LocalDateTime)value;
		// Define formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		// Replace value
		return text.replace(variable, newValue.format(formatter));
	}
	/**
	 * Method to set document variables
	 * @param text
	 * @param documentVariables
	 * @param mapSources
	 * @return
	 * @throws Exception
	 */
	private String SetDocumentVariables(String text, List<DocumentVariable> documentVariables, Map<String, Object> mapSources) throws Exception {
		// Define textResult
		String textResult = text;
		// Loop in variables
		for(DocumentVariable documentVariable : documentVariables)
		{
			// Check if text contains variable
			if(text.toLowerCase().trim().contains(documentVariable.getVariable().toLowerCase()) || text.equals(documentVariable.getVariable())) {
				// if document contains variable, check if we have the type
				if(mapSources.containsKey(documentVariable.getClassName())) {
					// Get method definition
					Method method;
					try {
						// if we have the class get the methos
						method = mapSources.get(documentVariable.getClassName()).getClass().getMethod(documentVariable.getMethodName());
					}
					catch(NoSuchMethodException noSuchMethodException) {
						continue;
					}
					// Invoke method
					Object invokeResult = method.invoke(mapSources.get(documentVariable.getClassName()));
					// Check return type to format and replace in text
					switch(documentVariable.getReturnType())
					{
						case RETURN_TYPE_STRING:
							textResult = ReplaceString(text,documentVariable.getVariable(), invokeResult);
							break;
						case RETURN_TYPE_INT:
							textResult = ReplaceInt(text,documentVariable.getVariable(), invokeResult);
							break;
						case RETURN_TYPE_DOUBLE:
							textResult = ReplaceDouble(text,documentVariable.getVariable(), invokeResult);
							break;
						case RETURN_TYPE_LOCALDATETIME:
							textResult = ReplaceLocalDateTime(text,documentVariable.getVariable(), invokeResult);
							break;
					}	
				}
			}
		}
		return textResult;
	}
	/**
	 * @name ProcessDocument
	 * @param documentPath
	 * @param documentVariables
	 * @param documentSources
	 * @throws Exception 
	 */
	protected void ProcessDocument(String documentPath, List<DocumentVariable> documentVariables, Object[] documentSources) throws Exception {
		// Open file
		FileInputStream fileInputStream = new FileInputStream(documentPath);
		// Open word document
		XWPFDocument document = null;
		try {
			document = new XWPFDocument(fileInputStream);
		}
		catch(Exception ex) {
			// Close file input stream
			fileInputStream.close();	
		}
		// Close file
		fileInputStream.close();
		try 
		{
			// Create dictionary of document sources
			Map<String, Object> mapSources = new HashMap<String, Object>();
			for(Object object : documentSources) {
				if(!mapSources.containsKey(object.getClass().getName()))
					mapSources.put(object.getClass().getName(), object);
			}
			// Loop in document paragrahps
			for (XWPFParagraph paragraph : document.getParagraphs()) {
                List<XWPFRun> runs = paragraph.getRuns();
                if (runs != null) {
                    for (XWPFRun run : runs) {
                    	String text = run.getText(0);
                        if (text != null) {
                            text = SetDocumentVariables(text, documentVariables, mapSources);
                        }
                        run.setText(text, 0);
                    }
                }
            }
			
			// Loop in tables of document
			for (XWPFTable tbl : document.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                   for (XWPFTableCell cell : row.getTableCells()) {
                      for (XWPFParagraph paragraph : cell.getParagraphs()) {
                         for (XWPFRun r : paragraph.getRuns()) {
                           String text = r.getText(0);
                           if (text != null) {
                               text = SetDocumentVariables(text, documentVariables, mapSources);
                           }
                           r.setText(text,0);
                         }
                      }
                   }
                }
             }
			// Define file output stream
			FileOutputStream fileOutputStream = new FileOutputStream(documentPath, false);
			// Write document
			document.write(fileOutputStream);
			// Commit changes to document
			document.close();
			// close file output stream
			fileOutputStream.close();
		}
		catch(Exception ex) {
			// Commit changes to document
			document.close();
			// Close file input stream
			fileInputStream.close();	
		}
	} 
	/**
	 * @name createParagraph
	 * {@summary Method to create paragraph }
	 * @param document
	 * @param paragraphAlignment
	 * @param text
	 */
	public void createParagraph(XWPFDocument document, ParagraphAlignment paragraphAlignment, String text) {
		// Create paragraph
		XWPFParagraph paragraph = document.createParagraph();
		// Set alignment
		paragraph.setAlignment(paragraphAlignment);
		// Create run
        XWPFRun run = paragraph.createRun();
        // Set text
        run.setText(text);
	}	
	/**
	 * @name setRowCotent
	 * {@summary Method to set row content }
	 * @param rowContent
	 * @param tableRow
	 */
	private void setRowHeaderCotent(List<String> rowContent, XWPFTableRow tableRow) {
		int index = 0;
		for(String columnContent: rowContent) {
			if(index == 0) {
				tableRow.getCell(0).setText(columnContent);
			}
			else {
				tableRow.addNewTableCell().setText(columnContent);
			}
			index++;
		}
	}
	/**
	 * @name setRowCotent
	 * {@summary Method to set row content }
	 * @param rowContent
	 * @param tableRow
	 */
	private void setRowCotent(List<String> rowContent, XWPFTableRow tableRow) {
		int index = 0;
		for(String columnContent: rowContent) {
			tableRow.getCell(index).setText(columnContent);
			index++;
		}
	}
	/**
	 * @name createTable
	 * {@summary Method to create table }
	 * @param document
	 * @param tableHeaders
	 * @param tableBody
	 */
	public void createTable(XWPFDocument document, List<String> tableHeaders, List<List<String>> tableBody) {
		// Create table
		XWPFTable table = document.createTable();
		// Get first row
		XWPFTableRow tableHeaderRow = table.getRow(0);
		// Create headers
		this.setRowHeaderCotent(tableHeaders, tableHeaderRow);
		// Loop in table body to add rows
		for(List<String> rowContent : tableBody) {
			// Create row
			XWPFTableRow tableRow = table.createRow();
			// Loop in columng
			this.setRowCotent(rowContent, tableRow);
		}
	}
}