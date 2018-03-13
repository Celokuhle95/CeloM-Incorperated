package com.cBudget.entity.enums;

import com.cBudget.controller.utils.DropDownsUtil;

public enum ExpenseType {
	RENT,
	FOOD,
	ACCOUNT,
	TRANSPORT,
	AIRTIME,
	COMMUNICATION,
	ELECTRICITY,
	FAMILY_RELATED,
	CLOTHES,
	INSURANCE,
	HEALTH,
	ENTERTAINMENT,
	FRIENDS_RELATED,
	EDUCATION,
	FURNITURE,
	TECH_GADGET,
	CREDIT,
	TRAVEL;

	@Override
	public String toString() {
		return DropDownsUtil.cleanUp(name());
	}
	
}
