package com.ryuseicode.siap.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ryuseicode.siap.entity.admin.AdministrativeUnit;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.service.admin.imp.AdministrativeUnitService;
import com.ryuseicode.siap.wrapper.admin.imp.AdministrativeUnitWrapper;

/**
 * @name AdministrativeUnitController
 * {@summary Controller for AdministrativeUnit}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-25
 */
@Controller
public class AdministrativeUnitController {
	/**
	 * AdministrativeUnitRepository
	 */
	@Autowired
    private AdministrativeUnitService administrativeUnitService;	
	
	@Autowired
	private AdministrativeUnitWrapper administrativeUnitWrapper;
	/**
	 * @name homeShortcut
	 * @abstract Method to return users view
	 * @param model
	 * @return home view
	 */
	@RequestMapping("/admin/administrativeUnit")
	public String administrativeUnit(Model model) {
	  return "admin/administrativeUnit";
	}
	/**
	 * @name Get
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/admin/administrativeUnit/get", produces = "application/json")	
    public List<AdministrativeUnit> get() {
		 return this.administrativeUnitService.Get();
    }
	/**
	 * @name getByUserDataId
	 * {@summary Method to get by user data id }
	 * @param userDataId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/admin/administrativeUnit/getByUserDataId/{userDataId}", produces = "application/json")	
    public AdministrativeUnit getByUserDataId(@PathVariable int userDataId) {
		 return this.administrativeUnitService.GetByUserDataId(userDataId);
    }
	/**
	 * @name post
	 * @abstract Method to save an user
	 * @param userData
	 */
	@PostMapping(path = "/admin/administrativeUnit/post", consumes = "application/json") 
	public void post(@RequestBody AdministrativeUnit administrativeUnit, HttpServletResponse response) {
	    try 
	    {
			this.administrativeUnitWrapper.save(administrativeUnit);
		} 
	    catch (ServiceException ex)
	    {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex)
	    {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al guardar unidad administrativa: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name put
	 * @abstract Method to update an user
	 * @param userData
	 */
	@PutMapping(path = "/admin/administrativeUnit/put", consumes = "application/json") 
	public void put(@RequestBody AdministrativeUnit administrativeUnit, HttpServletResponse response) {
		try 
	    {
			this.administrativeUnitWrapper.update(administrativeUnit);
		} 
	    catch (ServiceException ex)
	    {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex)
	    {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al actualizar unidad administrativa: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name delete
	 * @abstract Method to update an user
	 * @param userDataId
	 */
	@DeleteMapping(path = "/admin/administrativeUnit/delete/{administrativeUnitId}", consumes = "application/json") 
	public ResponseEntity<Object> delete(@PathVariable int administrativeUnitId) {
		try 
	    {
			this.administrativeUnitWrapper.Delete(administrativeUnitId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} 
	    catch (ServiceException ex)
	    {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex)
	    {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al eliminar unidad administrativa: %s", ex.getMessage()));
	    }
	}
}
