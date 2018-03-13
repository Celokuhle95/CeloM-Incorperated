package com.cBudget.service;

import javax.ejb.Local;

import com.cBudget.entity.ExpenseItem;

@Local
public interface ExpenseService {

	void update(ExpenseItem expense);
	
}
