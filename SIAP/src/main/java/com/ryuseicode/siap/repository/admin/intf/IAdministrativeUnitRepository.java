package com.ryuseicode.siap.repository.admin.intf;

import java.util.List;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;

/**
 * @name IAdministrativeUnitRepository
 * {@summary Class to define the behavior of AdministrativeUnitRepository}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
public interface IAdministrativeUnitRepository {
	/**
	 * @name Get
	 * {@summary Method to get a collection of AdministrativeUnit}
	 * @return collection of AdministrativeUnit
	 */
	List<AdministrativeUnit> Get();
	/**
	 * @name GetByAdministrativeUnitId
	 * {@summary Method to get userData by id}
	 * @param userDataId
	 * @return
	 */
	AdministrativeUnit GetByAdministrativeUnitId(int administrativeUnitId);
	/**
	 * @name GetByCode
	 * {@summary Method to get a userData by code}
	 * @param nickname
	 * @return
	 */
	AdministrativeUnit GetByCode(String code);
	/**
	 * @name GetByCodeDifferentId
	 * @param code
	 * @param administrativeUnitId
	 * @return
	 */
	AdministrativeUnit GetByCodeDifferentId(String code, int administrativeUnitId);
	/**
	 * @name GetByUserDataId
	 * {@summary Method to get by userId }
	 * @param userId
	 * @return
	 */
	AdministrativeUnit GetByUserDataId(int userDataId);
	/**
	 * @name GetByAuthorizer
	 * @return
	 */
	AdministrativeUnit GetByAuthorizer();
	/**
	 * @name Save
	 * @param AdministrativeUnit
	 */
	int Save(AdministrativeUnit administrativeUnit);
	/**
	 * @name Update
	 * @param AdministrativeUnit
	 */
	int Update(AdministrativeUnit administrativeUnit);
	/**
	 * @name Delete
	 * @param administrativeUnitId
	 */
	int Delete(int administrativeUnit);
}
