package com.ryuseicode.siap.service.admin.intf;

import java.util.List;

import com.ryuseicode.siap.entity.admin.Role;

/**
 * @name IRoleService
 * {@summary Service class for Role Service }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
public interface IRoleService {
	/**
	 * @name Get
	 * {@summay Method to get a list of Role Objects}
	 * @return
	 */
	List<Role> Get();
}
