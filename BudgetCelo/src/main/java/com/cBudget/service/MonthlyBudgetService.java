package com.cBudget.service;

import java.util.List;

import javax.ejb.Local;

import com.cBudget.entity.MonthlyBudget;

@Local
public interface MonthlyBudgetService extends BaseService<MonthlyBudget>{
	
	List<Integer> getBudgetingYears();
	
	List<MonthlyBudget> getBudgetsByYear(Integer year);
	
}
