package com.ryuseicode.siap.entity.award;

/**
 * @name Supplier
 * {@summary }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public class Supplier {
	/**
	 * SupplierId
	 */
	private int supplierId;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Address
	 */
	private String address;
	/**
	 * City
	 */
	private String city;
	/**
	 * State
	 */
	private String state;
	/**
	 * ZipCode
	 */
	private String zipCode;
	/**
	 * Active
	 */
	private int active;
	/**
	 * @name Supplier
	 * {@summary Default constructor }
	 * @param supplierId
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zipcode
	 * @param active
	 */
	public Supplier(int supplierId, String name , String address, String city, String state, String zipCode, int active) {
		this.setSupplierId(supplierId);
		this.setName(name);
		this.setAddress(address);
		this.setCity(city);
		this.setState(state);
		this.setZipCode(zipCode);
		this.setActive(active);
	}
	/**
	 * @name getSupplierId
	 * @return
	 */
	public int getSupplierId() {
		return supplierId;
	}
	/**
	 * @name setSupplierId
	 * @param supplierId
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
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
	 * @name getAddress
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @name setAddress
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @name getCity
	 * @return
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @name setCity
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @name getState
	 * @return
	 */
	public String getState() {
		return state;
	}
	/**
	 * @name setState
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @name setZipCode
	 * @return
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @name setZipCode
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
