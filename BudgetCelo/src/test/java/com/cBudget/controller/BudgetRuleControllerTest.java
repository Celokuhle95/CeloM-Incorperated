package com.cBudget.controller;

import java.math.BigDecimal;

import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.BudgetRule;

import junit.framework.TestCase;

public class BudgetRuleControllerTest extends TestCase {

//	private BudgetRuleController controller;
//	
//	private MonthlyBudget budget;
//
//	@Override
//	protected void setUp() throws Exception {
//		controller = initialise();
//	}

	
	public void testDummy () {
		
	}
//	private BudgetRuleController initialise() {
//		BudgetRuleController controller = new BudgetRuleController();
//		budget = new MonthlyBudget();
//		budget.setIncome(new BigDecimal("1000.00"));
//		budget.setTotalExpenses(new BigDecimal("450.00"));
//		budget.setTotalInvestments(new BigDecimal("350.00"));
//		budget.setTotalMoneyLeft(new BigDecimal("200.00"));
//		controller.setMonthlyBudget(budget);
//		return controller;
//	}
//	
//	public void testCalcLimitPercentageAmountFails() {
//		assertNotSame("", "450", controller.limitPercentageAmount(BudgetRule.FIFTHY));
//	}
//	
//	public void testCalcLimitPercentageAmountPasses() {
//		assertEquals(new BigDecimal("500.00"), controller.limitPercentageAmount(BudgetRule.FIFTHY));
//		assertEquals(new BigDecimal("300.00"), controller.limitPercentageAmount(BudgetRule.THIRTHY));
//		assertEquals(new BigDecimal("200.00"), controller.limitPercentageAmount(BudgetRule.TWENTHY));
//	}
//	
//	public void testCalcPercentagePasses() {
//		assertEquals(new BigDecimal("10.00"), controller.calcPercentage(budget.getTotalExpenses(), BudgetRule.FIFTHY));
//		assertEquals(new BigDecimal("16.67"), controller.calcPercentage(budget.getTotalInvestments(), BudgetRule.THIRTHY));
//		assertEquals(new BigDecimal("0.00"), controller.calcPercentage(budget.getTotalMoneyLeft(), BudgetRule.TWENTHY));
//	}
	
}
