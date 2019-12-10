package com.ryuseicode.siap.service.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.admin.UserDataAdministrativeUnit;
import com.ryuseicode.siap.repository.admin.imp.UserDataAdministrativeUnitRepository;
import com.ryuseicode.siap.service.admin.intf.IUserDataAdministrativeUnitService;

@Service
public class UserDataAdministrativeUnitService implements IUserDataAdministrativeUnitService {
	/**
	 * userDataAdministrativeUnitRepository
	 */
	@Autowired
	private UserDataAdministrativeUnitRepository userDataAdministrativeUnitRepository;
	/**
	 * @name GetByIds
	 * {@summary Method to get UserData AdministrativeUnit relation by ids}
	 * @param userDataId
	 * @param administrativeUnitId
	 * @return UserDataAdministrativeUnit
	 */
	public UserDataAdministrativeUnit GetByIds(int userDataId, int administrativeUnitId) {
		return this.userDataAdministrativeUnitRepository.GetByIds(userDataId, administrativeUnitId);
	}
	/**
	 * @name GetByAdministrativeUnitId
	 * @param administrativeUnitId
	 * @return Collection of UserAdministrativeUnit
	 */
	public List<UserDataAdministrativeUnit> GetByAdministrativeUnitId(int administrativeUnitId) {
		return this.userDataAdministrativeUnitRepository.GetByAdministrativeUnitId(administrativeUnitId);
	}
	/**
	 * @name Save
	 * {@summary Method to save UserDataAdministrativeUnit}
	 * @param userDataRole
	 */
	public void Save(UserDataAdministrativeUnit userDataAdministrativeUnit) {
		// Delete the current relation
		this.userDataAdministrativeUnitRepository.Delete(userDataAdministrativeUnit.getUserDataId());
		// Save the relation
		this.userDataAdministrativeUnitRepository.Save(userDataAdministrativeUnit);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataAdministrativeUnit}
	 * @param userDataId
	 */
	public void Delete(int userDataId) {
		this.userDataAdministrativeUnitRepository.Delete(userDataId);
	}
	/**
	 * @name Delete
	 * {@summary Method to delete UserDataAdministrativeUnit}
	 * @param userDataId
	 * @param administrativeUnitId
	 */
	public void Delete(int userDataId, int administrativeUnitId) {
		this.userDataAdministrativeUnitRepository.Delete(userDataId, administrativeUnitId);
	}
}
