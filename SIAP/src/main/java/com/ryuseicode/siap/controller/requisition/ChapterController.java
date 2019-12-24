package com.ryuseicode.siap.controller.requisition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryuseicode.siap.entity.requisition.Chapter;
import com.ryuseicode.siap.service.requisition.imp.ChapterService;
/**
 * @name ChapterController
 * {@summary Controller to expose the endpoints of Chapter }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Controller
public class ChapterController {
	/**
	 * ChapterService
	 */
	@Autowired
	private ChapterService chapterService;
	/**
	 * @name getByBudgetId
	 * {@summary Method to get by budget id }
	 * @param budgetId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/chapter/getByBudgetId/{budgetId}", produces = "application/json")	
    public List<Chapter> getByBudgetId(@PathVariable int budgetId) {
		return this.chapterService.getByBudgetId(budgetId);
	}
	/**
	 * @name getByChapterId
	 * {@summary Method to get by chapter id }
	 * @param chapterId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/chapter/getByChapterId/{chapterId}", produces = "application/json")	
    public Chapter getByChapterId(@PathVariable int chapterId) {
		return this.chapterService.getByChapterId(chapterId);
	}
}
