package com.ryuseicode.siap.repository.award.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Competitor;
import com.ryuseicode.siap.entity.award.Supplier;
import com.ryuseicode.siap.paramoutput.award.CompetitorParamOutput;
import com.ryuseicode.siap.repository.award.intf.ICompetitorRepository;
/**
 * @name CompetitorRepository
 * {@summary Repository to implement the behavior of ICompetitorRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 4, 2019
 */
@Repository
public class CompetitorRepository implements ICompetitorRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of competitor }
	 * @param adjudicationId
	 * @return
	 */
	public List<CompetitorParamOutput> getByAdjudicationId(int adjudicationId) {
		return jdbcTemplate.query(
                "select * from competitor c inner join supplier s on c.supplierid = s.supplierid where c.adjudicationid = ? and c.active = 1 and s.active = 1",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                	new CompetitorParamOutput(
                        new Competitor(
                        		rs.getInt(1),
                        		rs.getInt(2),
                        		rs.getInt(3),
                                rs.getString(4),
                                rs.getInt(5)
                        ),
                        new Supplier(
                        		rs.getInt(6),
                        		rs.getString(7),
                        		rs.getString(8),
                        		rs.getString(9),
                        		rs.getString(10),
                        		rs.getString(11),
                        		rs.getInt(12)
                		)
                    )
        );
	}
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get by competitorId }
	 * @param competitorId
	 * @return
	 */
	public CompetitorParamOutput getByCompetitorId(int competitorId) {
		List<CompetitorParamOutput> result = jdbcTemplate.query(
                "select * from competitor c inner join supplier s on c.supplierid = s.supplierid where c.competitorid = ? and c.active = 1 and s.active = 1",
                new Object[] { competitorId },
                (rs, rowNum) ->
                	new CompetitorParamOutput(
                        new Competitor(
                        		rs.getInt(1),
                        		rs.getInt(2),
                        		rs.getInt(3),
                                rs.getString(4),
                                rs.getInt(5)
                        ),
                        new Supplier(
                        		rs.getInt(6),
                        		rs.getString(7),
                        		rs.getString(8),
                        		rs.getString(9),
                        		rs.getString(10),
                        		rs.getString(11),
                        		rs.getInt(12)
                		)
                    )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name Save
	 * {@summary Method to save a competitor }
	 * @param competitor
	 */
	public int save(List<Competitor> competitors) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(Competitor competitor : competitors) {
			Object[] row = new Object[4];
			row[0] =  competitor.getAdjudicationId();
			row[1] =  competitor.getSupplierId();
			row[2] =  competitor.getTradeNumber();
			row[3] =  competitor.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into COMPETITOR (adjudicationid, supplierid, tradenumber, active) values(?,?,?,?)", rows)).sum();	
	}
}
