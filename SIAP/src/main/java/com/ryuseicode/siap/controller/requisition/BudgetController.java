package com.ryuseicode.siap.controller.requisition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ryuseicode.siap.entity.requisition.Budget;
import com.ryuseicode.siap.service.requisition.imp.BudgetService;

/**
 * @name BudgetController
 * {@summary Controller to expose budget endpoints }
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since Dec 22, 2019
 */
@Controller
public class BudgetController {
	/**
	 * budgetService
	 */
	@Autowired
	private BudgetService budgetService;
	/**
	 * @name getByAdministrativeUnitId
	 * {@summary Method to get list of budget by administrative unit }
	 * @param administrativeUnitId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/budget/getByAdministrativeUnitId/{administrativeUnitId}", produces = "application/json")	
    public List<Budget> getByAdministrativeUnitId(@PathVariable int administrativeUnitId) {
		return this.budgetService.getByAdministrativeUnidId(administrativeUnitId);
	}
	/**
	 * @name getByBudgetId
	 * {@summary Method to get by budget id }
	 * @param budgetId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "/requisition/budget/getByBudgetId/{budgetId}", produces = "application/json")	
    public Budget getByBudgetId(@PathVariable int budgetId) {
		return this.budgetService.getByBudgetId(budgetId);
	}
}
