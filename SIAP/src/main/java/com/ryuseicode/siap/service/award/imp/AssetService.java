package com.ryuseicode.siap.service.award.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Asset;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.AssetRepository;
import com.ryuseicode.siap.service.award.intf.IAssetService;

/**
 * @name AssetService
 * {@summary Class to implement the behavior of IAssetService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
@Service
public class AssetService implements IAssetService {
	/**
	 * AssetRepository
	 */
	@Autowired
	private AssetRepository assetRepository;	
	/**
	 * @name Get
	 * {@summary Method to get a list of assets}
	 * @return
	 */
	public List<Asset> get() {
		return this.assetRepository.get();
	}
	/**
	 * @name Save
	 * {@summary Method to save a good}
	 */
	public void save(Asset asset) throws Exception {
		// Check if asset already exist
		if(this.assetRepository.getByName(asset.getName()) != null) 
			throw new ServiceException("Ya existe un bien o servicio con el mismo nombre");
		// Set default data
		asset.setActive(1);
		// save the asset
		this.assetRepository.save(asset);
	}
	/**
	 * @name save
	 * {@summary Method to save a collection of assets}
	 * @param assets
	 */
	public void save(List<Asset> assets) throws Exception {
		// Get current assets
		List<Asset> currentAssets = this.assetRepository.get();
		// Define result collection
		ArrayList<Asset> newAssets = new ArrayList<Asset>();
		// Loop in assets and try check if are not repeated
		for(Asset asset : assets) {
			boolean flag = true;
			for(Asset current : currentAssets) {
				if (current.getName().toLowerCase().equals(asset.getName().toLowerCase())) {
					flag = false;
					break;
				} 
			}
			if(flag) {
				asset.setActive(1);
				newAssets.add(asset);
			}
		}
		// Save the new assets
		this.assetRepository.save(newAssets);
	}
}