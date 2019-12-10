package com.ryuseicode.siap.service.admin.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.admin.UserDataRole;
import com.ryuseicode.siap.repository.admin.imp.UserDataRoleRepository;
import com.ryuseicode.siap.service.admin.intf.IUserDataRoleService;

/**
 * @name UserDataRoleService
 * {@summary Service class to implement the behavior of IUserDataRoleService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
@Service
public class UserDataRoleService implements IUserDataRoleService {
	/**
	 * UserDataRoleRepository
	 */
	@Autowired
	private UserDataRoleRepository userDataRoleRepository;
	/**
	 * @name GetByIds
	 * {@summary Method to get UserData Role relation by ids}
	 * @param userDataId
	 * @param roleId
	 * @return UserDataRol
	 */
	public UserDataRole GetByIds(int userDataId, int roleId) { 
		return this.userDataRoleRepository.GetByIds(userDataId, roleId);
	}
	/**
	 * @name Save
	 * {@summary Method to save UserDataRole}
	 * @param userDataRole
	 */
	public void Save(UserDataRole userDataRole) {
		// Delete the current roles of user
		this.userDataRoleRepository.Delete(userDataRole.getUserDataId());
		// Save the relation
		this.userDataRoleRepository.Save(userDataRole);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataRole}
	 * @param userDataId
	 */
	public void Delete(int userDataId) {
		this.userDataRoleRepository.Delete(userDataId);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataRole}
	 * @param userDataId
	 * @param roleId
	 */
	public void Delete(int userDataId, int roleId) {
		this.userDataRoleRepository.Delete(userDataId, roleId);
	}
}
