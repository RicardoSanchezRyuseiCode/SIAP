package com.ryuseicode.siap.controller.award;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.award.Asset;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.AssetService;
import com.ryuseicode.siap.wrapper.award.imp.AssetWrapper;

/**
 * @name GoodsController
 * {@summary Controller to expose Goods endpoints }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 30, 2019
 */
@Controller
public class AssetController {
	/**
	 * FolderProperties
	 */
	@Autowired
    private FolderProperties folderProperties;
	/**
	 * AssetService
	 */
	@Autowired
	private AssetService assetService;
	/**
	 * AssetWrapper
	 */
	@Autowired
	private AssetWrapper assetWrapper;
    /**
	 * @name Get
	 * {@summary Method to get a collection of commodity objects}
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/asset/get", produces = "application/json")	
    public List<Asset> get() {
		return this.assetService.get();		
    }
	/**
	 * @name post
	 * {@summary Method to save a commodity in the database}
	 * @param commodity
	 * @param response
	 */
	@PostMapping(path = "/award/asset/post", consumes = "application/json") 
	public void post(@RequestBody Asset asset, HttpServletResponse response) {
	    try {
	    	this.assetService.save(asset);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar bien o servicio: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name upload
	 * {@summary MEthod to upload a file}
	 * @param file
	 * @param response
	 */
	@PostMapping("/award/asset/upload") 
    public void upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
		// Check if file is not empty
		if (file.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No ha enviado algpun archivo a procesar");
        }		
		try {
			// create timestampt
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			// Get file extension
			String extension = FilenameUtils.getExtension(file.getOriginalFilename());
			// Define string
			String pathStr = folderProperties.getUpload()  + "/"  + dateFormat.format(date) + "." + extension;
			// Define path
			Path path = Paths.get(pathStr);
			// Get byter of file
            byte[] bytes = file.getBytes();
            // Write files to path
            Files.write(path, bytes);
            // After save the file process to save the list of goods
            this.assetWrapper.importFile(pathStr);
        } 
		catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar bien o servicio: %s", ex.getMessage()));
	    }
	}
}
