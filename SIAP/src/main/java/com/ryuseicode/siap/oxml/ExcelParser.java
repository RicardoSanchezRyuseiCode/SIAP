package com.ryuseicode.siap.oxml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @name ExcelParser
 * {@summary Parser class to get content from excel files }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public class ExcelParser {
	/**
	 * @name IsEmpty
	 * {@abstract Method to check if content of row is empty}
	 * @param rowContent
	 * @return
	 */
	private static boolean IsEmpty(Object[] rowContent) {
		boolean result = false;
		int count = 0;
		for(Object cell : rowContent) {
			if(cell == null || cell.toString().isEmpty())
				count++;	
		}
		if(count == rowContent.length)
			result = true;
		return result;
	}
	/**
	 * @name GetMatrixContent
	 * @abstract Method to get the content of excel file with a matrix structure, first column need to be in the column A
	 * @param excelFilePath File system path of file
	 * @param headerFirstRow True if first row have the header
	 * @return Collection with content of each row in an object array
	 * @throws FileNotFoundException 
	 */
	public static List<Object[]> GetMatrixContent(String excelFilePath, String sheetName, boolean headerFirstRow) throws Exception {
		// Define filestream
		FileInputStream fileStream;
		try {
			// Open file to get content
			fileStream = new FileInputStream(excelFilePath);
		}
		catch(Exception ex) {
			throw ex;
		}
		XSSFWorkbook workbook;
		try {
			// Create excel reader
			workbook = new XSSFWorkbook(fileStream);
		}
		catch(Exception ex) {
			// Close file
			fileStream.close();
			// throw exception
			throw ex;
		}
		try {
			// Get the sheet from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// Get firs row of file with content
			int startRow = sheet.getFirstRowNum();
			// Get last row of file with content
			int endRow = sheet.getLastRowNum();
			// Get first column
			int startColumn = sheet.getRow(startRow).getFirstCellNum();
			// Get last column
			int endColumn = sheet.getRow(startRow).getLastCellNum();
			// Define array zie
			int arraySize = endColumn - startColumn;
			// Defiine result
			ArrayList<Object[]> result = new ArrayList<Object[]>();
			// Check if we have header
			if(headerFirstRow)
				startRow += 1;
			// Loop to get the data
			for(int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
				// Define collection
				Object[] rowContent = new Object[arraySize];
				// Define row
				XSSFRow row = sheet.getRow(rowIndex);
				// Define index
				int rowContentIndex = 0;
				// Loop in columns
				for(int columnIndex = startColumn; columnIndex < endColumn; columnIndex++) {
					// Get column cell in row
					XSSFCell cell = row.getCell(columnIndex);
					// Check if cell have value or is null
					if(cell != null)
						// Set content of cell in array
						rowContent[rowContentIndex++] = cell.toString().trim();
					else 
						rowContent[rowContentIndex++] = "";
				}
				// Check if array is empty
				if(!IsEmpty(rowContent))
					// Add object array to result
					result.add(rowContent);
			}
			// Close workbook
			workbook.close();
			// Close file
			fileStream.close();
			// return result
			return result;
		}
		catch(Exception ex) {
			// Close workbook
			workbook.close();
			// Close file
			fileStream.close();
			// throw exception
			throw ex;
		}
	}
}
