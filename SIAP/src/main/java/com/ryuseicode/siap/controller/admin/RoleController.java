package com.ryuseicode.siap.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryuseicode.siap.entity.admin.Role;
import com.ryuseicode.siap.service.admin.imp.RoleService;

/**
 * @name RoleController
 * {@summary Controller class to expose Role endpoints }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
@Controller
public class RoleController {
	/**
	 * RoleService
	 */
	@Autowired
	private RoleService roleService;
	/**
	 * @name Get
	 * {@summary Method to get a list of roles}
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/admin/role/get", produces = "application/json")	
    public List<Role> get() {
		 return this.roleService.Get();
    }
}
