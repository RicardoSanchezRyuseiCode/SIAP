package com.ryuseicode.siap.entity.award;
/**
 * @name Item
 * {@summary Entity class to modal Item }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
public class Item {
	/**
	 * ItemId
	 */
	private int itemId;
	/**
	 * ProposalId
	 */
	private int proposalId;
	/**
	 * AnnexId
	 */
	private int annexId;
	/**
	 * UnitPrice
	 */
	private double unitPrice;
	/**
	 * TotalAmount
	 */
	private double totalAmount;
	/**
	 * Winner
	 */
	private int winner;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default constructor
	 * @param itemId
	 * @param proposalId
	 * @param annexId
	 * @param unitPrice
	 * @param totalAmount
	 * @param active
	 */
	public Item(int itemId, int proposalId, int annexId, double unitPrice, double totalAmount, int winner, int active) {
		this.setItemId(itemId);
		this.setProposalId(proposalId);
		this.setAnnexId(annexId);
		this.setUnitPrice(unitPrice);
		this.setTotalAmount(totalAmount);
		this.setWinner(winner);
		this.setActive(active);
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the proposalId
	 */
	public int getProposalId() {
		return proposalId;
	}
	/**
	 * @param proposalId the proposalId to set
	 */
	public void setProposalId(int proposalId) {
		this.proposalId = proposalId;
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
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	/**
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}
	/**
	 * @param winner the winner to set
	 */
	public void setWinner(int winner) {
		this.winner = winner;
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