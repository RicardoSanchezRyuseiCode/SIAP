package com.ryuseicode.siap.repository.admin.intf;

import java.util.List;

import com.ryuseicode.siap.entity.admin.UserData;
import com.ryuseicode.siap.paramoutput.admin.UserDataParamOutput;
/**
 * @name IUserDataRepository
 * {@summary Interface to define the behavior of IUserData }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
public interface IUserDataRepository {
	/**
	 * @name Get
	 * {@summary Method to get a collection of user data}
	 * @return collection of user data
	 */
	List<UserDataParamOutput> Get();
	/**
	 * @name GetByUserDataId
	 * {@summary Method to get userData by id}
	 * @param userDataId
	 * @return
	 */
	UserDataParamOutput GetByUserDataId(int userDataId);	
	/**
	 * @name GetByRowId
	 * {@summary Method to get user data by rowid }
	 * @param rowId
	 * @return
	 */
	UserData GetByRowId(String rowId);
	/**
	 * @name GetByNickName
	 * {@summary Method to get a userData by nickname}
	 * @param nickname
	 * @return
	 */
	UserDataParamOutput GetByNickname(String nickname);
	/**
	 * @name Save
	 * @param userData
	 */
	String Save(UserData userData);
	/**
	 * @name Update
	 * @param userData
	 */
	int Update(UserData userData);
	/**
	 * @name UpdatePassword
	 * {@summary Method to update password}
	 * @param userData
	 * @return
	 */
	int UpdatePassword(UserData userData);
	/**
	 * @name Delete
	 * @param userDataId
	 */
	int Delete(int userDataId);
}
