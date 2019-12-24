package com.ryuseicode.siap.service.requisition.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryuseicode.siap.entity.requisition.Budget;
import com.ryuseicode.siap.repository.requisition.imp.BudgetRepository;
import com.ryuseicode.siap.service.requisition.intf.IBudgetService;
/**
 * @name BudgetService
 * {@summary Service to implement the behavior of IBudgetService }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Service
public class BudgetService implements IBudgetService {
	/**
	 * BudgetRepository
	 */
	@Autowired
	private BudgetRepository budgetRepository;
	/**
	 * @name getByAdministrativeUnidId
	 * {@summary Method to get a list of Budget }
	 * @return
	 */
	public List<Budget> getByAdministrativeUnidId(int administrativeUnitId) {
		return this.budgetRepository.getByAdministrativeUnidId(administrativeUnitId);
	}
	/**
	 * @name GetByBudgetId
	 * {@summary Method to get a budget by id }
	 * @param budgetId
	 * @return
	 */
	public Budget getByBudgetId(int budgetId) {
		return this.budgetRepository.getByBudgetId(budgetId);
	}
}
