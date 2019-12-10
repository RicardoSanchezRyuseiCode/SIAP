package com.ryuseicode.siap.controller.award;

import java.io.IOException;
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

import com.ryuseicode.siap.entity.award.Supplier;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.service.award.imp.SupplierService;

/**
 * @name SupplierController
 * {@summary Controller to expose endpoints of supplier }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
@Controller
public class SupplierController {
	/**
	 * FolderProperties
	 */
	@Autowired
    private FolderProperties folderProperties;
	/**
	 * Supplier serice
	 */
	@Autowired
	private SupplierService supplierService;
	/**
	 * @name Get
	 * {@summary Method to get a collection of commodity objects}
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/supplier/get", produces = "application/json")	
    public List<Supplier> get() {
		return this.supplierService.get();		
    }
	/**
	 * @name post
	 * {@summary Method to save a commodity in the database}
	 * @param commodity
	 * @param response
	 */
	@PostMapping(path = "/award/supplier/post", consumes = "application/json") 
	public void post(@RequestBody Supplier supplier, HttpServletResponse response) {
	    try {
	    	this.supplierService.save(supplier);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar proveedor: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name upload
	 * {@summary MEthod to upload a file}
	 * @param file
	 * @param response
	 */
	@PostMapping("/award/supplier/upload") 
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
			// Define path
			Path path = Paths.get(folderProperties.getUpload()  + "/"  + dateFormat.format(date) + "." + extension);
			// Get byter of file
            byte[] bytes = file.getBytes();
            // Write files to path
            Files.write(path, bytes);
            // After save the file process to save the list of goods
        } 
		catch (IOException e) {
            e.printStackTrace();
        }
	}
}
