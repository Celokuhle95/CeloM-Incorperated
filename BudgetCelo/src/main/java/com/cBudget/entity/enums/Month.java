package com.cBudget.entity.enums;

import com.cBudget.controller.utils.DropDownsUtil;

public enum Month {
	JANUARY, 
	FEBRUARY,
	MARCH, 
	APRIL, 
	MAY, 
	JUNE, 
	JULY, 
	AUGUST, 
	SEPTEMBER, 
	OCTOBER, 
	NOVEMBER,
	DECEMBER;
	
	@Override
	public String toString() {
		return DropDownsUtil.cleanUp(name());
	}
	
}
