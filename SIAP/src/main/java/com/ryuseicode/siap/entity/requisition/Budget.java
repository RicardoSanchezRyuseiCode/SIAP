package com.ryuseicode.siap.entity.requisition;
/**
 * @name Budget
 * {@summary Entity class to model Budget }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 21, 2019
 */
public class Budget {
	/**
	 * BudgetId
	 */
	private int budgetId;
	/**
	 * Administrative Unit
	 */
	private int administrativeUnitId;
	/**
	 * Code
	 */
	private String code;
	/**
	 * Description
	 */
	private String description;
	/**
	 * Season
	 */
	private String season;
	/**
	 * Amount
	 */
	private double amount;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @param budgetId
	 * @param administrativeUnitId
	 * @param code
	 * @param description
	 * @param season
	 * @param amount
	 * @param active
	 */
	public Budget(int budgetId, int administrativeUnitId, String code, String description, String season, double amount,
			int active) {
		super();
		this.budgetId = budgetId;
		this.administrativeUnitId = administrativeUnitId;
		this.code = code;
		this.description = description;
		this.season = season;
		this.amount = amount;
		this.active = active;
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
	 * @return the administrativeUnitId
	 */
	public int getAdministrativeUnitId() {
		return administrativeUnitId;
	}
	/**
	 * @param administrativeUnitId the administrativeUnitId to set
	 */
	public void setAdministrativeUnitId(int administrativeUnitId) {
		this.administrativeUnitId = administrativeUnitId;
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
	 * @return the season
	 */
	public String getSeason() {
		return season;
	}
	/**
	 * @param season the season to set
	 */
	public void setSeason(String season) {
		this.season = season;
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
