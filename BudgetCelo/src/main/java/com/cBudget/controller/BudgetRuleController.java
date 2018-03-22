/**
 * @author celokuhle.myeza
 */

package com.cBudget.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.cBudget.controller.utils.ColorUtil;
import com.cBudget.controller.utils.MoneyUtil;
import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.BudgetRule;

@Named
@SessionScoped
public class BudgetRuleController implements Serializable {

	private static final long serialVersionUID = 1L;

	private MonthlyBudget monthlyBudget = new MonthlyBudget();

	private List<BudgetRule> rules = new ArrayList<>(3);

	private BarChartModel barChart;

	public BudgetRuleController() {
		rules.add(BudgetRule.FIFTHY);
		rules.add(BudgetRule.THIRTHY);
		rules.add(BudgetRule.TWENTHY);
	}

	public BigDecimal limitPercentageAmount(BudgetRule budgetRule) {
		BigDecimal percentage = MoneyUtil.divide(budgetRule.getValue(), MoneyUtil.HUNDRED);
		BigDecimal incomePercentage = monthlyBudget.getTotalIncome().multiply(percentage);
		return MoneyUtil.format(incomePercentage);
	}

	public String limitPercentageAmountFormated(BudgetRule budgetRule) {
		return MoneyUtil.thousandSeperated(limitPercentageAmount(budgetRule));
	}

	public BigDecimal respectiveSpendingAmount(BudgetRule budgetRule) {
		if (budgetRule.getValue().equals(BudgetRule.FIFTHY.getValue())) {
			return monthlyBudget.getTotalExpenses();
		} else if (budgetRule.getValue().equals(BudgetRule.THIRTHY.getValue())) {
			return monthlyBudget.getTotalInvestments();
		} else {
			return monthlyBudget.getTotalMoneyLeft();
		}
	}
	
	public String textColor(BudgetRule rule) {
		BigDecimal amount = respectiveSpendingAmount(rule);
		BigDecimal limit = limitPercentageAmount(rule);
		if (rule.getValue().equals(BudgetRule.FIFTHY.getValue())) {
			if(amount.compareTo(limit) == 1 ) {
				return ColorUtil.UNACCEPTED;
			} else {
				return ColorUtil.ACCEPTED;
			}
		} else if (rule.getValue().equals(BudgetRule.THIRTHY.getValue())) {
			if(amount.compareTo(limit) == -1) {
				return ColorUtil.UNACCEPTED;
			} else {
				return ColorUtil.ACCEPTED;
			}
		} else {
			if(amount.compareTo(limit) == -1) {
				return ColorUtil.UNACCEPTED;
			} else {
				return ColorUtil.ACCEPTED;
			}
		}
	}
	
	public String respectiveSpendingAmountFormated(BudgetRule budgetRule) {
		return MoneyUtil.thousandSeperated(respectiveSpendingAmount(budgetRule));
	}

	public BigDecimal differencePercentage(BudgetRule budgetRule) {
		if (budgetRule.getValue().equals(BudgetRule.FIFTHY.getValue())) {
			return calcPercentage(monthlyBudget.getTotalExpenses(), budgetRule);
		} else if (budgetRule.getValue().equals(BudgetRule.THIRTHY.getValue())) {
			return calcPercentage(monthlyBudget.getTotalInvestments(), budgetRule);
		} else {
			return calcPercentage(monthlyBudget.getTotalMoneyLeft(), budgetRule);
		}
	}

	public String differencePercentageFormated(BudgetRule budgetRule) {
		return MoneyUtil.thousandSeperated(differencePercentage(budgetRule));
	}

	public BigDecimal calcPercentage(BigDecimal spendingBalance, BudgetRule budgetRule) {
		if(!spendingBalance.equals(BigDecimal.ZERO)) {
			BigDecimal limit = limitPercentageAmount(budgetRule);
			int condition = spendingBalance.compareTo(limit);
			if (condition == 1 || condition == 0) {// greater or equal to
				BigDecimal difference = spendingBalance.subtract(limit);
				BigDecimal percentage = MoneyUtil.percentage(difference, limit);
				return MoneyUtil.format(percentage);
			} else { // less
				BigDecimal difference = limit.subtract(spendingBalance);
				BigDecimal percentage = MoneyUtil.percentage(difference, limit);
				return MoneyUtil.format(percentage);
			}
		} else {
			return BigDecimal.ZERO;
		}
	}

	private void createBarChart() {
		barChart = new BarChartModel();
		barChart.setLegendPosition("n");
		barChart.setBarMargin(5);
		barChart.setAnimate(true);
		barChart.setExtender("ext");
		Axis yAxis = barChart.getAxis(AxisType.Y);
		yAxis.setLabel("R");
		
		initBarChart();
	}

	public void initBarChart() {
		barChart.clear();
		ChartSeries limits = addChartDisplayData(true);
		limits.setLabel("Limits");

		ChartSeries actuals = addChartDisplayData(false);
		actuals.setLabel("Actuals");
		
		barChart.addSeries(limits);
		barChart.addSeries(actuals);
	}

	private ChartSeries addChartDisplayData(boolean isLimit) {
		ChartSeries series = new ChartSeries();
		for (BudgetRule rule : rules) {
			if(isLimit) {
				BigDecimal limitPercentageAmount = limitPercentageAmount(rule);
				series.set(rule.getSummary(), limitPercentageAmount);
			} else {
				BigDecimal respectiveSpendingAmount = respectiveSpendingAmount(rule);
				series.set(rule.getSummary(), respectiveSpendingAmount);
			}
		}
		return series;
	}
	
	public void initForMonthlyBudgetCreation() {
		monthlyBudget = new MonthlyBudget();
		createBarChart();
	}

	public void setMonthlyBudget(MonthlyBudget monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
		createBarChart();
	}

	public void setRules(List<BudgetRule> rules) {
		this.rules = rules;
	}

	public List<BudgetRule> getRules() {
		return rules;
	}

	public BarChartModel getBarChart() {
		initBarChart();
		return barChart;
	}

	public void setBarChart(BarChartModel barChart) {
		this.barChart = barChart;
	}

	public MonthlyBudget getMonthlyBudget() {
		return monthlyBudget;
	}

}
