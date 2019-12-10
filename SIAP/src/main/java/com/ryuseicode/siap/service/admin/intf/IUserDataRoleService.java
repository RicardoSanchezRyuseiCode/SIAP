package com.ryuseicode.siap.service.admin.intf;

import com.ryuseicode.siap.entity.admin.UserDataRole;

/**
 * @name IUserDataRoleService
 * {@summary Interface to define the behavior of IUserDataRole Service }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
public interface IUserDataRoleService {
	/**
	 * @name GetByIds
	 * {@summary Method to get UserData Role relation by ids}
	 * @param userDataId
	 * @param roleId
	 * @return UserDataRol
	 */
	UserDataRole GetByIds(int userDataId, int roleId);
	/**
	 * @name Save
	 * {@summary Method to save UserDataRole}
	 * @param userDataRole
	 */
	void Save(UserDataRole userDataRole);
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataRole}
	 * @param userDataId
	 */
	void Delete(int userDataId);
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataRole}
	 * @param userDataId
	 * @param roleId
	 */
	void Delete(int userDataId, int roleId);
}
