package com.ryuseicode.siap.repository.award.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Supplier;
import com.ryuseicode.siap.repository.award.intf.ISupplierRepository;

@Repository
public class SupplierRepository implements ISupplierRepository {
	/**
	 * Autowired
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @name Get
	 * {@summary Method to get a collection of suplier }
	 * @return
	 */
	public List<Supplier> get() {
		return jdbcTemplate.query(
                "select * from SUPPLIER where Active = 1 ",
                (rs, rowNum) ->
                        new Supplier(
                        		rs.getInt("supplierid"),
                                rs.getString("name"),
                                rs.getString("address"),
                                rs.getString("city"),
                                rs.getString("state"),
                                rs.getString("zipcode"),
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name getByName
	 * {@summary Method to get supplier by name }
	 * @param name
	 * @return
	 */
	public Supplier getByName(String name) {
		List<Supplier> result = jdbcTemplate.query(
                "select * from SUPPLIER where LOWER(name)= LOWER(?) and Active = 1 ",
                new Object[] { name },
                (rs, rowNum) ->
                        new Supplier(
                        		rs.getInt("supplierid"),
                                rs.getString("name"),
                                rs.getString("address"),
                                rs.getString("city"),
                                rs.getString("state"),
                                rs.getString("zipcode"),
                                rs.getInt("active")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	/**
	 * @name save
	 * {@summary Method to save a supplier }
	 * @param supplier
	 */
	public int save(Supplier supplier) {
		return jdbcTemplate.update(
                "insert into SUPPLIER (name, address, city, state, zipcode, active) values (?,?,?,?,?,?)",
                supplier.getName(), supplier.getAddress(), supplier.getCity(), supplier.getState(), supplier.getZipCode(), supplier.getActive());
	}
	/**
	 * @name save
	 * {@summary Method to save a collections of suppliers}
	 * @param suppliers
	 */
	public int save(List<Supplier> suppliers) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(Supplier supplier : suppliers) {
			Object[] row = new Object[6];
			row[0] =  supplier.getName();
			row[1] =  supplier.getAddress();
			row[2] =  supplier.getCity();
			row[3] =  supplier.getState();
			row[4] =  supplier.getZipCode();
			row[5] =  supplier.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into SUPPLIER (name, address, city, state, zipcode, active) values(?,?,?,?,?,?)", rows)).sum();
	}
}
