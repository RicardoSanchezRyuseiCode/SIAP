package com.ryuseicode.siap.service.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.admin.imp.AdministrativeUnitRepository;
import com.ryuseicode.siap.service.admin.intf.IAdministrativeUnitService;

/**
 * @name AdministrativeUnitService
 * {@summary Class to implement the behavior of IAdministrativeUnitService}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
@Service
public class AdministrativeUnitService implements IAdministrativeUnitService {
	/**
	 * administrativeUnitRepository
	 */
	@Autowired
	public AdministrativeUnitRepository administrativeUnitRepository;	
	/**
	 * @name Get
	 * {@summary Method to get a collection of user data}
	 * @return collection of user data
	 */
	public List<AdministrativeUnit> Get() {
		return this.administrativeUnitRepository.Get();
	}
	/**
	 * @name GetByAuthorizer
	 * @return
	 */
	public AdministrativeUnit GetByAuthorizer() {
		return this.administrativeUnitRepository.GetByAuthorizer();
	}
	/**
	 * @name GetByUserDataId
	 * {@summary Method to get by userId }
	 * @param userId
	 * @return
	 */
	public AdministrativeUnit GetByUserDataId(int userDataId) {
		return this.administrativeUnitRepository.GetByUserDataId(userDataId);
	}
	/**
	 * @name Save
	 * @param AdministrativeUnit
	 * @throws Exception 
	 */
	public int Save(AdministrativeUnit administrativeUnit) throws Exception {
		// Check if another administrative unit
		if(this.administrativeUnitRepository.GetByCode(administrativeUnit.getCode()) != null)
			throw new ServiceException("Ya existe una unidad administrativa con el mismo código");
		// Set default data
		administrativeUnit.setActive(1);
		// Save user
		return this.administrativeUnitRepository.Save(administrativeUnit);
	}
	/**
	 * @name Update
	 * @param AdministrativeUnit
	 * @throws Exception 
	 */
	public int Update(AdministrativeUnit administrativeUnit) throws Exception {
		// Check if another administrative unit
		if(this.administrativeUnitRepository.GetByAdministrativeUnitId(administrativeUnit.getAdministrativeUnitId()) == null)
			throw new ServiceException("No se ha encontrado la unidad administrativa a actualizar");
		// Check if another administrative unit
		if(this.administrativeUnitRepository.GetByCodeDifferentId(administrativeUnit.getCode(), administrativeUnit.getAdministrativeUnitId()) != null)
			throw new ServiceException("Ya existe una unidad administrativa con el mismo código");				
		// Update administrative unit
		return this.administrativeUnitRepository.Update(administrativeUnit);
	}
	/**
	 * @name Delete
	 * @param AdministrativeUnit
	 * @throws Exception 
	 */
	public int Delete(int administrativeUnitId) throws Exception {
		// Check if another administrative unit
		if(this.administrativeUnitRepository.GetByAdministrativeUnitId(administrativeUnitId) == null)
			throw new ServiceException("No se ha encontrado la unidad administrativa a eliminar");
		// Update administrative unit
		return this.administrativeUnitRepository.Delete(administrativeUnitId);
	}

}
