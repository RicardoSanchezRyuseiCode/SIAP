package com.ryuseicode.siap.service.award.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.award.Supplier;
import com.ryuseicode.siap.exception.ServiceException;
import com.ryuseicode.siap.repository.award.imp.SupplierRepository;
import com.ryuseicode.siap.service.award.intf.ISupplierService;

@Service
public class SupplierService implements ISupplierService {
	/**
	 * SupplierRepository
	 */
	@Autowired
	private SupplierRepository supplierRepository;
	
	/**
	 * @name Get
	 * {@summary Method to get a collection of suplier }
	 * @return
	 */
	public List<Supplier> get() {
		return this.supplierRepository.get();
	}
	/**
	 * @name save
	 * {@summary Method to save a supplier }
	 * @param supplier
	 */
	public void save(Supplier supplier) throws Exception {
		// Check if exist
		if(this.supplierRepository.getByName(supplier.getName()) != null)
			throw new ServiceException("Ya existe un proveedor con el mismo nombre");
		// Set default data
		supplier.setActive(1);
		// Save supplier
		this.supplierRepository.save(supplier);
	}
	/**
	 * @name save
	 * {@summary Method to save a collections of suppliers}
	 * @param suppliers
	 */
	public void save(List<Supplier> suppliers) throws Exception {
		// Get current suppliers
		List<Supplier> currentSuppliers = this.supplierRepository.get();
		// Define result collection
		ArrayList<Supplier> newSupplier = new ArrayList<Supplier>();
		// Loop in supplier and try check if are not repeated
		for(Supplier supplier : suppliers) {
			boolean flag = true;
			for(Supplier current : currentSuppliers) {
				if (current.getName().toLowerCase().equals(supplier.getName().toLowerCase())) {
					flag = false;
					break;
				} 
			}
			if(flag) {
				supplier.setActive(1);
				newSupplier.add(supplier);
			}
		}
		// Save the new assets
		this.supplierRepository.save(newSupplier);
	}
}
