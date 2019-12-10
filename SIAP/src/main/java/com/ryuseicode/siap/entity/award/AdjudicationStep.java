package com.ryuseicode.siap.entity.award;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name AdjudicationStep
 * {@summary Entity class to model AdjudicationStep }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public class AdjudicationStep {
	/**
	 * adjudicationStepId
	 */
	private int adjudicationStepId;
	/**
	 * adjudicationId
	 */
	private int adjudicationId;
	/**
	 * step
	 */
	private String step;
	/**
	 * LocalDateTime
	 */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	/**
	 * Default constructor
	 * @param adjudicationStepId
	 * @param adjudicationId
	 * @param step
	 * @param creationDate
	 */
	public AdjudicationStep(int adjudicationStepId, int adjudicationId, String step, LocalDateTime creationDate) {
		this.setAdjudicationStepId(adjudicationStepId);
		this.setAdjudicationId(adjudicationId);
		this.setStep(step);
		this.setCreationDate(creationDate);
	}
	/**
	 * @return the adjudicationStepId
	 */
	public int getAdjudicationStepId() {
		return adjudicationStepId;
	}
	/**
	 * @param adjudicationStepId the adjudicationStepId to set
	 */
	public void setAdjudicationStepId(int adjudicationStepId) {
		this.adjudicationStepId = adjudicationStepId;
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
	 * @return the step
	 */
	public String getStep() {
		return step;
	}
	/**
	 * @param step the step to set
	 */
	public void setStep(String step) {
		this.step = step;
	}
	/**
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
}
