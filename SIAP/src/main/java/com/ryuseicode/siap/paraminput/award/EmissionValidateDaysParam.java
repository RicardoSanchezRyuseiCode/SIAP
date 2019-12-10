package com.ryuseicode.siap.paraminput.award;

/**
 * @name EmissionValidateDaysParam
 * {@summary Parameter class to valida days }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 5, 2019
 */
public class EmissionValidateDaysParam {
	/**
	 * sourceOrigin
	 */
	private String sourceOrigin;
	/**
	 * daysForPayment
	 */
	private int daysForPayment;
	/**
	 * Default constructor
	 * @param sourceOrigin
	 * @param daysForPayment
	 */
	public EmissionValidateDaysParam(String sourceOrigin, int daysForPayment) {
		this.setSourceOrigin(sourceOrigin);
		this.setDaysForPayment(daysForPayment);
	}
	/**
	 * @return the sourceOrigin
	 */
	public String getSourceOrigin() {
		return sourceOrigin;
	}
	/**
	 * @param sourceOrigin the sourceOrigin to set
	 */
	public void setSourceOrigin(String sourceOrigin) {
		this.sourceOrigin = sourceOrigin;
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
	
	
}
