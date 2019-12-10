package com.ryuseicode.siap.repository.award.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Asset;
import com.ryuseicode.siap.repository.award.intf.IAssetRepository;

@Repository
public class AssetRepository implements IAssetRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name Get
	 * {@summary Method to get a list of assets}
	 * @return
	 */
	public List<Asset> get() {
		return jdbcTemplate.query(
                "select * from ASSET where Active = 1 ",
                (rs, rowNum) ->
                        new Asset(
                        		rs.getInt("assetid"),
                                rs.getString("name"),
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name GetByName
	 * @param name
	 * @return 
	 */
	public Asset getByName(String name) {
		List<Asset> result = jdbcTemplate.query(
                "select * from ASSET where LOWER(name) = LOWER(?) and Active = 1 ",
                new Object[] { name },
                (rs, rowNum) ->
                        new Asset(
                        		rs.getInt("assetid"),
                                rs.getString("name"),
                                rs.getInt("active")
                        )
        );
		return result.size() > 0 ? result.get(0) : null;
	}
	
	/**
	 * @name Save
	 * {@summary Method to save a good}
	 */
	public int save(Asset asset) {
		return jdbcTemplate.update(
                "insert into ASSET (name, active) values(?,?)",
                asset.getName(), asset.getActive());
	}
	/**
	 * @name Save
	 * @param asset
	 * @return
	 */
	public int save(List<Asset> assets) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(Asset asset : assets) {
			Object[] row = new Object[2];
			row[0] =  asset.getName();
			row[1] =  asset.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into ASSET (name, active) values(?,?)", rows)).sum();
	}
	
}
