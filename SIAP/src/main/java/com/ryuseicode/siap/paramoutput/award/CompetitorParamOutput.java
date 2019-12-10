package com.ryuseicode.siap.paramoutput.award;

import com.ryuseicode.siap.entity.award.Competitor;
import com.ryuseicode.siap.entity.award.Supplier;

/**
 * @name CompetitorParamOutput
 * {@summary Entity class to define CompetitorParamOutput }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public class CompetitorParamOutput {
	/**
	 * competitor
	 */
	private Competitor competitor;
	/**
	 * supplier
	 */
	private Supplier supplier;
	/**
	 * Default constructor
	 * @param competitor
	 * @param supplier
	 */
	public CompetitorParamOutput(Competitor competitor, Supplier supplier)
	{
		this.setCompetitor(competitor);
		this.setSupplier(supplier);
	}
	/**
	 * @return the competitor
	 */
	public Competitor getCompetitor() {
		return competitor;
	}
	/**
	 * @param competitor the competitor to set
	 */
	public void setCompetitor(Competitor competitor) {
		this.competitor = competitor;
	}
	/**
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}
	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
