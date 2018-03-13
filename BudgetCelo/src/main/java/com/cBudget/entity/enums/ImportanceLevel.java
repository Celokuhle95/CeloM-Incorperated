package com.cBudget.entity.enums;

import com.cBudget.controller.utils.DropDownsUtil;

public enum ImportanceLevel {

	HIGH, AVERAGE, LOW;
	
	@Override
	public String toString() {
		return DropDownsUtil.cleanUp(name());
	}
}
