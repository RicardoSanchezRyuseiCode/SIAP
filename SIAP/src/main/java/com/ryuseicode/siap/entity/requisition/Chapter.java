package com.ryuseicode.siap.entity.requisition;
/**
 * @name Chapter
 * {@summary Entity class to model chapter  }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 21, 2019
 */
public class Chapter {
	/**
	 * ChaptetId
	 */
	private int chapterId;
	/**
	 * BudgetId
	 */
	private int budgetId;
	/**
	 * Code
	 */
	private String code;
	/**
	 * Concept
	 */
	private String concept;
	/**
	 * Amount
	 */
	private double amount;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @param chapterId
	 * @param budgetId
	 * @param code
	 * @param concept
	 * @param amount
	 * @param active
	 */
	public Chapter(int chapterId, int budgetId, String code, String concept, double amount, int active) {
		super();
		this.chapterId = chapterId;
		this.budgetId = budgetId;
		this.code = code;
		this.concept = concept;
		this.amount = amount;
		this.active = active;
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
	 * @return the budgetId
	 */
	public int getBudgetId() {
		return budgetId;
	}
	/**
	 * @param budgetId the budgetId to set
	 */
	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
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
	 * @return the concept
	 */
	public String getConcept() {
		return concept;
	}
	/**
	 * @param concept the concept to set
	 */
	public void setConcept(String concept) {
		this.concept = concept;
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
