package com.ryuseicode.siap.service.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.Chapter;
/**
 * @name IChapterService
 * {@summary Interface to define the behavior od IChapterService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IChapterService {
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
