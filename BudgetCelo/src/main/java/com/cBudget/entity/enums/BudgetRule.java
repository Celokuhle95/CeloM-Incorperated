package com.cBudget.entity.enums;

import java.math.BigDecimal;

import com.cBudget.controller.utils.MoneyUtil;

public enum BudgetRule {
	
	FIFTHY(new BigDecimal("50.00"), "Expenses", "Total Expenses should be 50%, or less, of your Total Income."),
	THIRTHY(new BigDecimal("30.00"), "Investments" ,"Total Investements should be 30%, or more, of your Total Income."),
	TWENTHY(new BigDecimal("20.00"), "Remaining","Money Remaining should be 20%, or more, of your Total Income.");
	
	private BigDecimal value;
	
	private String summary;
	
	private String description;
	
	private BudgetRule(BigDecimal value, String summary, String description) {
		this.value = value;
		this.summary = summary;
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}
	
	public String getValueFormated() {
		return MoneyUtil.noDecimals(value);
	}

	public String getSummary() {
		return summary;
	}

	public String getDescription() {
		return description;
	}
	
}
