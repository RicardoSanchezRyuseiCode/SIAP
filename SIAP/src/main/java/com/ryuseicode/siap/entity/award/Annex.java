package com.ryuseicode.siap.entity.award;
/**
 * @name Annex
 * {@summary Entity class to model annex }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 8, 2019
 */
public class Annex {
	/**
	 * AnnexId
	 */
	private int annexId;
	/**
	 * AdjudicationId
	 */
	private int adjudicationId;
	/**
	 * AssetId
	 */
	private int assetId;
	/**
	 * Quantity
	 */
	private double quantity;
	/**
	 * DeliveryTerm
	 */
	private String deliveryTerm;
	/**
	 * DeliveryPlace
	 */
	private String deliveryPlace;	
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default constructor
	 * @param annexId
	 * @param adjudicationId
	 * @param assetId
	 * @param quantity
	 * @param deliveryPlace
	 * @param deliveryDate
	 * @param active
	 */
	public Annex(int annexId, int adjudicationId, int assetId, double quantity, String deliveryTerm, String deliveryPlace, int active) {
		this.setAnnexId(annexId);
		this.setAdjudicationId(adjudicationId);
		this.setAssetId(assetId);
		this.setQuantity(quantity);
		this.setDeliveryTerm(deliveryTerm);
		this.setDeliveryPlace(deliveryPlace);
		this.setActive(active);
	}
	/**
	 * @return the annexId
	 */
	public int getAnnexId() {
		return annexId;
	}
	/**
	 * @param annexId the annexId to set
	 */
	public void setAnnexId(int annexId) {
		this.annexId = annexId;
	}
	/**
	 * @return the adjudicationId
	 */
	public int getAdjudicationId() {
		return adjudicationId;
	}
	/**
	 * @param adjudicationId the adjudicationId to set
	 */
	public void setAdjudicationId(int adjudicationId) {
		this.adjudicationId = adjudicationId;
	}
	/**
	 * @return the assetId
	 */
	public int getAssetId() {
		return assetId;
	}
	/**
	 * @param assetId the assetId to set
	 */
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the deliveryPlace
	 */
	public String getDeliveryPlace() {
		return deliveryPlace;
	}
	/**
	 * @param deliveryPlace the deliveryPlace to set
	 */
	public void setDeliveryPlace(String deliveryPlace) {
		this.deliveryPlace = deliveryPlace;
	}
	/**
	 * @return the deliveryDate
	 */
	public String getDeliveryTerm() {
		return deliveryTerm;
	}
	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryTerm(String deliveryTerm) {
		this.deliveryTerm = deliveryTerm;
	}
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}
}
