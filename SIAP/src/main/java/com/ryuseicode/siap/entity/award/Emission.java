package com.ryuseicode.siap.entity.award;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name Emission
 * {@summary Entity class to model emission object }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
public class Emission {
	/**
	 * EmissionId
	 */
	private int emissionId;
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * Place
	 */
	private String place;
	/**
	 * LocalDateTime
	 */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime emissionDate;
	/**
	 * EmissionDateText
	 */
	private String emissionDateText;
	/**
	 * DayForPayment
	 */
	private int daysForPayment;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default constructor
	 * @param emissionId
	 * @param adjudicationId
	 * @param place
	 * @param emissionDate
	 * @param daysForPaymet
	 * @param active
	 */
	public Emission(int emissionId, int adjudicationId, String place, LocalDateTime emissionDate, String emissionDateText, int daysForPaymet, int active) {
		this.setEmissionId(emissionId);
		this.setAdjudicationId(adjudicationId);
		this.setPlace(place);
		this.setEmissionDate(emissionDate);
		this.setEmissionDateText(emissionDateText);
		this.setDaysForPayment(daysForPaymet);
		this.setActive(active);
	}
	/**
	 * @return the emissionId
	 */
	public int getEmissionId() {
		return emissionId;
	}
	/**
	 * @param emissionId the emissionId to set
	 */
	public void setEmissionId(int emissionId) {
		this.emissionId = emissionId;
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
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the emissionDate
	 */
	public LocalDateTime getEmissionDate() {
		return emissionDate;
	}
	/**
	 * @param emissionDate the emissionDate to set
	 */
	public void setEmissionDate(LocalDateTime emissionDate) {
		this.emissionDate = emissionDate;
	}
	/**
	 * @return the emissionDateText
	 */
	public String getEmissionDateText() {
		return emissionDateText;
	}
	/**
	 * @param emissionDateText the emissionDateText to set
	 */
	public void setEmissionDateText(String emissionDateText) {
		this.emissionDateText = emissionDateText;
	}
	/**
	 * @return the daysForPayment
	 */
	public int getDaysForPayment() {
		return daysForPayment;
	}
	/**
	 * @param daysForPayment the daysForPayment to set
	 */
	public void setDaysForPayment(int daysForPayment) {
		this.daysForPayment = daysForPayment;
	}
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}	
}
