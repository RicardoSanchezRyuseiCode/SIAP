package com.ryuseicode.siap.entity.admin;
/**
 * @name UserDataRole
 * {@summary Entity class to model UserDataRol }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 26, 2019
 */
public class UserDataRole {
	/**
	 * UserDataId
	 */
	private int userDataId;
	/**
	 * RoleId
	 */
	private int roleId;
	/**
	 * @name UserDataRole
	 * {@summary Default constructor}
	 * @param userDataId
	 * @param roleId
	 */
	public UserDataRole(int userDataId, int roleId) {
		this.setUserDataId(userDataId);
		this.setRoleId(roleId);
	}
	/**
	 * @name getUserDataId
	 * @return
	 */
	public int getUserDataId() {
		return this.userDataId;
	}
	/**
	 * @name setUserDataId
	 * @param userDataId
	 */
	public void setUserDataId(int userDataId) {
		this.userDataId = userDataId;
	}
	/**
	 * @name getRoleId
	 * @return
	 */
	public int getRoleId() {
		return this.roleId;
	}
	/**
	 * @name serRoleId
	 * @param roleId
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
}
