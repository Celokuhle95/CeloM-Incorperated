package com.cBudget.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.MonthlyBudgetDAO;
import com.cBudget.entity.MonthlyBudget;

@Stateless
public class MonthlyBudgetServiceImpl implements MonthlyBudgetService {

	@Inject
	private MonthlyBudgetDAO monthlyBudgetDAO;

	@Override
	public void create(MonthlyBudget monthlyBudget) {
		// do some recurring logic...
		// Getting recurring values from previous months
		// by writing the hasPreceedingMonthBudget(); and the getPreceedingMonth();
		monthlyBudgetDAO.create(monthlyBudget);

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
		return monthlyBudgetDAO.findAll("MonthlyBudget");
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
