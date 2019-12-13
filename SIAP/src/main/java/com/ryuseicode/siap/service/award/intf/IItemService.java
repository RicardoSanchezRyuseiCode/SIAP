package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Item;

/**
 * @name IItemService
 * {@summary Interface to define the behavior of IItemService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public interface IItemService {
	/**
	 * @name getByProposalId
	 * {@summary Method to get list of items by proposalId }
	 * @param proposalId
	 * @return
	 */
	List<Item> getByProposalId(int proposalId);
	/**
	 * @name save
	 * {@summary Method to save a list of items }
	 * @param items
	 * @return
	 */
	void save(List<Item> items);
}
