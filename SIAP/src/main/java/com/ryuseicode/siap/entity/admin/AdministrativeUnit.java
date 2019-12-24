package com.ryuseicode.siap.entity.admin;

/**
 * @name AdministrativeUnit
 * {@summary Entity class to model Administrative unit}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
public class AdministrativeUnit {
	/**
	 * administrativeUnitId
	 */
	private int administrativeUnitId;	
	/**
	 * code
	 */
	private String code;	
	/**
	 * description
	 */
	private String description;
	/**
	 * email
	 */
	private String email;
	/**
	 * authorizer
	 */
	private int authorizer;
	/**
	 * active
	 */
	private int active;
	
	/**
	 * @name AdministrativeUnit
	 * @param administrativeUnitId
	 * @param code
	 * @param description
	 * @param active
	 */
	public AdministrativeUnit(int administrativeUnitId, String code, String description, String email, int authorizer, int active) {
		this.setAdministrativeUnitId(administrativeUnitId);
		this.setCode(code);
		this.setDescription(description);
		this.setEmail(email);
		this.setAuthorizer(authorizer);
		this.setActive(active);
	}
	/**
	 * @name getAdministrativeUnitId
	 * @return
	 */
	public int getAdministrativeUnitId() {
		return administrativeUnitId;
	}
	/**
	 * @name setAdministrativeUnitId
	 * @param administrativeUnitId
	 */
	public void setAdministrativeUnitId(int administrativeUnitId) {
		this.administrativeUnitId = administrativeUnitId;
	}
	/**
	 * @name getCode
	 * @return
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @name setCode
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @name getDescription
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @name setDescription
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * getActive
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
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the authorizer
	 */
	public int getAuthorizer() {
		return authorizer;
	}
	/**
	 * @param authorizer the authorizer to set
	 */
	public void setAuthorizer(int authorizer) {
		this.authorizer = authorizer;
	}
}
