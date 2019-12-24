package com.ryuseicode.siap.repository.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.Budget;

/**
 * @name IBudgetRepository
 * {@summary Interface to define the behavior of IBudgetRepository }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 21, 2019
 */
public interface IBudgetRepository {
	/**
	 * @name getByAdministrativeUnidId
	 * {@summary Method to get a list of Budget }
	 * @return
	 */
	List<Budget> getByAdministrativeUnidId(int administrativeUnitId);
	/**
	 * @name GetByBudgetId
	 * {@summary Method to get a budget by id }
	 * @param budgetId
	 * @return
	 */
	Budget getByBudgetId(int budgetId);
}
