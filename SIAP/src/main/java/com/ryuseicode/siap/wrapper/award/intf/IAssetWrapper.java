package com.ryuseicode.siap.wrapper.award.intf;
/**
 * @name IAssetWrapper
 * {@summary Interface to define the behavior of AssetWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public interface IAssetWrapper {
	/**
	 * @name Import
	 * {@summary Method to import assets from excel file }
	 * @param filePath
	 */
	void importFile(String filePath) throws Exception;
}
