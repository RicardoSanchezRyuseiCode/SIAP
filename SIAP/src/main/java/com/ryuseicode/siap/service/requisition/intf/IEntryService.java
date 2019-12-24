package com.ryuseicode.siap.service.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.Entry;
/**
 * @name IEntryService
 * {@summary Service to define the behavior of IEntryService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IEntryService {
	/**
	 * @name GetByChapterId
	 * {@summary Method to get a list of entries bye ChapterId }
	 * @param chapterId
	 * @return
	 */
	List<Entry> getByChapterId(int chapterId);
	/**
	 * @name GetByEntryId
	 * {summary Method to get entry by id }
	 * @param entryId
	 * @return
	 */
	Entry getByEntryId(int entryId);
	/**
	 * @name updateAmountUser
	 * {@summary Method to update amount by entry id}
	 * @param entryId
	 * @param amount
	 * @return
	 */
	void updateAmountUsed(int entryId, double amountUsed) throws Exception;
}
