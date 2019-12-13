package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Item;

/**
 * @name IItemRepository
 * {@summary Interface to define the behavior of IItemRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public interface IItemRepository {
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
	int save(List<Item> items);
}
