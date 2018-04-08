package com.cBudget.controller.utils;

import java.time.LocalDate;

import com.cBudget.entity.enums.BudgetDate;
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
	
	public static boolean isMonthValid(BudgetDate bd) {
		Month currentMonth = getCurrentMonth();
		if(isYearGreaterThanCurrentYear(bd.getYear())) {
			return true;
		}
		if(currentMonth.ordinal() <= bd.getMonth().ordinal()) {
			return true;
		}
		return false;
	}
	
	private static boolean isYearGreaterThanCurrentYear(int forYear) {
		return (forYear > getCurrentYear());			
	}

	public static boolean isYearValid(int year) {
		if(getCurrentYear() <= year) {
			return true;
		}
		return false;
	}
	
}
