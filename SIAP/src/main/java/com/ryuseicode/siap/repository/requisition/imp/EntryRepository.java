package com.ryuseicode.siap.repository.requisition.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.requisition.Entry;
import com.ryuseicode.siap.repository.requisition.intf.IEntryRepository;

/**
 * @name EntryRepository
 * {@summary Repository to implement the behavior of IEntry Repository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Repository
public class EntryRepository implements IEntryRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByChapterId
	 * {@summary Method to get a list of entries bye ChapterId }
	 * @param chapterId
	 * @return
	 */
	public List<Entry> getByChapterId(int chapterId) {
		return jdbcTemplate.query(
                "select * from entry where active = 1  and chapterid = ? order by code, description ",
                new Object[] { chapterId },
                (rs, rowNum) ->
                        new Entry(
                        		rs.getInt("entryid"),
                        		rs.getInt("chapterid"),
                                rs.getString("code"),
                                rs.getString("description"),
                                rs.getDouble("amountallocated"),
                                rs.getDouble("amountused"),
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name GetByEntryId
	 * {summary Method to get entry by id }
	 * @param entryId
	 * @return
	 */
	public Entry getByEntryId(int entryId) {
		List<Entry> results = jdbcTemplate.query(
                "select * from entry where active = 1  and entryid = ?",
                new Object[] { entryId },
                (rs, rowNum) ->
                        new Entry(
                        		rs.getInt("entryid"),
                        		rs.getInt("chapterid"),
                                rs.getString("code"),
                                rs.getString("description"),
                                rs.getDouble("amountallocated"),
                                rs.getDouble("amountused"),
                                rs.getInt("active")
                        )
        );
		return results.size() > 0 ? results.get(0) : null;
	}
	/**
	 * @name updateAmountUser
	 * {@summary Method to update amount by entry id}
	 * @param entryId
	 * @param amount
	 * @return
	 */
	public int updateAmountUsed(int entryId, double amountUsed) {
		return jdbcTemplate.update(
                "update entry set amountused = ? where entryId = ?",
                amountUsed, entryId);
	}
}
