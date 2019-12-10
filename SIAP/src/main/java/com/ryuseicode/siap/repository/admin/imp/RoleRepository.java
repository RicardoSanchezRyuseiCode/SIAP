package com.ryuseicode.siap.repository.admin.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.admin.Role;
import com.ryuseicode.siap.repository.admin.intf.IRoleRepository;

/**
 * @name RoleRepository
 * {@summary Repository class for Role }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 27, 2019
 */
@Repository
public class RoleRepository implements IRoleRepository{
	/**
	 * Jdbc Template
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name Get
	 * {@summay Method to get a list of Role Objects}
	 * @return
	 */
	public List<Role> Get() {
		return jdbcTemplate.query(
                "select * from Role where active = 1",
                (rs, rowNum) ->
                        new Role(
                        		rs.getInt("roleid"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("active")
                        )
        );
		
	}
}
