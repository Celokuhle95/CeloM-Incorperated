package com.cBudget.service;

import java.util.List;

import javax.ejb.Local;

import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.BudgetDate;

@Local
public interface MonthlyBudgetService extends BaseService<MonthlyBudget>{
	
	List<Integer> getBudgetingYears();
	
	List<MonthlyBudget> getBudgetsByYear(Integer year);

	boolean alreadyAdded(BudgetDate bDate);

	MonthlyBudget getPreceedingBudget(BudgetDate bDate);
	
}
