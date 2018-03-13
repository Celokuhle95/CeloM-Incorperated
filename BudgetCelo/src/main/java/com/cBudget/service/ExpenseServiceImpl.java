package com.cBudget.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.ExpenseDAO;
import com.cBudget.entity.ExpenseItem;

@Stateless
public class ExpenseServiceImpl implements ExpenseService{

	@Inject
	private ExpenseDAO expenseDAO;
	
	@Override
	public void update(ExpenseItem expense) {
		System.out.println("Update Called");
		expenseDAO.edit(expense);
	}
	
	protected ExpenseDAO getExpenseDAO() {
		return expenseDAO;
	}
	
	protected void setExpenseDAO(ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}
	
}
