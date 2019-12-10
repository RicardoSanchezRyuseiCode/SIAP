package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.AdjudicationStep;
import com.ryuseicode.siap.repository.award.intf.IAdjudicationStepRepository;
/**
 * @name AdjudicationStepRepository
 * {@summary Repository to implement the behavior of IAdjudicationStepRepository}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
@Repository
public class AdjudicationStepRepository implements IAdjudicationStepRepository {

	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of adjudication document }
	 * @param adjudicationId
	 * @return
	 */
	public List<AdjudicationStep> GetByAdjudicationId(int adjudicationId) {
		return jdbcTemplate.query(
                "select * from ADJUDICATIONSTEP where adjudicationid = ? ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new AdjudicationStep(
                        	rs.getInt("adjudicationstepid"),
                        	rs.getInt("adjudicationid"),
                        	rs.getString("step"),
                        	new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime()
                        )
        );
	}
	
	/**
	 * @name Save
	 * {@summary Method to save an adjudication step}
	 * @param adjudicationStep
	 */
	public int Save(AdjudicationStep adjudicationStep) {
		return jdbcTemplate.update(
                "insert into ADJUDICATIONSTEP (adjudicationid, step, creationdate) values(?,?,?)",
                adjudicationStep.getAdjudicationId(), adjudicationStep.getStep(), adjudicationStep.getCreationDate()
        );
	}
}
