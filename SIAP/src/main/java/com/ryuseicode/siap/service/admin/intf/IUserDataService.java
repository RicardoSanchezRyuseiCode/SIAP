package com.ryuseicode.siap.service.admin.intf;

import java.util.List;

import com.ryuseicode.siap.entity.admin.UserData;
import com.ryuseicode.siap.paramoutput.admin.UserDataParamOutput;

/**
 * @name IUserDataService
 * {@summary Interface class to define behavior of IUserDataService}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
public interface IUserDataService {
	/**
	 * @name Get
	 * {@summary Method to get a collection of user data}
	 * @return collection of user data
	 */
	List<UserDataParamOutput> Get();
	/**
	 * @name getByNickname
	 * {@summary Method to get by nickname }
	 * @param nickname
	 * @return
	 */
	UserDataParamOutput getByNickname(String nickname);
	/**
	 * @name Save
	 * @param userData
	 * @throws Exception 
	 */
	void Save(UserData userData) throws Exception;
	/**
	 * @name Update
	 * @param userData
	 */
	int Update(UserData userData) throws Exception;
	/**
	 * @name UpdatePassword
	 * {@summary Method to update password of users}
	 * @param userData
	 */
	int UpdatePassword(UserData userData) throws Exception;
	/**
	 * @name Delete
	 * @param userDataId
	 */
	int Delete(int userDataId) throws Exception;
}
