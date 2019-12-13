package com.ryuseicode.siap.service.award.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Item;
import com.ryuseicode.siap.repository.award.imp.ItemRepository;
import com.ryuseicode.siap.service.award.intf.IItemService;
/**
 * @name ItemService
 * {@summary Service to implement the behavior of IItemService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
@Service
public class ItemService implements IItemService {
	/**
	 * ItemRepository
	 */
	@Autowired
	private ItemRepository itemRepository;
	/**
	 * @name getByProposalId
	 * {@summary Method to get list of items by proposalId }
	 * @param proposalId
	 * @return
	 */
	public List<Item> getByProposalId(int proposalId) { 
		return this.itemRepository.getByProposalId(proposalId);
	}
	/**
	 * @name save
	 * {@summary Method to save a list of items }
	 * @param items
	 * @return
	 */
	public void save(List<Item> items) { 
		this.itemRepository.save(items);
		
	}
}