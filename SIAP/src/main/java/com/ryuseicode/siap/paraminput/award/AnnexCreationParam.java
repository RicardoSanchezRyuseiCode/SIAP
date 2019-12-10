package com.ryuseicode.siap.paraminput.award;

import java.util.List;

import com.ryuseicode.siap.entity.award.Annex;

/**
 * @name AnnexCreationParam
 * {@summary Entity class to define annex creation param }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public class AnnexCreationParam {
	/**
	 * adjudicationId
	 */
	private int adjudicationId;
	/**
	 * annexs
	 */
	private List<Annex> annexs;
	/**
	 * Default constructor
	 * @param adjudicationId
	 * @param annexs
	 */
	public AnnexCreationParam(int adjudicationId, List<Annex> annexs) {
		this.setAdjudicationId(adjudicationId);
		this.setAnnexs(annexs);
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
	 * @return the annexs
	 */
	public List<Annex> getAnnexs() {
		return annexs;
	}
	/**
	 * @param annexs the annexs to set
	 */
	public void setAnnexs(List<Annex> annexs) {
		this.annexs = annexs;
	}
}
