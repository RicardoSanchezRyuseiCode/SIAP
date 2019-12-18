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
                                rs.getDate("enddate") != null ? new Timestamp(rs.getDate("enddate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name getPending
	 * {@summary Method to get Pending }
	 * @return
	 */
	public List<Adjudication> getPending() {
		return jdbcTemplate.query(
                "select * from ADJUDICATION where finishdate is null and Active = 1 ",
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
                                rs.getDate("finishdate") != null ? new Timestamp(rs.getDate("finishdate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name getFinished
	 * {@summary Method to get finished }
	 * @return
	 */
	public List<Adjudication> getFinished() {
		return jdbcTemplate.query(
                "select * from ADJUDICATION where finishdate is not null and Active = 1 ",
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
                                rs.getDate("finishdate") != null ? new Timestamp(rs.getDate("finishdate").getTime()).toLocalDateTime() : null,
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
                                rs.getDate("finishdate") != null ? new Timestamp(rs.getDate("finishdate").getTime()).toLocalDateTime() : null,
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
                                rs.getDate("finishdate") != null ? new Timestamp(rs.getDate("finishdate").getTime()).toLocalDateTime() : null,
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
	 * @name updateStatus
	 * @abstract Method to update status
	 */
	public int updateStatus(int adjudicationId, String status) {
		return jdbcTemplate.update(
                "update ADJUDICATION set status = ? where adjudicationId = ?",
                status, adjudicationId);
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
	/**
	 * @name updateEndDate
	 * {@adjudication updateEndDate }
	 * @param adjudicationId
	 * @param endDate
	 * @return
	 */
	public int updateFinishDate(int adjudicationId, LocalDateTime finishDate) {
		return jdbcTemplate.update(
                "update ADJUDICATION set finishdate = ? where adjudicationId = ?",
                finishDate, adjudicationId);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete adjudication}
	 * @param adjudicationId
	 * @throws Exception
	 */
	public int delete(int adjudicationId) {
		return jdbcTemplate.update(
                "update ADJUDICATION set active = ? where adjudicationId = ?",
                0, adjudicationId);
	}
}
