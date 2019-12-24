package com.ryuseicode.siap.service.requisition.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.requisition.Entry;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.requisition.imp.EntryRepository;
import com.ryuseicode.siap.service.requisition.intf.IEntryService;
/**
 * @name EntryService
 * {@summary Service to implement the behavior of IEntryService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Service
public class EntryService implements IEntryService {
	/**
	 * EntryRepository
	 */
	@Autowired
	private EntryRepository entryRepository;
	/**
	 * @name GetByChapterId
	 * {@summary Method to get a list of entries bye ChapterId }
	 * @param chapterId
	 * @return
	 */
	public List<Entry> getByChapterId(int chapterId) {
		return this.entryRepository.getByChapterId(chapterId);
	}
	/**
	 * @name GetByEntryId
	 * {summary Method to get entry by id }
	 * @param entryId
	 * @return
	 */
	public Entry getByEntryId(int entryId) {
		return this.entryRepository.getByEntryId(entryId);
	}
	/**
	 * @name updateAmountUser
	 * {@summary Method to update amount by entry id}
	 * @param entryId
	 * @param amount
	 * @return
	 */
	public void updateAmountUsed(int entryId, double amountUsed) throws Exception {
		// Check if exist
		if(this.entryRepository.getByEntryId(entryId) == null)
			throw new ServiceException("La partida a actualizar no ha sido encontrada");
		// update the amount
		this.entryRepository.updateAmountUsed(entryId, amountUsed);
	}
}
