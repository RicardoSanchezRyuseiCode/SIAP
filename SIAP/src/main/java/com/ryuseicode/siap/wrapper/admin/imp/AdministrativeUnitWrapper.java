package com.ryuseicode.siap.wrapper.admin.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;
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
	 * @name save
	 * @abstract Method to save an administrativeunit
	 * @param administrativeUnit
	 */
	public void save(AdministrativeUnit administrativeUnit) throws Exception {
		if(administrativeUnit.getAuthorizer() == 1) {
			// Get current authorizet
			AdministrativeUnit authorizer = this.administrativeUnitService.GetByAuthorizer();
			if(authorizer != null) {
				authorizer.setAuthorizer(0);
				authorizer.setEmail(null);
				this.administrativeUnitService.Update(authorizer);
				this.administrativeUnitService.Save(administrativeUnit);
			}
		}
		else {
			this.administrativeUnitService.Save(administrativeUnit);
		}
	}
	
	/**
	 * @name update
	 * {@summary Method to update an administrative unit }
	 * @param administrativeUnit
	 * @throws Exception
	 */
	public void update(AdministrativeUnit administrativeUnit) throws Exception {
		if(administrativeUnit.getAuthorizer() == 1) {
			// Get current authorizet
			AdministrativeUnit authorizer = this.administrativeUnitService.GetByAuthorizer();
			if(authorizer != null) {
				authorizer.setAuthorizer(0);
				authorizer.setEmail(null);
				this.administrativeUnitService.Update(authorizer);
				this.administrativeUnitService.Update(administrativeUnit);
			}
		}
		else {
			this.administrativeUnitService.Update(administrativeUnit);
		}
	}
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
