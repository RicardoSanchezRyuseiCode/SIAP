package com.ryuseicode.siap.entity.award;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name Opening
 * {@summary Entity class to model opening }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
public class Opening {
	/**
	 * OpeningId
	 */
	private int openingId;
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * Denomination
	 */
	private String denomination;
	/**
	 * EventDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate eventDate;
	/**
	 * EventDateText
	 */
	private String eventDateText;
	/**
	 * EventStartHour
	 */
	@DateTimeFormat(iso = ISO.TIME)
	@JsonFormat(pattern="HH:mm")
	private LocalTime eventStartHour;
	/**
	 * EventStartHourText
	 */
	private String eventStartHourText;
	/**
	 * EventEndHour
	 */
	@DateTimeFormat(iso = ISO.TIME)
	@JsonFormat(pattern="HH:mm")
	private LocalTime eventEndHour;
	/**
	 * EventEndHourText
	 */
	private String eventEndHourText;
	/**
	 * Reponsable
	 */
	private String reponsable;
	/**
	 * FirstWitness
	 */
	private String firstWitness;
	/**
	 * SecondWitness
	 */
	private String secondWitness;
	/**
	 * SupplierId
	 */
	private int supplierId;
	/**
	 * FailureIssuanceDate
	 */
	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate failureIssuanceDate;
	/**
	 * FailureIssuanceDateText
	 */
	private String failureIssuanceDateText;
	/**
	 * FailureIssuanceHour
	 */
	@DateTimeFormat(iso = ISO.TIME)
	@JsonFormat(pattern="HH:mm")
	private LocalTime failureIssuanceHour;
	/**
	 * FailureIssuanceHourText
	 */
	private String failureIssuanceHourText;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default constructor
	 * @param openingId
	 * @param adjudicationId
	 * @param denomination
	 * @param eventDate
	 * @param eventDateText
	 * @param eventStartHour
	 * @param eventStartHourText
	 * @param eventEndHour
	 * @param eventEndHourText
	 * @param responsable
	 * @param firstWitness
	 * @param secondWitness
	 * @param failureIssuanceDate
	 * @param failureIssuanceDateText
	 * @param failureIssuanceHour
	 * @param failureIssuanceHourText
	 */
	public Opening(int openingId, int adjudicationId, String denomination, LocalDate eventDate, String eventDateText, LocalTime eventStartHour, String eventStartHourText, LocalTime eventEndHour, String eventEndHourText,
			       String responsable, String firstWitness, String secondWitness, int supplierId, LocalDate failureIssuanceDate, String  failureIssuanceDateText, LocalTime failureIssuanceHour, String failureIssuanceHourText, int active) {
		this.setOpeningId(openingId);
		this.setAdjudicationId(adjudicationId);
		this.setDenomination(denomination);
		this.setEventDate(eventDate);
		this.setEventDateText(eventDateText);
		this.setEventStartHour(eventStartHour);
		this.setEventStartHourText(eventStartHourText);
		this.setEventEndHour(eventEndHour);
		this.setEventEndHourText(eventEndHourText);
		this.setReponsable(responsable);
		this.setFirstWitness(firstWitness);
		this.setSecondWitness(secondWitness);
		this.setSupplierId(supplierId);
		this.setFailureIssuanceDate(failureIssuanceDate);
		this.setFailureIssuanceDateText(failureIssuanceDateText);
		this.setFailureIssuanceHour(failureIssuanceHour);
		this.setFailureIssuanceHourText(failureIssuanceHourText);
		this.setActive(active);
	}
	/**
	 * @return the openingId
	 */
	public int getOpeningId() {
		return openingId;
	}
	/**
	 * @param openingId the openingId to set
	 */
	public void setOpeningId(int openingId) {
		this.openingId = openingId;
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
	 * @return the denomination
	 */
	public String getDenomination() {
		return denomination;
	}
	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}
	/**
	 * @return the eventDate
	 */
	public LocalDate getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return the eventDateText
	 */
	public String getEventDateText() {
		return eventDateText;
	}
	/**
	 * @param eventDateText the eventDateText to set
	 */
	public void setEventDateText(String eventDateText) {
		this.eventDateText = eventDateText;
	}
	/**
	 * @return the eventStartHour
	 */
	public LocalTime getEventStartHour() {
		return eventStartHour;
	}
	/**
	 * @param eventStartHour the eventStartHour to set
	 */
	public void setEventStartHour(LocalTime eventStartHour) {
		this.eventStartHour = eventStartHour;
	}
	/**
	 * @return the eventStartHourText
	 */
	public String getEventStartHourText() {
		return eventStartHourText;
	}
	/**
	 * @param eventStartHourText the eventStartHourText to set
	 */
	public void setEventStartHourText(String eventStartHourText) {
		this.eventStartHourText = eventStartHourText;
	}
	/**
	 * @return the eventEndHour
	 */
	public LocalTime getEventEndHour() {
		return eventEndHour;
	}
	/**
	 * @param eventEndHour the eventEndHour to set
	 */
	public void setEventEndHour(LocalTime eventEndHour) {
		this.eventEndHour = eventEndHour;
	}
	/**
	 * @return the eventEndHourText
	 */
	public String getEventEndHourText() {
		return eventEndHourText;
	}
	/**
	 * @param eventEndHourText the eventEndHourText to set
	 */
	public void setEventEndHourText(String eventEndHourText) {
		this.eventEndHourText = eventEndHourText;
	}
	/**
	 * @return the reponsable
	 */
	public String getReponsable() {
		return reponsable;
	}
	/**
	 * @param reponsable the reponsable to set
	 */
	public void setReponsable(String reponsable) {
		this.reponsable = reponsable;
	}
	/**
	 * @return the firstWitness
	 */
	public String getFirstWitness() {
		return firstWitness;
	}
	/**
	 * @param firstWitness the firstWitness to set
	 */
	public void setFirstWitness(String firstWitness) {
		this.firstWitness = firstWitness;
	}
	/**
	 * @return the secondWitness
	 */
	public String getSecondWitness() {
		return secondWitness;
	}
	/**
	 * @param secondWitness the secondWitness to set
	 */
	public void setSecondWitness(String secondWitness) {
		this.secondWitness = secondWitness;
	}
	/**
	 * @return the failureIssuanceDate
	 */
	public LocalDate getFailureIssuanceDate() {
		return failureIssuanceDate;
	}
	/**
	 * @param failureIssuanceDate the failureIssuanceDate to set
	 */
	public void setFailureIssuanceDate(LocalDate failureIssuanceDate) {
		this.failureIssuanceDate = failureIssuanceDate;
	}
	/**
	 * @return the supplierId
	 */
	public int getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * @return the failureIssuanceDateText
	 */
	public String getFailureIssuanceDateText() {
		return failureIssuanceDateText;
	}
	/**
	 * @param failureIssuanceDateText the failureIssuanceDateText to set
	 */
	public void setFailureIssuanceDateText(String failureIssuanceDateText) {
		this.failureIssuanceDateText = failureIssuanceDateText;
	}
	/**
	 * @return the failureIssuanceHour
	 */
	public LocalTime getFailureIssuanceHour() {
		return failureIssuanceHour;
	}
	/**
	 * @param failureIssuanceHour the failureIssuanceHour to set
	 */
	public void setFailureIssuanceHour(LocalTime failureIssuanceHour) {
		this.failureIssuanceHour = failureIssuanceHour;
	}
	/**
	 * @return the failureIssuanceHourText
	 */
	public String getFailureIssuanceHourText() {
		return failureIssuanceHourText;
	}
	/**
	 * @param failureIssuanceHourText the failureIssuanceHourText to set
	 */
	public void setFailureIssuanceHourText(String failureIssuanceHourText) {
		this.failureIssuanceHourText = failureIssuanceHourText;
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
