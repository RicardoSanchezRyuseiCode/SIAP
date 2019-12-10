package com.ryuseicode.siap.service.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.admin.Role;
import com.ryuseicode.siap.repository.admin.imp.RoleRepository;
import com.ryuseicode.siap.service.admin.intf.IRoleService;

/**
 * @name RoleService
 * {@summary Service class to implement the behavior of IRoleService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
@Service
public class RoleService implements IRoleService {
	/**
	 * Role Repository
	 */
	@Autowired
	private RoleRepository roleRepository;	
	/**
	 * @name Get
	 * {@summay Method to get a list of Role Objects}
	 * @return
	 */
	public List<Role> Get() {
		return this.roleRepository.Get();
	}
}
