package com.ryuseicode.siap.entity.admin;
/**
 * @name UserData
 * {@summary Entity class to model user data}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Nov 26, 2019
 */
public class UserData {
	/**
	 * @name UserDataId
	 */
	private int userDataId;	
	/**
	 * @name nickname
	 */
	private String nickname;	
	/**
	 * @name name
	 */
	private String name;	
	/**
	 * @name password
	 */
	private String password;	
	/**
	 * @name active
	 */
	private int active;	
	/**
	 * @name UserData
	 * {@summary Default constructor}
	 * @param nickname
	 * @param name
	 * @param password
	 * @param active
	 */
	public UserData(int userDataId, String nickname, String name, String password, int active)
	{
		this.setUserDataId(userDataId);
		this.setNickname(nickname);
		this.setName(name);
		this.setPassword(password);
		this.setActive(active);
	}
	/**
	 * @name getUserDataId
	 * @return
	 */
	public int getUserDataId() {
		return userDataId;
	}
	/**
	 * getUserDataId
	 * @param userDataId
	 */
	public void setUserDataId(int userDataId) {
		this.userDataId = userDataId;
	}
	/**
	 * @name getNickname
	 * @return
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @name setNickname
	 * @return
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @name getName
	 * @return
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
	 * @name getPassword
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @name setPassword
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @name getActive
	 * @return
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
