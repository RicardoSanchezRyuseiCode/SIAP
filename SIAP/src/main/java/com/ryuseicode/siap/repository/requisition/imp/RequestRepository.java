package com.ryuseicode.siap.repository.requisition.imp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.requisition.Request;
import com.ryuseicode.siap.repository.requisition.intf.IRequestRepository;
/**
 * @name RequestRepository
 * {@summary Repository class to implement the behavior of IRequestRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Repository
public class RequestRepository implements IRequestRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByEntryId
	 * {@summary Method to get list of request by EntryId }
	 * @param entryId
	 * @return
	 */
	public List<Request> getByEntryId(int entryId) {
		return jdbcTemplate.query(
                "select * from request where active = 1  and entryid = ? order by code",
                new Object[] { entryId },
                (rs, rowNum) ->
                        new Request(
                        		rs.getInt("requestid"),
                        		rs.getInt("entryid"),
                        		rs.getString("code"),
                                rs.getString("status"),
                                rs.getDouble("amount"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getDate("closedate") != null ? new Timestamp(rs.getDate("closedate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name getByEntryIdStatus
	 * {@summary Method to get by status and entryid }
	 * @param entryId
	 * @param status
	 * @return
	 */
	public List<Request> getByEntryIdStatus(int entryId, String status) {
		return jdbcTemplate.query(
                "select * from request where active = 1  and entryid = ? and status = ? order by code",
                new Object[] { entryId, status },
                (rs, rowNum) ->
                        new Request(
                        		rs.getInt("requestid"),
                        		rs.getInt("entryid"),
                        		rs.getString("code"),
                                rs.getString("status"),
                                rs.getDouble("amount"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getDate("closedate") != null ? new Timestamp(rs.getDate("closedate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name GetByRequestId
	 * {@sumamry Method to get request by id }
	 * @param requestId
	 * @return
	 */
	public Request getByRequestId(int requestId) {
		List<Request> result = jdbcTemplate.query(
                "select * from request where active = 1  and requestid = ?",
                new Object[] { requestId },
                (rs, rowNum) ->
                        new Request(
                        		rs.getInt("requestid"),
                        		rs.getInt("entryid"),
                        		rs.getString("code"),
                                rs.getString("status"),
                                rs.getDouble("amount"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getDate("closedate") != null ? new Timestamp(rs.getDate("closedate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name getByCode
	 * {@summary Method to get by code }
	 * @param code
	 * @return
	 */
	public Request getByCode(String code) {
		List<Request> result = jdbcTemplate.query(
                "select * from request where active = 1  and code = ?",
                new Object[] { code },
                (rs, rowNum) ->
                        new Request(
                        		rs.getInt("requestid"),
                        		rs.getInt("entryid"),
                        		rs.getString("code"),
                                rs.getString("status"),
                                rs.getDouble("amount"),
                                new Timestamp(rs.getDate("creationdate").getTime()).toLocalDateTime(),
                                rs.getDate("closedate") != null ? new Timestamp(rs.getDate("closedate").getTime()).toLocalDateTime() : null,
                                rs.getInt("active")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name save
	 * {@summary Method to save request }
	 * @param request
	 * @return
	 */
	public int save(Request request) {
		return jdbcTemplate.update(
                "insert into request (entryid, code, status, amount, creationdate, closedate, active) values (?,?,?,?,?,?,?)",
                request.getEntryId(), request.getCode(), request.getStatus(), request.getAmount(), request.getCreationDate(), request.getCloseDate(), request.getActive());
	}
	/**
	 * @name updateStatus
	 * {@summary Method to update status of request }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	public int updateStatus(int requestId, String status) {
		return jdbcTemplate.update(
                "update request set status = ? where requestid = ?",
                status, requestId);
	}
	/**
	 * @name updateAmount
	 * {@summary Method to update amount }
	 * @param requestId
	 * @param amount
	 * @return
	 */
	public int updateAmount(int requestId, double amount) {
		return jdbcTemplate.update(
                "update request set amount = ? where requestid = ?",
                amount, requestId);
	}
	/**
	 * @name closeDate
	 * {@summary Method to update close date }
	 * @param requestId
	 * @param closeDate
	 * @return
	 */
	public int updateCloseDate(int requestId, LocalDateTime closeDate) {
		return jdbcTemplate.update(
                "update request set closedate = ? where requestid = ?",
                closeDate, requestId);
	}
}
