package com.ryuseicode.siap.service.admin.intf;

import java.util.List;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;

/**
 * @name IAdministrativeUnitService
 * {@summary Interface to define the behavior of IAdministrativeUnitService}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
public interface IAdministrativeUnitService {
	/**
	 * @name Get
	 * {@summary Method to get a collection of user data}
	 * @return collection of user data
	 */
	List<AdministrativeUnit> Get();
	/**
	 * @name Save
	 * @param AdministrativeUnit
	 * @throws Exception 
	 */
	int Save(AdministrativeUnit administrativeUnit) throws Exception;
	/**
	 * @name Update
	 * @param AdministrativeUnit
	 * @throws Exception 
	 */
	int Update(AdministrativeUnit administrativeUnit) throws Exception;
	/**
	 * @name Delete
	 * @param AdministrativeUnit
	 * @throws Exception 
	 */
	int Delete(int administrativeUnitId) throws Exception;
}
