package com.ryuseicode.siap.repository.requisition.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.requisition.RequestDetail;
import com.ryuseicode.siap.repository.requisition.intf.IRequestDetailRepository;

/**
 * @name RequestDetailRepository
 * {@summary Repositiory class to implement the behavior of IRequestDetailRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Repository
public class RequestDetailRepository implements IRequestDetailRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name getByRequestId
	 * {@summary Method to get list of request detail by request id }
	 * @param requestId
	 * @return
	 */
	public List<RequestDetail> getByRequestId(int requestId) {
		return jdbcTemplate.query(
                "select * from requestdetail where active = 1  and requestid = ?",
                new Object[] { requestId },
                (rs, rowNum) ->
                        new RequestDetail(
                        		rs.getInt("requestdetailid"),
                        		rs.getInt("requestid"),
                                rs.getString("asset"),
                                rs.getInt("quantity"),
                                rs.getDouble("unitPrice"),
                                rs.getDouble("total"),
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name getByRequestDetailId
	 * {@summary Method to get requestdetail by id }
	 * @param requestDetailId
	 * @return
	 */
	public RequestDetail getByRequestDetailId(int requestDetailId) {
		List<RequestDetail> results = jdbcTemplate.query(
                "select * from requestdetail where active = 1  and requestDetailid = ?",
                new Object[] { requestDetailId },
                (rs, rowNum) ->
                        new RequestDetail(
                        		rs.getInt("requestdetailid"),
                        		rs.getInt("requestid"),
                                rs.getString("asset"),
                                rs.getInt("quantity"),
                                rs.getDouble("unitPrice"),
                                rs.getDouble("total"),
                                rs.getInt("active")
                        )
        );
		return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name getByAsset
	 * {@summary Method to get by Asset }
	 * @param asset
	 * @return
	 */
	public RequestDetail getByAsset(String asset, int requestId) {
		List<RequestDetail> results = jdbcTemplate.query(
                "select * from requestdetail where active = 1  and asset = ? and requestid = ?",
                new Object[] { asset, requestId },
                (rs, rowNum) ->
                        new RequestDetail(
                        		rs.getInt("requestdetailid"),
                        		rs.getInt("requestid"),
                                rs.getString("asset"),
                                rs.getInt("quantity"),
                                rs.getDouble("unitPrice"),
                                rs.getDouble("total"),
                                rs.getInt("active")
                        )
        );
		return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name save
	 * {@summary Method to save a request detail }
	 * @param requestDetail
	 * @return
	 */
	public int save(RequestDetail requestDetail) {
		return jdbcTemplate.update(
                "insert into requestdetail (requestid, asset, quantity, unitprice, total, active) values(?,?,?,?,?,?)",
                requestDetail.getRequestId(), requestDetail.getAsset(), requestDetail.getQuantity(), requestDetail.getUnitPrice(), requestDetail.getTotal(), requestDetail.getActive());
	}
	/**
	 * @name save
	 * {@summary Method to save a list of request details }
	 * @param requestDetails
	 * @return
	 */
	public int save(List<RequestDetail> requestDetails) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(RequestDetail requestDetail : requestDetails) {
			Object[] row = new Object[6];
			row[0] =  requestDetail.getRequestId();
			row[1] =  requestDetail.getAsset();
			row[2] =  requestDetail.getQuantity();
			row[3] =  requestDetail.getUnitPrice();
			row[4] =  requestDetail.getTotal();
			row[5] =  requestDetail.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into requestdetail (requestid, asset, quantity, unitprice, total, active) values(?,?,?,?,?,?)", rows)).sum();
	}
	/**
	 * @name delete
	 * @summary Method to delete request detail id
	 * @param requestDetailId
	 * @return
	 */
	public int delete(int requestDetailId) {
		return jdbcTemplate.update(
                "update requestdetail set active = 0 where requestdetailid = ?",
                requestDetailId);
	}
	/**
	 * @name deleteByRequestId
	 * {@summary Method to delete request details by requestId }
	 * @param requestId
	 * @return
	 */
	public int deleteByRequestId(int requestId) {
		return jdbcTemplate.update(
                "update requestdetail set active = 0 where requestid = ?",
                requestId);
	}
}
