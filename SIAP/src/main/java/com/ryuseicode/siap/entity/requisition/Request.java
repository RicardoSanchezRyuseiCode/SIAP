package com.ryuseicode.siap.entity.requisition;

import java.time.LocalDateTime;

/**
 * @name Request
 * {@summary Entity class to model Request }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 21, 2019
 */
public class Request {
	/**
	 * Status In Process
	 */
	public final static String STATUS_IN_PROCESS = "En proceso";
	/**
	 * Completa
	 */
	public final static String STATUS_COMPLETE = "Completa";
	/**
	 * RequestId
	 */
	private int requestId;
	/**
	 * EntryId
	 */
	private int entryId;
	/**
	 * Code
	 */
	private String code;
	/**
	 * Status
	 */
	private String status;
	/**
	 * Amount
	 */
	private double amount;
	/**
	 * CreationDate
	 */
	private LocalDateTime creationDate;
	/**
	 * CloseDate
	 */
	private LocalDateTime closeDate;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @param requestId
	 * @param entryId
	 * @param status
	 * @param amount
	 * @param creationDate
	 * @param closeDate
	 * @param active
	 */
	public Request(int requestId, int entryId, String code, String status, double amount, LocalDateTime creationDate,
			LocalDateTime closeDate, int active) {
		super();
		this.requestId = requestId;
		this.entryId = entryId;
		this.setCode(code);
		this.status = status;
		this.amount = amount;
		this.creationDate = creationDate;
		this.closeDate = closeDate;
		this.active = active;
	}
	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	/**
	 * @return the entryId
	 */
	public int getEntryId() {
		return entryId;
	}
	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
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
