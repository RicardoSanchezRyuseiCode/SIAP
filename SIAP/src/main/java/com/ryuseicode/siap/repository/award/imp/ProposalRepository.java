package com.ryuseicode.siap.repository.award.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Proposal;
import com.ryuseicode.siap.repository.award.intf.IProposalRepository;
/**
 * @name ProposalRepository
 * {@summary Repository class to implement the behavior of IProposalRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
@Repository
public class ProposalRepository implements IProposalRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name getByCompetitorId
	 * {@summary Metho to get a Proposal by competitor id }
	 * @param competitorId
	 * @return
	 */
	public Proposal getByCompetitorId(int competitorId) {
		List<Proposal> result = jdbcTemplate.query(
                "select * from PROPOSAL where competitorid = ? and active = 1 ",
                new Object[] { competitorId },
                (rs, rowNum) ->
                        new Proposal(
                        	rs.getInt("proposalid"),
                        	rs.getInt("competitorid"),
                        	rs.getInt("adjudicationid"),
                        	rs.getString("technicalsubmitted"),
                        	rs.getString("technicalremark"),
                        	rs.getString("economicsubmitted"),
                        	rs.getString("economicremark"),
                        	rs.getDouble("totalamount"),
                        	rs.getString("status"),
                        	rs.getInt("winner"),
                        	rs.getInt("active")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get a list of Proposal by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	public List<Proposal> getByAdjudicationId(int adjudicationId) {
		return jdbcTemplate.query(
                "select * from PROPOSAL where adjudicationid = ? and active = 1 ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new Proposal(
                        	rs.getInt("proposalid"),
                        	rs.getInt("competitorid"),
                        	rs.getInt("adjudicationid"),
                        	rs.getString("technicalsubmitted"),
                        	rs.getString("technicalremark"),
                        	rs.getString("economicsubmitted"),
                        	rs.getString("economicremark"),
                        	rs.getDouble("totalamount"),
                        	rs.getString("status"),
                        	rs.getInt("winner"),
                        	rs.getInt("active")
                        )
        );
	}
	/**
	 * @name save
	 * {@summary Method to save a proposal object to database}
	 * @param proposal
	 * @return
	 */
	public int save(Proposal proposal) {
		return jdbcTemplate.update(
                "insert into PROPOSAL (competitorid, adjudicationid, technicalsubmitted, technicalremark, economicsubmitted, economicremark, totalamount, status, winner, active) values(?,?,?,?,?,?,?,?,?,?)",
                proposal.getCompetitorId(), proposal.getAdjudicationId(), proposal.getTechnicalSubmitted(), proposal.getTechnicalRemark(), 
                proposal.getEconomicSubmitted(),proposal.getEconomicRemark(), proposal.getTotalAmount(), proposal.getStatus(), proposal.getWinner(), proposal.getActive());
	}
	/**
	 * @name save
	 * {@summary Method to save a collection of proposal objects }
	 * @param proposals
	 * @return
	 */
	public int save(List<Proposal> proposals) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(Proposal proposal : proposals) {
			Object[] row = new Object[10];
			row[0] =  proposal.getCompetitorId();
			row[1] =  proposal.getAdjudicationId();
			row[2] =  proposal.getTechnicalSubmitted();
			row[3] =  proposal.getTechnicalRemark();
			row[4] =  proposal.getEconomicSubmitted();
			row[5] =  proposal.getEconomicRemark();
			row[6] =  proposal.getTotalAmount();
			row[7] =  proposal.getStatus();
			row[8] =  proposal.getWinner();
			row[9] =  proposal.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into PROPOSAL (competitorid, adjudicationid, technicalsubmitted, technicalremark, economicsubmitted, economicremark, totalamount, status, winner, active) values(?,?,?,?,?,?,?,?,?,?)", rows)).sum();
	}
	/**
	 * @name update
	 * {@summary Method to update a proposal }
	 * @param proposal
	 * @return
	 */
	public int update(Proposal proposal) {
		return jdbcTemplate.update(
                "update PROPOSAL set competitorid = ?, adjudicationid = ?, technicalsubmitted = ?, technicalremark = ?, economicsubmitted = ?, economicremark = ?, totalamount = ?, status = ?, winner = ? where proposalid = ?",
                proposal.getCompetitorId(), proposal.getAdjudicationId(), proposal.getTechnicalSubmitted(), proposal.getTechnicalRemark(), 
                proposal.getEconomicSubmitted(),proposal.getEconomicRemark(), proposal.getTotalAmount(), proposal.getStatus(), proposal.getWinner(), 
                proposal.getActive(), proposal.getProposalId());
	}
	/**
	 * @name update
	 * {@summary Method to update a collection of proposals }
	 * @param proposals
	 * @return
	 */
	public int update(List<Proposal> proposals) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(Proposal proposal : proposals) {
			Object[] row = new Object[10];
			row[0] =  proposal.getCompetitorId();
			row[1] =  proposal.getAdjudicationId();
			row[2] =  proposal.getTechnicalSubmitted();
			row[3] =  proposal.getTechnicalRemark();
			row[4] =  proposal.getEconomicSubmitted();
			row[5] =  proposal.getEconomicRemark();
			row[6] =  proposal.getTotalAmount();
			row[7] =  proposal.getStatus();
			row[8] =  proposal.getWinner();
			row[9] =  proposal.getProposalId();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("update PROPOSAL set competitorid = ?, adjudicationid = ?, technicalsubmitted = ?, technicalremark = ?, economicsubmitted = ?, economicremark = ?, totalamount = ?, status = ?, winner = ? where proposalid = ?", rows)).sum();
	}
}
