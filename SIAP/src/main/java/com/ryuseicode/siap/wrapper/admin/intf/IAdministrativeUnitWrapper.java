package com.ryuseicode.siap.wrapper.admin.intf;

/**
 * @name IAdministrativeUnitWrapper
 * {@summary Interface to define the behavior of IAdministrativeUnitWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 28, 2019
 */
public interface IAdministrativeUnitWrapper {
	/**
	 * @name Delete
	 * {@summary Method to delete an administrative unit}
	 * @param administrativeUnitId
	 * @throws Exception
	 */
	void Delete(int administrativeUnitId) throws Exception;
}
