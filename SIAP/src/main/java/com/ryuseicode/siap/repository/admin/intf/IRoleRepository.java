package com.ryuseicode.siap.repository.admin.intf;

import java.util.List;

import com.ryuseicode.siap.entity.admin.Role;

/**
 * @name IRoleRepository
 * {@summary Interface to define the behavior of IRoleRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
public interface IRoleRepository {
	/**
	 * @name Get
	 * {@summay Method to get a list of Role Objects}
	 * @return
	 */
	List<Role> Get();	
}
