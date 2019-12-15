package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Quotation;
import com.ryuseicode.siap.repository.award.intf.IQuotationRepository;
/**
 * @name QuotationRepository
 * {@summary Repository class to implement the behavior of QuotationRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
@Repository
public class QuotationRepository implements IQuotationRepository {
	/**
	 *  jdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name getByAdjudicationId
	 * {@summary Method to get Quotation by adjudicationId }
	 * @param adjudicationId
	 * @return
	 */
	public Quotation getByAdjudicationId(int adjudicationId) {
		List<Quotation> results = jdbcTemplate.query(
                "select * from quotation where adjudicationId = ? and  active = 1 ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new Quotation(
                        	rs.getInt("quotationid"),
                        	rs.getInt("adjudicationid"),
                        	rs.getString("demanding"),
                        	rs.getString("demandingjob"),
                        	rs.getString("authorizer"),
                        	rs.getString("authorizerjob"),
                        	new Timestamp(rs.getDate("elaborationdate").getTime()).toLocalDateTime().toLocalDate(),
                        	rs.getString("elaborationdatetext"),
                        	rs.getInt("active")
                        )
        );
		return results.size() > 0 ? results.get(0) : null; 
	}
	/**
	 * @name save
	 * {@summary Method to save a quotation }
	 * @param quotation
	 * @return
	 */
	public int save(Quotation quotation) {
		return jdbcTemplate.update(
                "insert into quotation(adjudicationid, demanding, demandingjob, authorizer, authorizerjob, elaborationdate, elaborationdatetext, active)values(?, ?, ?, ?, ?, ?, ?, ?)",
                quotation.getAdjudicationId(), quotation.getDemanding(), quotation.getDemandingJob(), quotation.getAuthorizer(), quotation.getAuthorizerJob(), quotation.getElaborationDate(), quotation.getElaborationDateText(), quotation.getActive()
        );
	}
}
