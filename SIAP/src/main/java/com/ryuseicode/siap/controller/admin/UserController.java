package com.ryuseicode.siap.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.paraminput.admin.UserDataCreateParam;
import com.ryuseicode.siap.paraminput.admin.UserDataUpdateParam;
import com.ryuseicode.siap.paramoutput.admin.UserDataParamOutput;
import com.ryuseicode.siap.service.admin.imp.UserDataService;
import com.ryuseicode.siap.wrapper.admin.imp.UserDataWrapper;

/**
 * @name UserController
 * {@summary Controller class to expose home views}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-23
 */
@Controller
public class UserController {
	/**
	 * UserDataRepository
	 */
	@Autowired
    private UserDataService userDataService;	
	
	@Autowired
	private UserDataWrapper userDataWrapper;
	/**
	 * @name homeShortcut
	 * @abstract Method to return users view
	 * @param model
	 * @return home view
	 */
	@RequestMapping("/admin/users")
	public String users(Model model) {
	  return "admin/users";
	}
	/**
	 * @name Get
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/admin/users/get", produces = "application/json")	
    public List<UserDataParamOutput> get() {
		 return this.userDataService.Get();
    }
	/**
	 * @name getByNickname
	 * {@summary Method to get by nickname }
	 * @param nickname
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/admin/users/getByNickname/{nickname}", produces = "application/json")	
    public UserDataParamOutput getByNickname(@PathVariable String nickname) {
		 return this.userDataService.getByNickname(nickname);
    }
	/**
	 * @name getCurrentUser
	 * {@summary Method to get current user }
	 * @param authentication
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/admin/users/getCurrentUser", produces = "application/json")	
    public UserDataParamOutput getCurrentUser(Authentication authentication) {
		 return this.userDataService.getByNickname(authentication.getName());
    }
	/**
	 * @name post
	 * @abstract Method to save an user
	 * @param userData
	 */
	@PostMapping(path = "/admin/users/post", consumes = "application/json") 
	public void post(@RequestBody UserDataCreateParam userDataCreateParam, HttpServletResponse response) {
	    try 
	    {
			this.userDataWrapper.Save(userDataCreateParam.getUserData(), userDataCreateParam.getRoleId(), userDataCreateParam.getAdministrativeUnitId());
		} 
	    catch (ServiceException ex)
	    {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex)
	    {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al crear usuario: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name put
	 * @abstract Method to update an user
	 * @param userData
	 */
	@PutMapping(path = "/admin/users/put", consumes = "application/json") 
	public void put(@RequestBody UserDataUpdateParam userDataUpdateParam, HttpServletResponse response) {
		try 
	    {
			this.userDataWrapper.Update(userDataUpdateParam.getUserData(), userDataUpdateParam.getRoleId(), userDataUpdateParam.getAdministrativeUnitId());
		} 
	    catch (ServiceException ex)
	    {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex)
	    {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al actualizar usuario: %s", ex.getMessage()));
	    }
	}
	
	@PutMapping(path = "/admin/users/updatePassword", consumes = "application/json") 
	public void updatePassword(@RequestBody UserDataUpdateParam userDataUpdateParam, HttpServletResponse response) {
		try 
	    {
			this.userDataWrapper.UpdatePassword(userDataUpdateParam.getUserData());
		} 
	    catch (ServiceException ex)
	    {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex)
	    {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al actualizar la contraseña del usuario: %s", ex.getMessage()));
	    }
	}
	/**
	 * @name delete
	 * @abstract Method to update an user
	 * @param userDataId
	 */
	@DeleteMapping(path = "/admin/users/delete/{userDataId}", consumes = "application/json") 
	public ResponseEntity<Object> delete(@PathVariable int userDataId) {
		try 
	    {
			this.userDataWrapper.Delete(userDataId);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} 
	    catch (ServiceException ex)
	    {	
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	    catch(Exception ex)
	    {
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Error al eliminar el usuario %s", ex.getMessage()));
	    }
	}
}
