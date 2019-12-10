package com.ryuseicode.siap.entity.award;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @name Institution
 * {@summary Entity class to model Institution }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 7, 2019
 */
public class Institution {
	/**
	 * InstitutionId
	 */
	private int institutionId;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Address
	 */
	private String address;
	/**
	 * Representative
	 */
	private String representative;
	/**
	 * Accreditation Number
	 */
	private String accreditationNumber;
	/**
	 * TaxId
	 */
	private String taxId;
	/**
	 * Designation
	 */
	private String designation;
	/**
	 * CreationDate
	 */
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;
	/**
	 * Active
	 */
	private int active;
	/**
	 * Default constructor
	 * @param institutionId
	 * @param name
	 * @param address
	 * @param representative
	 * @param accreditationNumber
	 * @param taxId
	 * @param designation
	 * @param creationDate
	 * @param active
	 */
	public Institution(int institutionId, String name, String address, String representative, String accreditationNumber, String taxId, String designation, LocalDateTime creationDate, int active) {
		this.setInstitutionId(institutionId);
		this.setName(name);
		this.setAddress(address);
		this.setRepresentative(representative);
		this.setAccreditationNumber(accreditationNumber);
		this.setTaxId(taxId);
		this.setDesignation(designation);
		this.setCreationDate(creationDate);
		this.setActive(active);
	}
	/**
	 * @return the institutionId
	 */
	public int getInstitutionId() {
		return institutionId;
	}
	/**
	 * @param institutionId the institutionId to set
	 */
	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the representative
	 */
	public String getRepresentative() {
		return representative;
	}
	/**
	 * @param representative the representative to set
	 */
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	/**
	 * @return the accreditationNumber
	 */
	public String getAccreditationNumber() {
		return accreditationNumber;
	}
	/**
	 * @param accreditationNumber the accreditationNumber to set
	 */
	public void setAccreditationNumber(String accreditationNumber) {
		this.accreditationNumber = accreditationNumber;
	}
	/**
	 * @return the taxId
	 */
	public String getTaxId() {
		return taxId;
	}
	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the creationDate
	 */
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
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
