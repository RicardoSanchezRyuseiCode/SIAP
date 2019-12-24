package com.ryuseicode.siap.service.requisition.intf;

import java.util.List;

import com.ryuseicode.siap.entity.requisition.Budget;
/**
 * @name IBudgetService
 * {@summary Service to implement the behavior of IBudgetService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
public interface IBudgetService {
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
