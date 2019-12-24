package com.ryuseicode.siap.entity.requisition;
/**
 * @name Entry
 * {@summary Entity to model entry }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 21, 2019
 */
public class Entry {
	/**
	 * EntryId
	 */
	private int entryId;
	/**
	 * ChapterId
	 */
	private int chapterId;
	/**
	 * Code
	 */
	private String code;
	/**
	 * Description
	 */
	private String description;
	/**
	 * AmountAllocated
	 */
	private double amountAllocated;
	/**
	 * AmountUsed
	 */
	private double amountUsed;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @param entryId
	 * @param chapterId
	 * @param code
	 * @param description
	 * @param amountAllocated
	 * @param amountUsed
	 * @param active
	 */
	public Entry(int entryId, int chapterId, String code, String description, double amountAllocated, double amountUsed,
			int active) {
		super();
		this.entryId = entryId;
		this.chapterId = chapterId;
		this.code = code;
		this.description = description;
		this.amountAllocated = amountAllocated;
		this.amountUsed = amountUsed;
		this.active = active;
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
	 * @return the chapterId
	 */
	public int getChapterId() {
		return chapterId;
	}
	/**
	 * @param chapterId the chapterId to set
	 */
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the amountAllocated
	 */
	public double getAmountAllocated() {
		return amountAllocated;
	}
	/**
	 * @param amountAllocated the amountAllocated to set
	 */
	public void setAmountAllocated(double amountAllocated) {
		this.amountAllocated = amountAllocated;
	}
	/**
	 * @return the amountUsed
	 */
	public double getAmountUsed() {
		return amountUsed;
	}
	/**
	 * @param amountUsed the amountUsed to set
	 */
	public void setAmountUsed(double amountUsed) {
		this.amountUsed = amountUsed;
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
