package com.ryuseicode.siap.repository.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.Entry;

/**
 * @name IEntryRepository
 * {@summary Interface to define the behavior of IEntry Repository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IEntryRepository {
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
	int updateAmountUsed(int entryId, double amountUsed);
}
