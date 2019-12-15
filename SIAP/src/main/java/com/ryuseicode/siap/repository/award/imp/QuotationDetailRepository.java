package com.ryuseicode.siap.repository.award.imp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.QuotationDetail;
import com.ryuseicode.siap.repository.award.intf.IQuotationDetailRepository;

/**
 * @name QuotationDetailRepository
 * {@summary Repository class to implement the behavior of IQuotationDetailRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 13, 2019
 */
@Repository
public class QuotationDetailRepository implements IQuotationDetailRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name getByQuotationId
	 * {@summary Method to get quotation detail by quotationid }
	 * @param quotationId
	 * @return
	 */
	public List<QuotationDetail> getByQuotationId(int quotationId) {
		return jdbcTemplate.query(
	            "select * from quotationdetail where quotationId = ? and active = 1 ",
	            new Object[] { quotationId },
	            (rs, rowNum) ->
	                new QuotationDetail(
	            		rs.getInt("quotationdetailid"),
	            		rs.getInt("quotationid"),
	            		rs.getInt("competitorid"),
	            		new Timestamp(rs.getDate("quotationdate").getTime()).toLocalDateTime().toLocalDate(),
	            		rs.getString("quotationdatetext"),
	            		rs.getString("creditcondition"),
	            		rs.getString("deliveryterm"),
	            		rs.getInt("active")
	                )
	        );
	} 
	/**
	 * @name getByCompetitorId
	 * {@summary Method to get quotation detail }
	 * @param competitorId
	 * @return
	 */
	public QuotationDetail getByCompetitorId(int competitorId) {
		List<QuotationDetail> results = jdbcTemplate.query(
            "select * from quotationdetail where competitorid = ? and active = ? ",
            new Object[] { competitorId },
            (rs, rowNum) ->
                new QuotationDetail(
            		rs.getInt("quotationdetailid"),
            		rs.getInt("quotationid"),
            		rs.getInt("competitorid"),
            		new Timestamp(rs.getDate("quotationdate").getTime()).toLocalDateTime().toLocalDate(),
            		rs.getString("quotationdatetext"),
            		rs.getString("creditcondition"),
            		rs.getString("deliveryterm"),
            		rs.getInt("active")
                )
        );
		return results.size() > 0 ? results.get(0) : null; 
	}
	/**
	 * @name save
	 * {@summary Method to save a collection of quotationDetails }
	 * @param quotationDetails
	 */
	public int save(List<QuotationDetail> quotationDetails) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(QuotationDetail quotationDetail : quotationDetails) {
			Object[] row = new Object[7];
			row[0] =  quotationDetail.getQuotationId();
			row[1] =  quotationDetail.getCompetitorId();
			row[2] =  quotationDetail.getQuotationDate();
			row[3] =  quotationDetail.getQuotationDateText();
			row[4] =  quotationDetail.getCreditCondition();
			row[5] =  quotationDetail.getDeliveryTerm();
			row[6] =  quotationDetail.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into quotationdetail(quotationid, competitorid, quotationdate, quotationdatetext, creditcondition, deliveryterm, active) values(?,?,?,?,?,?,?)", rows)).sum();
	}
}
