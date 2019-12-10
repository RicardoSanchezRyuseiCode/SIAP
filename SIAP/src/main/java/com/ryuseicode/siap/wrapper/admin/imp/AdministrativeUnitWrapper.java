package com.ryuseicode.siap.wrapper.admin.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.service.admin.imp.AdministrativeUnitService;
import com.ryuseicode.siap.service.admin.imp.UserDataAdministrativeUnitService;
import com.ryuseicode.siap.wrapper.admin.intf.IAdministrativeUnitWrapper;

/**
 * @name AdministrativeUnitWrapper
 * {@summary Class to implement the behavior of  IAdministrativeUnitWrapper }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 28, 2019
 */
@Service
@Transactional
public class AdministrativeUnitWrapper implements IAdministrativeUnitWrapper {
	/**
	 * administrativeUnitService
	 */
	@Autowired
	private AdministrativeUnitService administrativeUnitService;
	/**
	 * UserDataAdministrativeUnitService
	 */
	@Autowired
	private UserDataAdministrativeUnitService userDataAdministrativeUnitService;
	/**
	 * @name Delete
	 * {@summary Method to delete an administrative unit}
	 * @param administrativeUnitId
	 * @throws Exception
	 */
	public void Delete(int administrativeUnitId) throws Exception {
		if(this.userDataAdministrativeUnitService.GetByAdministrativeUnitId(administrativeUnitId).size() > 0)
			throw new ServiceException("La unidad administrativa no puede ser eliminada ya que tiene usuarios relacionados");
		this.administrativeUnitService.Delete(administrativeUnitId);
	}
}
