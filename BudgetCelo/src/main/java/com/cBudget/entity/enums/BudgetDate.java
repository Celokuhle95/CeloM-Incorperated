package com.cBudget.entity.enums;

public class BudgetDate {

	private Month month;
	
	private int year;
	
	public BudgetDate(Month month, int year) {
		super();
		this.month = month;
		this.year = year;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
}
