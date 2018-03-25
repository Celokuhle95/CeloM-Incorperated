package com.cBudget.service;

import java.util.List;

import javax.ejb.Local;

import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.BudgetDate;
import com.cBudget.entity.enums.Month;

@Local
public interface MonthlyBudgetService extends BaseService<MonthlyBudget>{
	
	List<Integer> getBudgetingYears();
	
	List<MonthlyBudget> getBudgetsByYear(Integer year);

	boolean alreadyAdded(BudgetDate bDate);

	MonthlyBudget getPreceedingBudget(BudgetDate bDate);
	
}
