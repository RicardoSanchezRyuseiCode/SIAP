package com.ryuseicode.siap.repository.requisition.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ryuseicode.siap.entity.requisition.Budget;
import com.ryuseicode.siap.repository.requisition.intf.IBudgetRepository;

/**
 * @name BudgetRepository
 * {@summary Repository class to implement the behavior of IBudgetRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Repository
public class BudgetRepository implements IBudgetRepository {
	/**
	 * JdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @name getByAdministrativeUnidId
	 * {@summary Method to get a list of Budget }
	 * @return
	 */
	public List<Budget> getByAdministrativeUnidId(int administrativeUnitId) {
		return jdbcTemplate.query(
                "select * from budget where active = 1  and administrativeunitid = ? order by code, description, season ",
                new Object[] { administrativeUnitId },
                (rs, rowNum) ->
                        new Budget(
                        		rs.getInt("budgetid"),
                        		rs.getInt("administrativeunitid"),
                                rs.getString("code"),
                                rs.getString("description"),
                                rs.getString("season"),
                                rs.getDouble("amount"),
                                rs.getInt("active")
                        )
        );
	}
	/**
	 * @name GetByBudgetId
	 * {@summary Method to get a budget by id }
	 * @param budgetId
	 * @return
	 */
	public Budget getByBudgetId(int budgetId) {
		List<Budget> results = jdbcTemplate.query(
                "select * from budget where active = 1  and budgetid = ?",
                new Object[] { budgetId },
                (rs, rowNum) ->
                        new Budget(
                        		rs.getInt("budgetid"),
                        		rs.getInt("administrativeunitid"),
                                rs.getString("code"),
                                rs.getString("description"),
                                rs.getString("season"),
                                rs.getDouble("amount"),
                                rs.getInt("active")
                        )
        );
        return results.size() > 0 ? results.get(0) : null;
	}
}