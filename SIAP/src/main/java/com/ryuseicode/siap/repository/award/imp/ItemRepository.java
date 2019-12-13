package com.ryuseicode.siap.repository.award.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.award.Item;
import com.ryuseicode.siap.repository.award.intf.IItemRepository;
/**
 * @name ItemRepository
 * {@summary Repository class to implement IItemRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 10, 2019
 */
@Repository
public class ItemRepository implements IItemRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @name getByProposalId
	 * {@summary Method to get list of items by proposalId }
	 * @param proposalId
	 * @return
	 */
	public List<Item> getByProposalId(int proposalId) {
		return jdbcTemplate.query(
                "select * from ITEM where proposalid = ? and active = 1 ",
                new Object[] { proposalId },
                (rs, rowNum) ->
                        new Item(
                        	rs.getInt("itemid"),
                        	rs.getInt("proposalid"),
                        	rs.getInt("annexid"),
                        	rs.getDouble("unitprice"),
                        	rs.getDouble("totalamount"),	
                        	rs.getInt("winner"),
                        	rs.getInt("active")
                        )
        );
	}
	/**
	 * @name save
	 * {@summary Method to save a list of items }
	 * @param items
	 * @return
	 */
	public int save(List<Item> items) {
		// Define list
		ArrayList<Object[]> rows = new ArrayList<Object[]>();
		// loop to add parameters
		for(Item item : items) {
			Object[] row = new Object[6];
			row[0] =  item.getProposalId();
			row[1] =  item.getAnnexId();
			row[2] =  item.getUnitPrice();
			row[3] =  item.getTotalAmount();
			row[4] =  item.getWinner();
			row[5] =  item.getActive();
			rows.add(row);
		}
		return Arrays.stream(jdbcTemplate.batchUpdate("insert into ITEM (proposalid, annexid, unitprice, totalamount, winner, active) values(?,?,?,?,?,?)", rows)).sum();
	}
}