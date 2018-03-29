/**
 * @author celokuhle.myeza
 */
package com.cBudget.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.entity.ExpenseItem;
import com.cBudget.entity.InvestmentItem;
import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.BudgetDate;
import com.cBudget.service.AuthenticationService;
import com.cBudget.service.MonthlyBudgetService;

@Named(value = "monthlyBudgetController")
@SessionScoped
public class MonthlyBudgetController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MonthlyBudgetService monthlyBudgetService;
	
	@Inject
	private AuthenticationService authenticationService;

	private MonthlyBudget monthlyBudget = new MonthlyBudget();

	private ExpenseItem expense = new ExpenseItem();

	private InvestmentItem investment = new InvestmentItem();

	private boolean isView;
	
	private boolean moreRecurringExpensesAllowed;
	
	private boolean moreRecurringInvestmentsAllowed;
	
	public void addRecurringExpensesFromPreceedingBudget() {
		BudgetDate bDate= new BudgetDate(monthlyBudget.getMonth(), monthlyBudget.getYear());
		MonthlyBudget preceedingBudget = monthlyBudgetService.getPreceedingBudget(bDate);
		if(preceedingBudget != null) {
			List<ExpenseItem> recurrungExpenses = makeExpensesReleventToCurrentBudget(preceedingBudget.getRecurringExpenses()); 
			monthlyBudget.getExpenses().addAll(recurrungExpenses);
			moreRecurringExpensesAllowed = false;
		} else {
			showNoPreviousBudgetsErrorMessage();
			moreRecurringExpensesAllowed = false;
		}
	}

	public void addRecurringInvestmentsFromPreceedingBudget() {
		BudgetDate bDate= new BudgetDate(monthlyBudget.getMonth(), monthlyBudget.getYear());
		MonthlyBudget preceedingBudget = monthlyBudgetService.getPreceedingBudget(bDate);
		if(preceedingBudget != null) {
			List<InvestmentItem> recurringInvestments = makeInvestmentsReleventeToCurrentBudget(preceedingBudget.getRecurringInvestments());
			monthlyBudget.getInvestments().addAll(recurringInvestments);
			moreRecurringInvestmentsAllowed = false;
		} else {
			showNoPreviousBudgetsErrorMessage();
			moreRecurringInvestmentsAllowed = false;
		}
	}

	private void showNoPreviousBudgetsErrorMessage() {
		JsfUtil.addErrorMessage("No previous Budget found within the last 6 months, for the period: " + monthlyBudget.getMonth() + " " + monthlyBudget.getYear());
	}
	
	private List<ExpenseItem> makeExpensesReleventToCurrentBudget(List<ExpenseItem> recurringExpenses) {
		List<ExpenseItem> newOnes = new ArrayList<>();
		for (ExpenseItem expense : recurringExpenses) {
			ExpenseItem clonedExpense = cloneExpense(expense);
			newOnes.add(clonedExpense);
		}
		return newOnes;
	}
	
	private List<InvestmentItem> makeInvestmentsReleventeToCurrentBudget(List<InvestmentItem> recurringInvestments) {
		List<InvestmentItem> newOnes = new ArrayList<>();
		for (InvestmentItem investment : recurringInvestments) {
			InvestmentItem clonedInvesment = cloneInvesment(investment);
			newOnes.add(clonedInvesment);
		}
		return newOnes;
	}
	
	private ExpenseItem cloneExpense(ExpenseItem other) {
		ExpenseItem clone = new ExpenseItem(other.getExpenseType(), other.getNecessityLevel(), other.getName(), other.getAmount(), other.getRecurring());
		clone.setMonthlyBudget(monthlyBudget);
		return clone;
	}
	
	private InvestmentItem cloneInvesment(InvestmentItem other) {
		InvestmentItem clone = new InvestmentItem(other.getInvestmentType(), other.getRiskLevel(), other.getName(), other.getAmount(), other.getInterestRate(), other.getRecurring(), other.getPeriod());
		clone.setMonthlyBudget(monthlyBudget);
		return clone;
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
			BudgetDate bDate= new BudgetDate(monthlyBudget.getMonth(), monthlyBudget.getYear());
			if (!monthlyBudgetService.alreadyAdded(bDate)) {
				monthlyBudget.setOwner(authenticationService.getCurrentUser());
				monthlyBudgetService.edit(monthlyBudget);
				JsfUtil.addSuccessMessage(
						"Budget Created for " + monthlyBudget.getMonth() + " " + monthlyBudget.getYear());
				return JsfUtil.redirectable("/views/monthlyBudget/list");
			} else {
				JsfUtil.addErrorMessage("There is an already existing budget for: " + monthlyBudget.getMonth() + " "
						+ monthlyBudget.getYear());
				return "";
			}
		} else {
			JsfUtil.addErrorMessage(
					"Your Total Spendings cannot be greater than your Total Income, the difference is: R"
							+ monthlyBudget.getTotalMoneyLeft());
			return "";
		}
	}

	public String edit() {
		monthlyBudgetService.edit(monthlyBudget);
		return JsfUtil.redirectable("/views/monthlyBudget/list");
	}

	public void delete(MonthlyBudget monthlyBudget) {
		monthlyBudgetService.remove(monthlyBudget);
		JsfUtil.addSuccessMessage("Monthly Budget removed successfully.");
	}

	public boolean isMonthlyBudgetValid() {
		if (monthlyBudget.getYear() == null || monthlyBudget.getMonth() == null || monthlyBudget.getIncome() == null) {
			return false;
		}
		return true;
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
		return JsfUtil.redirectable("/views/monthlyBudget/list");
	}

	public String goToNewBudget() {
		resetBudget();
		return JsfUtil.redirectable("/views/monthlyBudget/create");
	}

	private void resetBudget() {
		monthlyBudget = new MonthlyBudget();
		isView = false;
		moreRecurringExpensesAllowed = true;
		moreRecurringInvestmentsAllowed = true;
	}

	public String getCurrentMonthAndYear() {
		return null;
	}

	public String goToView() {
		isView = true;
		return JsfUtil.redirectable("/views/monthlyBudget/view");
	}

	private boolean isExpenseValid() {
		return expense.getExpenseType() != null && expense.getNecessityLevel() != null && expense.getName() != null
				&& expense.getAmount() != null;
	}

	private boolean isInvestmentValid() {
		return investment.getInvestmentType() != null && investment.getRiskLevel() != null
				&& investment.getName() != null && investment.getAmount() != null;
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

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isMoreRecurringExpensesAllowed() {
		return moreRecurringExpensesAllowed;
	}

	public void setMoreRecurringExpensesAllowed(boolean moreRecurringExpensesAllowed) {
		this.moreRecurringExpensesAllowed = moreRecurringExpensesAllowed;
	}

	public boolean isMoreRecurringInvestmentsAllowed() {
		return moreRecurringInvestmentsAllowed;
	}

	public void setMoreRecurringInvestmentsAllowed(boolean moreRecurringInvestmentsAllowed) {
		this.moreRecurringInvestmentsAllowed = moreRecurringInvestmentsAllowed;
	}

}
