package com.cBudget.service;

import java.util.List;

import javax.ejb.Local;

import com.cBudget.entity.MonthlyBudget;

@Local
public interface MonthlyBudgetService {

	void create(MonthlyBudget monthlyBudget);
	
	void edit(MonthlyBudget monthlyBudget);
	
	MonthlyBudget find(Integer id);
	
	List<MonthlyBudget> findAll();
	
	void remove(MonthlyBudget monthlyBudget);
	
	List<Integer> getBudgetingYears();
	
	List<MonthlyBudget> getBudgetsByYear(Integer year);
}
