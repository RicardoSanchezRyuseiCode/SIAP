package com.ryuseicode.siap.paramoutput.admin;

import com.ryuseicode.siap.entity.admin.UserData;
/**
 * @name UserDataParamOutput
 * {@summary Param output class for userdata }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 30, 2019
 */
public class UserDataParamOutput {
	/**
	 * userData
	 */
	private UserData userData;
	/**
	 * roleId
	 */
	private int roleId;
	/**
	 * administrativeUnitId
	 */
	private int administrativeUnitId;
	/**
	 * @name UserDataCreateParam
	 * {@abstract Default constructor}
	 * @param userData
	 * @param roleId
	 * @param administrativeUnitId
	 */
	public UserDataParamOutput(UserData userData, int roleId, int administrativeUnitId) {
		this.setUserData(userData);
		this.setRoleId(roleId);
		this.setAdministrativeUnitId(administrativeUnitId);
	}
	/**
	 * @name getUserData
	 * @return userData
	 */
	public UserData getUserData() {
		return userData;
	}
	/**
	 * @name setUserData
	 * @param userData
	 */
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	/**
	 * @name getRoleId
	 * @return roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @name setRoleId
	 * @param roleId
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	/**
	 * @name getAdministrativeUnitId
	 * @return administrativeUnitId
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
}
