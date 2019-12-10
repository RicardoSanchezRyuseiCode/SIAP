package com.ryuseicode.siap.wrapper.award.imp;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.award.Asset;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.oxml.ExcelParser;
import com.ryuseicode.siap.service.award.imp.AssetService;
import com.ryuseicode.siap.wrapper.award.intf.IAssetWrapper;

/**
 * 
 * @name AssetWrapper
 * {@summary }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
@Service
@Transactional
public class AssetWrapper implements IAssetWrapper {
	/**
	 * Bienes
	 */
	private static final String SHEET_NAME = "Bienes";
	/**
	 * Name Position
	 */
	private static final int ASSET_NAME_POSITION = 2;
	/**
	 * AssetSevice
	 */
	@Autowired
	private AssetService assetService;
	/**
	 * @name Import
	 * {@summary Method to import assets from excel file }
	 * @param filePath
	 */
	public void importFile(String filePath) throws Exception {
		try {
			// Get content of file
			List<Object[]> excelContent = ExcelParser.GetMatrixContent(filePath, SHEET_NAME, true);
			// Parse the rows to goods
			ArrayList<Asset> assets = new ArrayList<Asset>();
			// Define map for distinct
			Map<String, Asset> distinct = new HashMap<String, Asset>();
			// Loop in data
			for(Object[] content : excelContent) {
				if(content[ASSET_NAME_POSITION] != null && !content[ASSET_NAME_POSITION].toString().isEmpty())
					if(!distinct.containsKey(content[ASSET_NAME_POSITION].toString()))
						distinct.put(content[ASSET_NAME_POSITION].toString(), new Asset(0, content[ASSET_NAME_POSITION].toString(), 1));
			}
			// Loop to get distinct values
			distinct.forEach((name, asset) -> {
				assets.add(asset);
			});
			// Save the collection
			this.assetService.save(assets);
			// Delete file
			Files.deleteIfExists(Paths.get(filePath));
		}	
		catch(Exception ex) {
			// Delete file
			Files.deleteIfExists(Paths.get(filePath));
			// Throw exception
			throw new ServiceException(String.format("Ha ocurrido un error en la importación de bienes: %s", ex.getMessage()));
		}
	}	
}
