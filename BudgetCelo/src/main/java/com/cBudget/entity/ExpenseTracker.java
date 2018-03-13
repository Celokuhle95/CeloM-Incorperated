package com.cBudget.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ExpenseTracker extends Tracker {
	
	@ManyToOne
	private ExpenseItem expense;

	public ExpenseItem getExpense() {
		return expense;
	}

	public void setExpense(ExpenseItem expense) {
		this.expense = expense;
	}
	
}
