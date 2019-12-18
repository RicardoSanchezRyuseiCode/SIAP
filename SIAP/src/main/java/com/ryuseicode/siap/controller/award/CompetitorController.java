package com.ryuseicode.siap.controller.award;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.award.CompetitorCreationParam;
import com.ryuseicode.siap.paramoutput.award.CompetitorContractStatusParamOutput;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.service.award.imp.CompetitorService;
import com.ryuseicode.siap.wrapper.award.imp.CompetitorWrapper;
/**
 * @name CompetitorController
 * {@summary Controller to expose endpoints of Competitor }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
@Controller
public class CompetitorController {
	/**
	 * CompetitorService
	 */
	@Autowired
	private CompetitorService competitorService;
	/**
	 * CompetitorWrapper
	 */
	@Autowired
	private CompetitorWrapper competitorWrapper;
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get a collection of competitor }
	 * @param adjudicationId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/competitor/getByAdjudicationId/{adjudicationId}", produces = "application/json")
	public List<CompetitorParamOutput> getByAdjudicationId(@PathVariable("adjudicationId") int adjudicationId) {
		return this.competitorService.getByAdjudicationId(adjudicationId);
	}
	/**
	 * getWinnersByAdjudicationId
	 * {@summary Method to get competitors winners for adjudication and the status of competitor}
	 * @param adjudicationId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/award/competitor/getWinnersByAdjudicationId/{adjudicationId}", produces = "application/json")
	public List<CompetitorContractStatusParamOutput> getWinnersByAdjudicationId(@PathVariable("adjudicationId") int adjudicationId) {
		return this.competitorWrapper.getWinnersByAdjudicationId(adjudicationId);
	}
	/**
	 * @name post
	 * {@summary Method to post a competitor creation param }
	 * @param competitor
	 * @param response
	 */
	@PostMapping(path = "/award/competitor/post", consumes = "application/json") 
	public void post(@RequestBody CompetitorCreationParam competitorCreationParam, HttpServletResponse response) {
	    try {
	    	this.competitorService.save(competitorCreationParam.getAdjudicationId(), competitorCreationParam.getCompetitors()); 
	    }
	    catch (ServiceException ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	    }
	    catch (Exception ex) {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar datos de participante : %s", ex.getMessage()));
	    }
	}
}
