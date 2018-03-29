package com.cBudget.controller.utils;

import java.time.LocalDate;

import com.cBudget.entity.enums.Month;

public class BudgetDateUtil {

	public static Month getCurrentMonth() {
		LocalDate now = LocalDate.now();
		int monthValue = now.getMonthValue();
		return Month.values()[monthValue - 1];
	}
	
	public static int getCurrentYear() {
		LocalDate now = LocalDate.now();
		return now.getYear();
	}
	
	public static void main(String[] args) {
		getCurrentYear();
	}
}
