package com.ryuseicode.siap.repository.requisition.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.requisition.Chapter;
import com.ryuseicode.siap.repository.requisition.intf.IChapterRepository;
/**
 * @name ChapterRepository
 * {@summary Repository class to implement the behavior of IChapterRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Repository
public class ChapterRepository implements IChapterRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name GetByBudgetId
	 * {@summary Method to get by budgetId }
	 * @param budgetId
	 * @return
	 */
	public List<Chapter> getByBudgetId(int budgetId) {
		return jdbcTemplate.query(
                "select * from chapter where active = 1  and budgetid = ? and amount > 0 order by code, concept ",
                new Object[] { budgetId },
                (rs, rowNum) ->
                        new Chapter(
                        		rs.getInt("chapterid"),
                        		rs.getInt("budgetid"),
                                rs.getString("code"),
                                rs.getString("concept"),
                                rs.getDouble("amount"),
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name GetByChapterId
	 * {@summary Method to get by chapterId }
	 * @param chapterId
	 * @return
	 */
	public Chapter getByChapterId(int chapterId) {
		List<Chapter> results = jdbcTemplate.query(
                "select * from chapter where active = 1  and chapterid = ?",
                new Object[] { chapterId },
                (rs, rowNum) ->
                        new Chapter(
                        		rs.getInt("chapterid"),
                        		rs.getInt("budgetid"),
                                rs.getString("code"),
                                rs.getString("concept"),
                                rs.getDouble("amount"),
                                rs.getInt("active")
                        )
        );
		return results.size() > 0 ? results.get(0) : null;
	}
}
