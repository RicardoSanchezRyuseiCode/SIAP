package com.ryuseicode.siap.repository.admin.intf;

import com.ryuseicode.siap.entity.admin.UserDataRole;

/**
 * @name IUserDataRoleRepository
 * {@summary Interface to define the behavior of IUserDataRoleRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
public interface IUserDataRoleRepository {
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
	int Save(UserDataRole userDataRole);
	/**
	 * @name Delete
	 * {@summary Method to delete roles of user}
	 * @param userDataId
	 * @return
	 */
	int Delete(int userDataId);
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataRole}
	 * @param userDataId
	 * @param roleId
	 */
	int Delete(int userDataId, int roleId);
}
