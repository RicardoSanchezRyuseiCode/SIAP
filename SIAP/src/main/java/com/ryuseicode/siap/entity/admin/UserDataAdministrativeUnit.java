package com.ryuseicode.siap.entity.admin;
/**
 * @name UserDataAdministrativeUnit
 * {@summary Entity class to model UserDataAdministrativeUnit}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 26, 2019
 */
public class UserDataAdministrativeUnit {
	/**
	 * UserDataId
	 */
	private int userDataId;
	/**
	 * AdministrativeUnitId
	 */
	private int administrativeUnitId;
	/**
	 * @name UserDataAdministrativeUnit
	 * {@summary Default constructor}
	 * @param userDataId
	 * @param administrativeUnitId
	 */
	public UserDataAdministrativeUnit(int userDataId, int administrativeUnitId) {
		this.setUserDataId(userDataId);
		this.setAdministrativeUnitId(administrativeUnitId);
	}
	/**
	 * @getUserDataId
	 * @return userDataId
	 */
	public int getUserDataId() {
		return userDataId;
	}
	/**
	 * @name setUserDataId
	 * @param userDataId
	 */
	public void setUserDataId(int userDataId) {
		this.userDataId = userDataId;
	}
	/**
	 * @name getAdministrativeUnitId
	 * @return
	 */
	public int getAdministrativeUnitId() {
		return administrativeUnitId;
	}
	/**
	 * @name setAdministrativeUnit
	 * @param administrativeUnitId
	 */
	public void setAdministrativeUnitId(int administrativeUnitId) {
		this.administrativeUnitId = administrativeUnitId;
	}	
}
