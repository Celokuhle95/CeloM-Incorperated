package com.cBudget.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.cBudget.controller.utils.BudgetDateUtil;
import com.cBudget.controller.utils.DropDownsUtil;
import com.cBudget.entity.enums.Month;
import com.cBudget.entity.enums.comparators.EnumComparator;

@Named
@SessionScoped
public class ForwardMoverController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public SelectItem[] getPossibleYears() {
		return DropDownsUtil.getSelectItems(calcPossibleYears());
	}

	public SelectItem[] getPossibleMonths() {
		return DropDownsUtil.getSelectItems(calcPossibleMonths());
	}
	
	public SelectItem[] getAllPossibleMonthsInAYear() {
		Map<Integer, Object> months = new TreeMap<>(EnumComparator.get());
		for (Month month : Month.values()) {
			months.put(month.ordinal(), month);
		}
		return DropDownsUtil.getSelectItems(months);
	}
	
	public Map<Integer, Object> calcPossibleMonths() {
		Map<Integer, Object> months = new TreeMap<>(EnumComparator.get());
		int numAllowedMonthsAhead = 1;
		Month currentMonth = BudgetDateUtil.getCurrentMonth();
		months.put(currentMonth.ordinal(), currentMonth);
		for (int i = 0; i < numAllowedMonthsAhead; i++) {
			currentMonth = nextMonth(currentMonth);
			months.put(currentMonth.ordinal(), currentMonth);
		}
		return months;
	}
	
	public Map<Integer, Object> calcPossibleYears() {
		Map<Integer, Object> years = new TreeMap<>(EnumComparator.get());
		int numAllowedMonthsAhead = 1;
		Integer currentYear = BudgetDateUtil.getCurrentYear();
		years.put(currentYear, currentYear);
		for (int i = 0; i < numAllowedMonthsAhead; i++) {
			currentYear = nextYear(currentYear);
			years.put(currentYear, currentYear);
		}
		return years;
	}
	
	private Month nextMonth(Month currentMonth) {
		if(currentMonth.equals(Month.DECEMBER)) {
			return Month.JANUARY;
		}
		return Month.values()[currentMonth.ordinal() + 1];
	}

	private int nextYear(int currentYear) {
		return ++currentYear;
	}

}
