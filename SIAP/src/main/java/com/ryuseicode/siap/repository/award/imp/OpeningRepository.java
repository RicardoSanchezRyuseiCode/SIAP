package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Opening;
import com.ryuseicode.siap.repository.award.intf.IOpeningRepository;

/**
 * @name OpeningRepository
 * {@summary Repository class to implement the behavior of IOpeningRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 9, 2019
 */
@Repository
public class OpeningRepository implements IOpeningRepository {
	/**
	 * jdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a Opening data by adjudicatinId }
	 * @param adjudicationId
	 * @return
	 */
	public Opening getByAdjudicationId(int adjudicationId) {
		List<Opening> results = jdbcTemplate.query(
                "select * from OPENING where adjudicationid = ? and active = 1 ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new Opening(
                        		rs.getInt("openingid"),
                        		rs.getInt("adjudicationid"),
                                rs.getString("denomination"),
                                new Timestamp(rs.getDate("eventdate").getTime()).toLocalDateTime().toLocalDate(),
                                rs.getString("eventdatetext"),
                                LocalTime.parse(rs.getString("eventstarthour")),
                                rs.getString("eventstarthourtext"),
                                LocalTime.parse(rs.getString("eventendhour")),
                                rs.getString("eventendhourtext"),
                                rs.getString("responsable"),
                                rs.getString("firstwitness"),
                                rs.getString("secondwitness"),
                                rs.getInt("supplierid"),
                                new Timestamp(rs.getDate("failureissuancedate").getTime()).toLocalDateTime().toLocalDate(),
                                rs.getString("failureissuancedatetext"),
                                LocalTime.parse(rs.getString("failureissuancehour")),
                                rs.getString("failureissuancehourtext"),
                                rs.getInt("active")
                        )
        );
		return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name Save
	 * {@summary Method to save an opening object }
	 * @param opening
	 * @return
	 */
	public int save(Opening opening) {
		return jdbcTemplate.update(
                "insert into OPENING (ADJUDICATIONID,DENOMINATION,EVENTDATE,EVENTDATETEXT,EVENTSTARTHOUR,EVENTSTARTHOURTEXT,EVENTENDHOUR,EVENTENDHOURTEXT,RESPONSABLE,FIRSTWITNESS,SECONDWITNESS,SUPPLIERID,FAILUREISSUANCEDATE,FAILUREISSUANCEDATETEXT,FAILUREISSUANCEHOUR,FAILUREISSUANCEHOURTEXT,ACTIVE)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                opening.getAdjudicationId(), opening.getDenomination(), opening.getEventDate(), opening.getEventDateText(), opening.getEventStartHour().format(DateTimeFormatter.ofPattern("HH:mm")), opening.getEventStartHourText(),
                opening.getEventEndHour().format(DateTimeFormatter.ofPattern("HH:mm")), opening.getEventEndHourText(), opening.getReponsable(), opening.getFirstWitness(), opening.getSecondWitness(),opening.getSupplierId(), 
                opening.getFailureIssuanceDate(),opening.getFailureIssuanceDateText(), opening.getFailureIssuanceHour().format(DateTimeFormatter.ofPattern("HH:mm")), opening.getFailureIssuanceHourText(), opening.getActive()
        );
	}
}
