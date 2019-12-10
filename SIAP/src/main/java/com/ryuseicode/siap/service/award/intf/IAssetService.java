package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Asset;

/**
 * @name IAssetService
 * {@summary Interface to define the behavior of }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public interface IAssetService {
	/**
	 * @name Get
	 * {@summary Method to get a list of assets}
	 * @return
	 */
	List<Asset> get();
	/**
	 * @name Save
	 * {@summary Method to save a good}
	 */
	void save(Asset asset) throws Exception;
	/**
	 * @name save
	 * {@summary Method to save a collection of assets }
	 * @param asset
	 */
	void save(List<Asset> asset) throws Exception;
}
