package com.ryuseicode.siap.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("com.ryuseicode.siap.document")
public class DocumentProperties {
	/**
	 * Invitation
	 */
	private String invitation;
	/**
	 * Annex
	 */
	private String annex;
	/**
	 * Opening
	 */
	private String opening;	
	/**
	 * Comparative
	 */
	private String comparative;
	/**
	 * Judgment
	 */
	private String judgment;
	/**
	 * Contract
	 */
	private String contract;
	/**
	 * Requisition
	 */
	private String requisition;
	/**
	 * @return the invitation
	 */
	public String getInvitation() {
		return invitation;
	}

	/**
	 * @param invitation the invitation to set
	 */
	public void setInvitation(String invitation) {
		this.invitation = invitation;
	}

	/**
	 * @return the annex
	 */
	public String getAnnex() {
		return annex;
	}

	/**
	 * @param annex the annex to set
	 */
	public void setAnnex(String annex) {
		this.annex = annex;
	}

	/**
	 * @return the opening
	 */
	public String getOpening() {
		return opening;
	}

	/**
	 * @param opening the opening to set
	 */
	public void setOpening(String opening) {
		this.opening = opening;
	}

	/**
	 * @return the comparative
	 */
	public String getComparative() {
		return comparative;
	}

	/**
	 * @param comparative the comparative to set
	 */
	public void setComparative(String comparative) {
		this.comparative = comparative;
	}

	/**
	 * @return the judgment
	 */
	public String getJudgment() {
		return judgment;
	}

	/**
	 * @param judgment the judgment to set
	 */
	public void setJudgment(String judgment) {
		this.judgment = judgment;
	}

	/**
	 * @return the contract
	 */
	public String getContract() {
		return contract;
	}

	/**
	 * @param contract the contract to set
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}

	/**
	 * @return the requisition
	 */
	public String getRequisition() {
		return requisition;
	}

	/**
	 * @param requisition the requisition to set
	 */
	public void setRequisition(String requisition) {
		this.requisition = requisition;
	}
}
