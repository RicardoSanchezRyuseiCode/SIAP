package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Asset;

/**
 * @name IAssetRepository
 * {@summary Interface to define the behavior of IAssetRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public interface IAssetRepository {
	/**
	 * @name Get
	 * {@summary Method to get a list of assets}
	 * @return
	 */
	List<Asset> get();
	/**
	 * @name GetByName
	 * @param name
	 * @return
	 */
	Asset getByName(String name);
	/**
	 * @name Save
	 * {@summary Method to save a good}
	 */
	int save(Asset asset);
	/**
	 * @name Save
	 * @param asset
	 * @return
	 */
	int save(List<Asset> assets);
}