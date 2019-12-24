package com.ryuseicode.siap.entity.requisition;
/**
 * @name RequestDetail
 * {@summary Entity to model request detail }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 21, 2019
 */
public class RequestDetail {
	/**
	 * RequestDetailId
	 */
	private int requestDetailId;
	/**
	 * RequestId
	 */
	private int requestId;
	/**
	 * Asset
	 */
	private String asset;
	/**
	 * Quantity
	 */
	private int quantity;
	/**
	 * UnitPrice
	 */
	private double unitPrice;
	/**
	 * Total
	 */
	private double total;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @param requestDetailId
	 * @param requestId
	 * @param asset
	 * @param quantity
	 * @param unitPrice
	 * @param total
	 * @param active
	 */
	public RequestDetail(int requestDetailId, int requestId, String asset, int quantity, double unitPrice, double total,
			int active) {
		super();
		this.requestDetailId = requestDetailId;
		this.requestId = requestId;
		this.asset = asset;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.total = total;
		this.active = active;
	}
	/**
	 * @return the requestDetailId
	 */
	public int getRequestDetailId() {
		return requestDetailId;
	}
	/**
	 * @param requestDetailId the requestDetailId to set
	 */
	public void setRequestDetailId(int requestDetailId) {
		this.requestDetailId = requestDetailId;
	}
	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	/**
	 * @return the asset
	 */
	public String getAsset() {
		return asset;
	}
	/**
	 * @param asset the asset to set
	 */
	public void setAsset(String asset) {
		this.asset = asset;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
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
