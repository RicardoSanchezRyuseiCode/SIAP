package com.ryuseicode.siap.entity.admin;

/**
 * @name Role
 * {@summary Entity class to model Role}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 26, 2019
 */
public class Role {
	
	/**
	 * RoleId
	 */
	private int roleId;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Description
	 */
	private String description;
	/**
	 * Active
	 */
	private int active;
	
	/**
	 * @name Role
	 * {@summary Construtor}
	 * @param roleId
	 * @param name
	 * @param description
	 * @param active
	 */
	public Role(int roleId, String name, String description, int active)
	{
		this.setRoleId(roleId);
		this.setName(name);
		this.setDescription(description);
		this.setActive(active);
	}
	/**
	 * @name getRoleId
	 * @return roleId
	 */
	public int getRoleId() {
		return roleId;
	}
	/**
	 * @name setRoleId
	 * @param roleId
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	/**
	 * @name getName
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @name setName
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @name getDescription
	 * @return description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @name setDescription
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @name getActive
	 * @return active
	 */
	public int getActive() {
		return active;
	}
	/**
	 * @name setActive
	 * @param active
	 */
	public void setActive(int active) {
		this.active = active;
	}
}