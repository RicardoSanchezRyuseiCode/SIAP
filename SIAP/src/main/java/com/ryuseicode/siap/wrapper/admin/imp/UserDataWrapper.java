package com.ryuseicode.siap.wrapper.admin.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.admin.UserData;
import com.ryuseicode.siap.entity.admin.UserDataAdministrativeUnit;
import com.ryuseicode.siap.entity.admin.UserDataRole;
import com.ryuseicode.siap.service.admin.imp.UserDataAdministrativeUnitService;
import com.ryuseicode.siap.service.admin.imp.UserDataRoleService;
import com.ryuseicode.siap.service.admin.imp.UserDataService;
import com.ryuseicode.siap.wrapper.admin.intf.IUserDataWrapper;

/**
 * @name UserDataWrapper
 * {@summary Wrapper class to make the operations of UserDataWrapper}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
@Service
@Transactional
public class UserDataWrapper implements IUserDataWrapper {
	/**
	 * UserDataService
	 */
	@Autowired
	private UserDataService userDataService;
	/**
	 * UserDataRoleService
	 */
	@Autowired
	private UserDataRoleService userDataRoleService;
	/**
	 * UserDataAdministrativeUnitService
	 */
	@Autowired
	private UserDataAdministrativeUnitService userDataAdministrativeUnitService;
	/**
	 * @name Save
	 * {@summary Method to save an UserDataWrapper}
	 * @param userData
	 * @param roleId
	 * @param administrativeUnitId
	 * @throws Exception 
	 */
	public void Save(UserData userData, int roleId, int administrativeUnitId) throws Exception {
		// Save user data 
		this.userDataService.Save(userData);
		// Save role relation
		this.userDataRoleService.Save(new UserDataRole(userData.getUserDataId(), roleId));
		// Save  administrative unit relation
		this.userDataAdministrativeUnitService.Save(new UserDataAdministrativeUnit(userData.getUserDataId(), administrativeUnitId));
	}
	/**
	 * @name Update
	 * @param userData
	 * @param roleId
	 * @param administrativeUnitId
	 * @throws Exception
	 */
	public void Update(UserData userData, int roleId, int administrativeUnitId) throws Exception {
		// Save user data 
		this.userDataService.Update(userData);
		// Save role relation
		this.userDataRoleService.Save(new UserDataRole(userData.getUserDataId(), roleId));
		// Save  administrative unit relation
		this.userDataAdministrativeUnitService.Save(new UserDataAdministrativeUnit(userData.getUserDataId(), administrativeUnitId));
	}
	
	/**
	 * @name UpdatePassword
	 * {@summary Method to update password of users}
	 * @param userData
	 */
	public void UpdatePassword(UserData userData) throws Exception {
		this.userDataService.UpdatePassword(userData);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete an UserDataId}
	 * @param userDataId
	 * @throws Exception 
	 */
	public void Delete(int userDataId) throws Exception {
		// Delete role relation
		this.userDataRoleService.Delete(userDataId);
		// Delete administrative unit relation
		this.userDataAdministrativeUnitService.Delete(userDataId);
		// Delete the user
		this.userDataService.Delete(userDataId);
	}
}
