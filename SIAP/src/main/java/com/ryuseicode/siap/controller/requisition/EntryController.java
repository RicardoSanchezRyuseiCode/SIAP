package com.ryuseicode.siap.controller.requisition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryuseicode.siap.entity.requisition.Entry;
import com.ryuseicode.siap.service.requisition.imp.EntryService;

/**
 * @name EntryController
 * {@summary Controller to expose entry endpoint }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Controller
public class EntryController {
	/**
	 * EntryService
	 */
	@Autowired
	private EntryService entryService;
	/**
	 * @name getByChapterId
	 * {@summary Method to get by chapterId }
	 * @param chapterId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/entry/getByChapterId/{chapterId}", produces = "application/json")	
    public List<Entry> getByChapterId(@PathVariable int chapterId) {
		return this.entryService.getByChapterId(chapterId);
	}
	/**
	 * @name getByEntryId
	 * {@summary Method to get by entryid }
	 * @param entryId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/entry/getByEntryId/{entryId}", produces = "application/json")	
    public Entry getByEntryId(@PathVariable int entryId) {
		return this.entryService.getByEntryId(entryId);
	}
}
