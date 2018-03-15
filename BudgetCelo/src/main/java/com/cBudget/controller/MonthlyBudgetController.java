package com.cBudget.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.cBudget.controller.utils.DropDownsUtil;
import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.entity.ExpenseItem;
import com.cBudget.entity.InvestmentItem;
import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.Month;
import com.cBudget.entity.enums.comparators.EnumComparator;
import com.cBudget.service.MonthlyBudgetService;

@Named(value = "monthlyBudgetController")
@SessionScoped
public class MonthlyBudgetController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MonthlyBudgetService monthlyBudgetService;

	private MonthlyBudget monthlyBudget = new MonthlyBudget();

	private ExpenseItem expense = new ExpenseItem();

	private InvestmentItem investment = new InvestmentItem();

	private boolean showListBudgetsSensitiveInfo = false;

	private boolean showExpenseSensitiveInfo = false;

	private boolean showInvestementSensitiveInfo = false;

	private boolean showRuleSensitiveInfo = false;

	private boolean showBudgetInfo = false;

	private boolean isView = false;

	public MonthlyBudgetController() {
	}

	public void sayHi(ExpenseItem exp) { //DELETE
		System.out.println(exp);
	}

	public List<List<MonthlyBudget>> getAllBudgets() {
		List<List<MonthlyBudget>> budgets = new ArrayList<>();
		List<Integer> budgetingYears = monthlyBudgetService.getBudgetingYears();
		for (Integer year : budgetingYears) {
			List<MonthlyBudget> budgetsByYear = monthlyBudgetService.getBudgetsByYear(year);
			budgets.add(budgetsByYear);
		}
		return budgets;
	}

	public void addExpense() {
		if (isExpenseValid()) {
			expense.setMonthlyBudget(monthlyBudget);
			monthlyBudget.getExpenses().add(expense);
			RequestContext.getCurrentInstance().execute("PF('createExpenseDialog').hide()");
		}
	}

	public void addInvestment() {
		if (isInvestmentValid()) {
			investment.setMonthlyBudget(monthlyBudget);
			monthlyBudget.getInvestments().add(investment);
			RequestContext.getCurrentInstance().execute("PF('createInvestmentDialog').hide()");
		}
	}

	public String create() {
		if (!monthlyBudget.isMoneyGoingOutGreaterThanMoneyComingIn()) {
			monthlyBudgetService.create(monthlyBudget);
			JsfUtil.addSuccessMessage("Budget Created for " + monthlyBudget.getMonth() + " " + monthlyBudget.getYear());
			return JsfUtil.redirectable("/views/monthlyBudget/list");
		} else {
			JsfUtil.addErrorMessage(
					"Your Total Spendings cannot be greater than your Total Income, the difference is: R"
							+ monthlyBudget.getTotalMoneyLeft());
			return null;
		}
	}

	public String edit() {
		monthlyBudgetService.edit(monthlyBudget);
		return JsfUtil.redirectable("/views/monthlyBudget/list");
	}

	public void delete(MonthlyBudget monthlyBudget) {
		monthlyBudgetService.remove(monthlyBudget);
		// show some message
	}

	private boolean isExpenseValid() {
		return expense.getExpenseType() != null && expense.getNecessityLevel() != null && expense.getName() != null
				&& expense.getAmount() != null;
	}

	private boolean isInvestmentValid() {
		return investment.getInvestmentType() != null && investment.getRiskLevel() != null
				&& investment.getName() != null && investment.getAmount() != null;
	}

	public boolean isMonthlyBudgetValid() {
		int duplications = 0;
		if (monthlyBudget.getYear() == null || monthlyBudget.getMonth() == null || monthlyBudget.getIncome() == null) {
			preventDeplicateShowingOfMessage(duplications);
			return false;
		}
		return true;
	}

	private void preventDeplicateShowingOfMessage(Integer duplications) {
		if (++duplications == 1) {
			JsfUtil.addInfoMessage("Provide all Required Information before continuing");
		} else {
			if (duplications == 3) { // 3 = number of possible calls that could provide the same message in a single
										// front end call/update
				duplications = 0;
			}
		}
	}

	public void resetExpense() {
		expense = new ExpenseItem();
		RequestContext.getCurrentInstance().update("createExpenseForm");
	}

	public void resetInvestment() {
		investment = new InvestmentItem();
		RequestContext.getCurrentInstance().update("createInvestmentForm");
	}

	public String goToAllBudgets() {
		setShowListBudgetsSensitiveInfo(false);
		return JsfUtil.redirectable("/views/monthlyBudget/list");
	}

	public String goToNewBudget() {
		resetBudget();
		showAllSensitieInfo();
		isView = false;
		return JsfUtil.redirectable("/views/monthlyBudget/create");
	}

	private void resetBudget() {
		monthlyBudget = new MonthlyBudget();
	}

	private void showAllSensitieInfo() {
		showExpenseSensitiveInfo = true;
		showInvestementSensitiveInfo = true;
		showRuleSensitiveInfo = true;
		showBudgetInfo = false;
	}

	private void hideAllSensitiveInfo() {
		showExpenseSensitiveInfo = false;
		showInvestementSensitiveInfo = false;
		showRuleSensitiveInfo = false;
		showBudgetInfo = true;
	}

	public String getCurrentMonthAndYear() {
		return null;
	}

	public String goToView() {
		hideAllSensitiveInfo();
		isView = true;
		return JsfUtil.redirectable("/views/monthlyBudget/view");
	}

	public SelectItem[] getPossibleYears() {
		Map<Integer, Object> years = new TreeMap<>(EnumComparator.get());
		years.put(2018, 2018);
		years.put(2019, 2019);
		years.put(2020, 2020);
		return DropDownsUtil.getSelectItems(years);
	}

	public SelectItem[] getPossibleMonths() {
		Map<Integer, Object> months = new TreeMap<>(EnumComparator.get());
		for (Month month : Month.values()) {
			months.put(month.ordinal(), month);
		}
		return DropDownsUtil.getSelectItems(months);
	}

	public void updateMonthlyBudget() {

	}

	// getters/setters
	public MonthlyBudget getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(MonthlyBudget monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}

	public MonthlyBudgetService getMonthlyBudgetService() {
		return monthlyBudgetService;
	}

	public void setMonthlyBudgetService(MonthlyBudgetService monthlyBudgetService) {
		this.monthlyBudgetService = monthlyBudgetService;
	}

	public ExpenseItem getExpense() {
		return expense;
	}

	public void setExpense(ExpenseItem expense) {
		this.expense = expense;
	}

	public InvestmentItem getInvestment() {
		return investment;
	}

	public void setInvestment(InvestmentItem investment) {
		this.investment = investment;
	}

	public String disguiser() {
		return "***";
	}

	public void toggleShowExpenseSensitiveInfo() {
		showExpenseSensitiveInfo = !showExpenseSensitiveInfo;
	}

	public void toggleShowInvestementSensitiveInfo() {
		showInvestementSensitiveInfo = !showInvestementSensitiveInfo;
	}

	public void toggleShowRuleSensitiveInfo() {
		showRuleSensitiveInfo = !showRuleSensitiveInfo;
	}

	public void toggleShowListBudgetsSensitiveInfo() {
		showListBudgetsSensitiveInfo = !showListBudgetsSensitiveInfo;
	}

	public boolean getShowListBudgetsSensitiveInfo() {
		return showListBudgetsSensitiveInfo;
	}

	public void setShowListBudgetsSensitiveInfo(boolean showListBudgetsSensitiveInfo) {
		this.showListBudgetsSensitiveInfo = showListBudgetsSensitiveInfo;
	}

	public boolean isShowExpenseSensitiveInfo() {
		return showExpenseSensitiveInfo;
	}

	public void setShowExpenseSensitiveInfo(boolean showExpenseSensitiveInfo) {
		this.showExpenseSensitiveInfo = showExpenseSensitiveInfo;
	}

	public boolean getShowInvestementSensitiveInfo() {
		return showInvestementSensitiveInfo;
	}

	public void setShowInvestementSensitiveInfo(boolean showInvestementSensitiveInfo) {
		this.showInvestementSensitiveInfo = showInvestementSensitiveInfo;
	}

	public boolean getShowRuleSensitiveInfo() {
		return showRuleSensitiveInfo;
	}

	public void setShowRuleSensitiveInfo(boolean showRuleSensitiveInfo) {
		this.showRuleSensitiveInfo = showRuleSensitiveInfo;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean getShowBudgetInfo() {
		return showBudgetInfo;
	}

	public void setShowBudgetInfo(boolean showBudgetInfo) {
		this.showBudgetInfo = showBudgetInfo;
	}

}
