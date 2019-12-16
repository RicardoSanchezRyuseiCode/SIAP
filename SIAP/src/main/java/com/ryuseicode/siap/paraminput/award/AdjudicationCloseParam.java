package com.ryuseicode.siap.paraminput.award;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name AdjudicationCloseParam
 * {@summary Parameter class to close adjudication }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 15, 2019
 */
public class AdjudicationCloseParam {
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * CloseDate
	 */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime closeDate;
	/**
	 * Default constructor
	 * @param adjudicationId
	 * @param closeDate
	 */
	public AdjudicationCloseParam(int adjudicationId, LocalDateTime closeDate) {
		this.setAdjudicationId(adjudicationId);
		this.setCloseDate(closeDate);
	}
	/**
	 * @return the adjudicationId
	 */
	public int getAdjudicationId() {
		return adjudicationId;
	}
	/**
	 * @param adjudicationId the adjudicationId to set
	 */
	public void setAdjudicationId(int adjudicationId) {
		this.adjudicationId = adjudicationId;
	}
	/**
	 * @return the closeDate
	 */
	public LocalDateTime getCloseDate() {
		return closeDate;
	}
	/**
	 * @param closeDate the closeDate to set
	 */
	public void setCloseDate(LocalDateTime closeDate) {
		this.closeDate = closeDate;
	}
	
}
