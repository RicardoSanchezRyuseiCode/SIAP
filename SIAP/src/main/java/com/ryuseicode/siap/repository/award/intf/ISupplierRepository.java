package com.ryuseicode.siap.repository.award.intf;

import java.util.List;

import com.ryuseicode.siap.entity.award.Supplier;

/**
 * @name ISupplierRepository
 * {@summary Interface to define the behavior of ISupplierRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 1, 2019
 */
public interface ISupplierRepository {
	/**
	 * @name Get
	 * {@summary Method to get a collection of suplier }
	 * @return
	 */
	List<Supplier> get();
	/**
	 * @name getByName
	 * {@summary Method to get supplier by name }
	 * @param name
	 * @return
	 */
	Supplier getByName(String name);
	/**
	 * @name save
	 * {@summary Method to save a supplier }
	 * @param supplier
	 */
	int save(Supplier supplier);
	/**
	 * @name save
	 * {@summary Method to save a collections of suppliers}
	 * @param suppliers
	 */
	int save(List<Supplier> suppliers);
}
