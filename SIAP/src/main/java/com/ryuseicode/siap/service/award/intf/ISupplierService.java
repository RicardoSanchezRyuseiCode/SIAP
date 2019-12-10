package com.ryuseicode.siap.service.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Supplier;

/**
 * @name ISupplierService
 * {@summary Interface to define the behavior of ISupplierService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public interface ISupplierService {
	/**
	 * @name Get
	 * {@summary Method to get a collection of suplier }
	 * @return
	 */
	List<Supplier> get();
	/**
	 * @name save
	 * {@summary Method to save a supplier }
	 * @param supplier
	 */
	void save(Supplier supplier) throws Exception;
	/**
	 * @name save
	 * {@summary Method to save a collections of suppliers}
	 * @param suppliers
	 */
	void save(List<Supplier> suppliers) throws Exception;
}
