package com.cBudget.entity.enums;

import com.cBudget.controller.utils.DropDownsUtil;

public enum RiskLevel {
	LOW,
	AVERAGE,
	HIGH;
	
	@Override
	public String toString() {
		return DropDownsUtil.cleanUp(name());
	}
}
