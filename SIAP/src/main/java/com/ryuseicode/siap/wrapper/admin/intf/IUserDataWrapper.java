package com.ryuseicode.siap.wrapper.admin.intf;

import com.ryuseicode.siap.entity.admin.UserData;
/**
 * @name IUserDataWrapper
 * {@summary Interface to define the behavior of IUseDataWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 28, 2019
 */
public interface IUserDataWrapper {
	/**
	 * @name Save
	 * {@summary Method to save an UserDataWrapper}
	 * @param userData
	 * @param roleId
	 * @param administrativeUnitId
	 * @throws Exception 
	 */
	void Save(UserData userData, int roleId, int administrativeUnitId) throws Exception;
	/**
	 * @name Update
	 * {@summary Method to update user data}
	 * @param userData
	 * @param roleId
	 * @param administrativeUnitId
	 * @throws Exception
	 */
	void Update(UserData userData, int roleId, int administrativeUnitId) throws Exception ;
	/**
	 * @name UpdatePassword
	 * {@summary Method to update password of users}
	 * @param userData
	 */
	void UpdatePassword(UserData userData) throws Exception;
	/**
	 * @name Delete
	 * {@summary Method to delete an UserDataId}
	 * @param userDataId
	 * @throws Exception 
	 */
	void Delete(int userDataId) throws Exception;
}
