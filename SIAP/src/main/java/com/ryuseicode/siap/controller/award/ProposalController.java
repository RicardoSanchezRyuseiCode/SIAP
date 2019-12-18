package com.ryuseicode.siap.controller.award;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.award.ListProposalCreationParam;
import com.ryuseicode.siap.properties.FolderProperties;
import com.ryuseicode.siap.wrapper.award.imp.ProposalWrapper;
/**
 * @name ProposalController
 * {@summary Controller class for proposal }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
@Controller
public class ProposalController {
	/**
	 * ProposalWrapper
	 */
	@Autowired
	private ProposalWrapper proposalWrapper;
	/**
	 * folderProperties
	 */
	@Autowired
	private FolderProperties folderProperties;
	/**
	 * @name create
	 * {@summary Method to create proposal with items }
	 * @param listProposalCreationParams
	 * @param response
	 * @return
	 */
	@PostMapping(path = "/award/proposal/create", consumes = "application/json") 
	public ResponseEntity<Object> create(@RequestBody ListProposalCreationParam  listProposalCreationParams, HttpServletResponse response) {
		try {
	    	return new ResponseEntity<Object>(this.proposalWrapper.create(listProposalCreationParams) , HttpStatus.OK);
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al crear adjudicación: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name upload
	 * {@summary Method to upload file to import unit price }
	 * @param file
	 * @param response
	 * @return
	 */
	@PostMapping("/award/proposal/upload/{adjudicationId}") 
    public ResponseEntity<Object> upload(@PathVariable("adjudicationId") int adjudicationId, @RequestParam("file") MultipartFile file, HttpServletResponse response) {
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
            return new ResponseEntity<Object>(this.proposalWrapper.importFile(adjudicationId, pathStr), HttpStatus.OK);
        } 
		catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al importar precios: %s", ex.getMessage()));
	    }
	}
}