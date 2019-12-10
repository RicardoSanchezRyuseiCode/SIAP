package com.ryuseicode.siap.paramoutput.award;

import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.entity.award.Asset;

/**
 * @name AnnexParamOutput
 * {@summary Entity to model annex param output }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public class AnnexParamOutput {
	/**
	 * Annex
	 */
	private Annex annex;
	/**
	 * Asset
	 */
	private Asset asset;
	
	public AnnexParamOutput(Annex annex, Asset asset) {
		this.setAnnex(annex);
		this.setAsset(asset);
	}
	/**
	 * @return the annex
	 */
	public Annex getAnnex() {
		return annex;
	}
	/**
	 * @param annex the annex to set
	 */
	public void setAnnex(Annex annex) {
		this.annex = annex;
	}
	/**
	 * @return the asset
	 */
	public Asset getAsset() {
		return asset;
	}
	/**
	 * @param asset the asset to set
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
}
