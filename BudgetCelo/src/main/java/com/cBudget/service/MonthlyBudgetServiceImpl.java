package com.cBudget.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.MonthlyBudgetDAO;
import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.BudgetDate;

@Stateless
public class MonthlyBudgetServiceImpl implements MonthlyBudgetService {

	@Inject
	private MonthlyBudgetDAO monthlyBudgetDAO;

	@Override
	public void create(MonthlyBudget monthlyBudget) {
		monthlyBudgetDAO.create(monthlyBudget);
	}
	
	@Override
	public MonthlyBudget getPreceedingBudget(BudgetDate bDate) {
		return monthlyBudgetDAO.getPreceedingBudget(bDate);
	}

	@Override
	public boolean alreadyAdded(BudgetDate bDate) {
		return monthlyBudgetDAO.alreadyAdded(bDate);
	}
	
	@Override
	public void edit(MonthlyBudget monthlyBudget) {
		monthlyBudgetDAO.edit(monthlyBudget);
	}

	@Override
	public MonthlyBudget find(Integer id) {
		return monthlyBudgetDAO.find(id);
	}

	@Override
	public List<MonthlyBudget> findAll() {
		return monthlyBudgetDAO.findAll();
	}

	@Override
	public void remove(MonthlyBudget monthlyBudget) {
		monthlyBudgetDAO.remove(monthlyBudget);
	}

	@Override
	public List<Integer> getBudgetingYears() {
		return monthlyBudgetDAO.getBudgetingYears();
	}

	@Override
	public List<MonthlyBudget> getBudgetsByYear(Integer year) {
		return monthlyBudgetDAO.getBudgetsByYear(year);
	}

}
