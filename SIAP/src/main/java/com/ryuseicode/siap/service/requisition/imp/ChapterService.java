package com.ryuseicode.siap.service.requisition.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.requisition.Chapter;
import com.ryuseicode.siap.repository.requisition.imp.ChapterRepository;
import com.ryuseicode.siap.service.requisition.intf.IChapterService;

/**
 * @name ChapterService
 * {@summary Service class to implement the behavior of IChapterService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Service
public class ChapterService implements IChapterService {
	/**
	 * ChapterRepository 
	 */
	@Autowired
	private ChapterRepository chapterRepository;
	/**
	 * @name GetByBudgetId
	 * {@summary Method to get by budgetId }
	 * @param budgetId
	 * @return
	 */
	public List<Chapter> getByBudgetId(int budgetId) {
		return this.chapterRepository.getByBudgetId(budgetId);
	}
	/**
	 * @name GetByChapterId
	 * {@summary Method to get by chapterId }
	 * @param chapterId
	 * @return
	 */
	public Chapter getByChapterId(int chapterId) {
		return this.chapterRepository.getByChapterId(chapterId);
	}
}
