package com.cBudget.entity.enums;

import com.cBudget.controller.utils.DropDownsUtil;

public enum ToBuyType {
	GENERAL_EXPENSE,
	INVESTMENT,
	EDECATION,
	GADGET,
	CLOTHES,
	ENTERTAINMENT,
	APPLIANCE,
	MOTOR,
	FURNITURE;
	
	@Override
	public String toString() {
		return DropDownsUtil.cleanUp(name());
	}
}
