package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Adjudication;
import com.ryuseicode.siap.repository.award.intf.IAdjudicationRepository;

/**
 * @name AdjudicationRepository
 * {@summary Repository class to implement the behavior of AdjudicationRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
@Repository
public class AdjudicationRepository implements IAdjudicationRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name Get
	 * {@summary Method to get a collection of adjudication }
	 * @return
	 */
	public List<Adjudication> get() {
		return jdbcTemplate.query(
                "select * from ADJUDICATION where Active = 1 ",
                (rs, rowNum) ->
                        new Adjudication(
                        		rs.getInt("adjudicationid"),
                                rs.getString("procedurenumber"),
                                rs.getString("contracttype"),
                                rs.getString("modality"),
                                rs.getString("sourceorigin"),
                                rs.getString("adjudicationtype"),
                                rs.getDouble("amount"),
                                rs.getString("status"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getDate("closedate") != null ? new Timestamp(rs.getDate("closedate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name GetById
	 * {@summary Method to get an adjudication by id }
	 * @param adjudicationId
	 * @return
	 */
	public Adjudication getById(int adjudicationId) { 
		List<Adjudication> result = jdbcTemplate.query(
                "select * from ADJUDICATION where adjudicationid = ? and Active = 1 ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new Adjudication(
                        		rs.getInt("adjudicationid"),
                                rs.getString("procedurenumber"),
                                rs.getString("contracttype"),
                                rs.getString("modality"),
                                rs.getString("sourceorigin"),
                                rs.getString("adjudicationtype"),
                                rs.getDouble("amount"),
                                rs.getString("status"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getDate("closedate") != null ? new Timestamp(rs.getDate("closedate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name GetByProcedureNumber
	 * {@summary Method to get an adjudication by procedure number }
	 * @param procedureNumber
	 * @return
	 */
	public Adjudication getByProcedureNumber(String procedureNumber) { 
		List<Adjudication> result = jdbcTemplate.query(
                "select * from ADJUDICATION where procedurenumber = ? and Active = 1 ",
                new Object[] { procedureNumber },
                (rs, rowNum) ->
                        new Adjudication(
                        		rs.getInt("adjudicationid"),
                                rs.getString("procedurenumber"),
                                rs.getString("contracttype"),
                                rs.getString("modality"),
                                rs.getString("sourceorigin"),
                                rs.getString("adjudicationtype"),
                                rs.getDouble("amount"),
                                rs.getString("status"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getDate("closedate") != null ? new Timestamp(rs.getDate("closedate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name Save
	 * {@summary Method to save an adjudication }
	 * @param adjudication
	 */
	public int save(Adjudication adjudication) {
		return jdbcTemplate.update(
                "insert into ADJUDICATION (procedurenumber, contracttype, modality, sourceorigin, adjudicationtype, amount, status, creationdate, active) values(?,?,?,?,?,?,?,?,?)",
                adjudication.getProcedureNumber(), adjudication.getContractType(), adjudication.getModality(), adjudication.getSourceOrigin(), adjudication.getAdjudicationType(), 
                adjudication.getAmount(), adjudication.getStatus(), adjudication.getCreationDate(), adjudication.getActive());
	}
	/**
	 * @name updateCloseDate
	 * {@summary Method to update close date }
	 * @param adjudicationId
	 * @param closeDate
	 * @return
	 */
	public int updateCloseDate(int adjudicationId, LocalDateTime closeDate) {
		return jdbcTemplate.update(
                "update ADJUDICATION set closedate = ? where adjudicationId = ?",
                closeDate, adjudicationId);
	}
}
