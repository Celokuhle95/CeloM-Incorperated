package com.cBudget.entity.enums;

import com.cBudget.controller.utils.DropDownsUtil;

public enum NecessityType {
	NEED,
	WANT;

	@Override
	public String toString() {
		return DropDownsUtil.cleanUp(name());
	}
	
}
