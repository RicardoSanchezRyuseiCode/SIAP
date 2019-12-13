package com.ryuseicode.siap.paramoutput.award;

import com.ryuseicode.siap.entity.award.Annex;
import com.ryuseicode.siap.entity.award.Item;

public class ItemParamOutput {
	/**
	 * Item
	 */
	private Item item;
	/**
	 * Annex
	 */
	private Annex annex;
	/**
	 * Default constructor
	 * @param item
	 * @param annex
	 */
	public ItemParamOutput(Item item, Annex annex) {
		this.setItem(item);
		this.setAnnex(annex);
	}
	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
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
}