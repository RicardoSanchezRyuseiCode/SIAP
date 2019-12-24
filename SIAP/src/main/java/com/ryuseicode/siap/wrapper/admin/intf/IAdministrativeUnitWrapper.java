package com.ryuseicode.siap.wrapper.admin.intf;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;

/**
 * @name IAdministrativeUnitWrapper
 * {@summary Interface to define the behavior of IAdministrativeUnitWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 28, 2019
 */
public interface IAdministrativeUnitWrapper {
	/**
	 * @name save
	 * {@summary Method to save an administrative unit }
	 * @param administrativeUnit
	 * @throws Exception
	 */
	void save(AdministrativeUnit administrativeUnit) throws Exception;
	/**
	 * @name update
	 * {@summary Method to update an administrative unit }
	 * @param administrativeUnit
	 * @throws Exception
	 */
	void update(AdministrativeUnit administrativeUnit) throws Exception;
	/**
	 * @name Delete
	 * {@summary Method to delete an administrative unit}
	 * @param administrativeUnitId
	 * @throws Exception
	 */
	void Delete(int administrativeUnitId) throws Exception;
}
