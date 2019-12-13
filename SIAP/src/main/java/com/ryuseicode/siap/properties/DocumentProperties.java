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
}
