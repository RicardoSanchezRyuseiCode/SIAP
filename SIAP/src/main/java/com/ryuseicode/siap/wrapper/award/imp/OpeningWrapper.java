package com.ryuseicode.siap.wrapper.award.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.award.AdjudicationStep;
import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.entity.award.Opening;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.service.award.imp.AdjudicationService;
import com.ryuseicode.siap.service.award.imp.AdjudicationStepService;
import com.ryuseicode.siap.service.award.imp.EmissionService;
import com.ryuseicode.siap.service.award.imp.OpeningService;
import com.ryuseicode.siap.wrapper.award.intf.IOpeningWrapper;
/**
 * @name @name OpeningWrapper
 * {@summary Wrapper class for OpeningWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
@Service
@Transactional
public class OpeningWrapper implements IOpeningWrapper {
	/**
	 * Identifier
	 */
	private static final String IDENTIFIER = "OPENING";
	/**
	 * AdjudicationStepService
	 */
	@Autowired
	private AdjudicationService adjudicationService;
	/**
	 * AdjudicationStepService
	 */
	@Autowired
	private AdjudicationStepService adjudicationStepService;
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
	 * @name Save
	 * {@summary Method to save an opening object }
	 * @param opening
	 * @return
	 */
	public void save(Opening opening) throws Exception {
		// Get emission data
		Emission emission = this.emissionService.getByAdjudicationId(opening.getAdjudicationId());
		// with emission data validate the date of emission with the date of event 
		if(ChronoUnit.DAYS.between(emission.getEmissionDate().toLocalDate(), opening.getEventDate()) < 5)
			throw new ServiceException(String.format("Fecha en que se realiza el evento no puede ser menor a 5 días de la fecha de la invitación: %s", DateTimeFormatter.ofPattern("dd-MM-yyyy").format(emission.getEmissionDate())));
		// Compare the event date with failure issuance date
		if(opening.getFailureIssuanceDate().isBefore(opening.getEventDate()))
			throw new ServiceException(String.format("La fecha de emisión del fallo debe ser posterior a la fecha en que se realiza el evento: %s", DateTimeFormatter.ofPattern("dd-MM-yyyy").format(opening.getEventDate())));
		// Compare issuance date must be less than 20 days
		if(ChronoUnit.DAYS.between( opening.getEventDate(), opening.getFailureIssuanceDate()) > 20)
			throw new ServiceException(String.format("La fecha de emisión del fallo no debe ser posterior a 20 dias naturales despues de la fecha en que se realiza el evento: %s", DateTimeFormatter.ofPattern("dd-MM-yyyy").format(opening.getEventDate())));
		// Compare hours
		if(opening.getEventEndHour().isBefore(opening.getEventStartHour()))
			throw new ServiceException(String.format("La hora en que finaliza el evento debe ser posterior a la hora de inicio del evento"));
		// if all is ok save the opening
		this.openingService.save(opening);
		// Save step
		this.adjudicationStepService.Save(new AdjudicationStep(0, opening.getAdjudicationId(), IDENTIFIER, LocalDateTime.now()));
		// Update status
		this.adjudicationService.updateStatus(opening.getAdjudicationId(), AdjudicationService.STATUS_DETAIL);
	}	
}