package com.ryuseicode.siap.entity.award;

/**
 * @name Asset
 * {@summary Entity class to model asset }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public class Asset {
	/**
	 * AssetId
	 */
	private int assetId;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @name Asset
	 * {@summary Default constructor}
	 * @param assetId
	 * @param name
	 * @param active
	 */
	public Asset(int assetId, String name, int active) {
		this.setAssetId(assetId);
		this.setName(name);
		this.setActive(active);
	}
	/**
	 * @name getAssetId
	 * @return
	 */
	public int getAssetId() {
		return assetId;
	}
	/**
	 * @name setAssetId
	 */
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	/**
	 * @name getName
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * @setName
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @name getActive
	 * @return
	 */
	public int getActive() {
		return active;
	}
	/**
	 * @name setActive
	 * @param active
	 */
	public void setActive(int active) {
		this.active = active;
	}
}
