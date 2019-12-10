package com.ryuseicode.siap.paraminput.award;

import java.util.List;

import com.ryuseicode.siap.entity.award.Competitor;

public class CompetitorCreationParam {
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * Competitors
	 */
	private List<Competitor> competitors;
	/**
	 * Default constructor
	 * @param adjudicationId
	 * @param competitors
	 */
	public CompetitorCreationParam(int adjudicationId, List<Competitor> competitors) {
		this.setAdjudicationId(adjudicationId);
		this.setCompetitors(competitors);
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
	 * @return the competitors
	 */
	public List<Competitor> getCompetitors() {
		return competitors;
	}

	/**
	 * @param competitors the competitors to set
	 */
	public void setCompetitors(List<Competitor> competitors) {
		this.competitors = competitors;
	}
}
