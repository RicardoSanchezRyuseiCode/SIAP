package com.ryuseicode.siap.service.admin.intf;

import java.util.List;

import com.ryuseicode.siap.entity.admin.UserDataAdministrativeUnit;

/**
 * @name IUserDataAdministrativeUnitService
 * {@summary Interface to define the behavior of }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
public interface IUserDataAdministrativeUnitService {
	/**
	 * @name GetByIds
	 * {@summary Method to get UserData AdministrativeUnit relation by ids}
	 * @param userDataId
	 * @param administrativeUnitId
	 * @return UserDataAdministrativeUnit
	 */
	UserDataAdministrativeUnit GetByIds(int userDataId, int administrativeUnitId);
	/**
	 * @name GetByAdministrativeUnitId
	 * @param administrativeUnitId
	 * @return Collection of UserAdministrativeUnit
	 */
	List<UserDataAdministrativeUnit> GetByAdministrativeUnitId(int administrativeUnitId);
	/**
	 * @name Save
	 * {@summary Method to save UserDataAdministrativeUnit}
	 * @param userDataRole
	 */
	void Save(UserDataAdministrativeUnit userDataAdministrativeUnit);
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataAdministrativeUnit}
	 * @param userDataId
	 */
	void Delete(int userDataId);
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataAdministrativeUnit}
	 * @param userDataId
	 * @param administrativeUnitId
	 */
	void Delete(int userDataId, int administrativeUnitId);
}
