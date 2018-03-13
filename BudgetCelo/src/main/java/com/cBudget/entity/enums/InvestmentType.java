package com.cBudget.entity.enums;

import com.cBudget.controller.utils.DropDownsUtil;

public enum InvestmentType {
	FIXED_ACCOUNT,
	EMERGENCY_FUND,
	CRYPTO_CURRENCY,
	STOCKS,
	ETF,
	TAX_FREE,
	UNIT_TRUST,
	MONEY_MARKET,
	PROPERTY,
	RETIREMENT_ANNUITY;
	
	@Override
	public String toString() {
		return DropDownsUtil.cleanUp(name());
	}
}
