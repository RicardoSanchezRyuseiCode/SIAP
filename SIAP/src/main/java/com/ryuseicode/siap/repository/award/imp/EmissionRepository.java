package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Emission;
import com.ryuseicode.siap.repository.award.intf.IEmissionRepository;

/**
 * @name EmissionRepository
 * {@summary Repository class to implement the behavior of IEmissionRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
@Repository
public class EmissionRepository implements IEmissionRepository {
	/**
	 * jdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name Emission
	 * {@summary Method to get a list of emission }
	 * @return
	 */
	public Emission GetByAdjudicationId(int adjudicationId) {
		List<Emission> results = jdbcTemplate.query(
                "select * from EMISSION where adjudicationid = ? and active = 1 ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new Emission(
                        		rs.getInt("emissionid"),
                        		rs.getInt("adjudicationid"),
                                rs.getString("place"),
                                new Timestamp(rs.getDate("emissiondate").getTime()).toLocalDateTime(),
                                rs.getString("emissiondatetext"),
                                rs.getInt("daysforpayment"),
                                rs.getInt("active")
                        )
        );
		return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name Save
	 * {@summary Method to save an emission object }
	 * @param emission
	 */
	public int Save(Emission emission) {
		return jdbcTemplate.update(
                "insert into EMISSION (adjudicationid, place, emissiondate, emissiondatetext, daysforpayment, active) values(?,?,?,?,?,?)",
                emission.getAdjudicationId(), emission.getPlace(), emission.getEmissionDate(), emission.getEmissionDateText(), emission.getDaysForPayment(), emission.getActive()
        );
	}
}
