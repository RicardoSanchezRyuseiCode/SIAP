package com.ryuseicode.siap.repository.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.Chapter;

/**
 * @name IChapterInterface
 * {@summary Interface to define the behavior of IChapterInterface }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IChapterRepository {
	/**
	 * @name GetByBudgetId
	 * {@summary Method to get by budgetId }
	 * @param budgetId
	 * @return
	 */
	List<Chapter> getByBudgetId(int budgetId);
	/**
	 * @name GetByChapterId
	 * {@summary Method to get by chapterId }
	 * @param chapterId
	 * @return
	 */
	Chapter getByChapterId(int chapterId);
	
}
