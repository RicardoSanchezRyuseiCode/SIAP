package com.ryuseicode.siap.repository.award.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.entity.award.Asset;
import com.ryuseicode.siap.paramoutput.award.AnnexParamOutput;
import com.ryuseicode.siap.repository.award.intf.IAnnexRepository;

/**
 * @name AnnexRepository
 * {@summary Repository class to implement the behavior of AnnexRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
@Repository
public class AnnexRepository implements IAnnexRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByAdjudicationId
	 * {@summary Method to get a list of annex param output }
	 * @param adjudicationId
	 * @return
	 */
	public List<AnnexParamOutput> GetByAdjudicationId(int adjudicationId) {
		return jdbcTemplate.query(
                "select * from annex an inner join asset a on an.assetid = a.assetid where  an.active = 1 and a.active = 1 and an.adjudicationid = ? ",
                new Object[] { adjudicationId },
                (rs, rowNum) ->
                        new AnnexParamOutput(
                        	new Annex(
                        		rs.getInt(1),
                        		rs.getInt(2),
                        		rs.getInt(3),
                        		rs.getDouble(4),
                        		rs.getString(5),
                        		rs.getString(6),
                        		rs.getInt(7)
                			),
                        	new Asset(
                    			rs.getInt(8),
                    			rs.getString(9),
                    			rs.getInt(10)
                			)
                        )
        );
	}
	/**
	 * @name Save
	 * {@summary Method to save a list of annex }
	 * @param annexs
	 * @return
	 */
	public int Save(List<Annex> annexs) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(Annex annex : annexs) {
			Object[] row = new Object[6];
			row[0] =  annex.getAdjudicationId();
			row[1] =  annex.getAssetId();
			row[2] =  annex.getQuantity();
			row[3] =  annex.getDeliveryTerm();
			row[4] =  annex.getDeliveryPlace();
			row[5] =  annex.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into annex (adjudicationid, assetid, quantity, deliveryterm, deliveryplace, active) values(?,?,?,?,?,?)", rows)).sum();
	}
}
