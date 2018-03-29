/**
 * @author celokuhle.myeza
 */

package com.cBudget.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cBudget.controller.utils.MoneyUtil;
import com.cBudget.entity.enums.Month;

@Entity
public class MonthlyBudget implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer year;

	@Enumerated(EnumType.ORDINAL)
	private Month month;

	@Column
	private BigDecimal income;

	@Column
	private BigDecimal extraIncome;

	@OneToMany(mappedBy = "monthlyBudget", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ExpenseItem> expenses;

	@OneToMany(mappedBy = "monthlyBudget", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<InvestmentItem> investments;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "owner_id")
	private User owner;

	@Transient
	private BigDecimal totalExpenses;

	@Transient
	private BigDecimal totalInvestments;

	@Transient
	private BigDecimal totalMoneyLeft;

	public MonthlyBudget() {
		expenses = new ArrayList<>();
		investments = new ArrayList<>();
	}

	public BigDecimal getTotalIncome() {
		if (income == null && extraIncome == null) {
			return BigDecimal.ZERO;
		} else if (income != null && extraIncome == null) {
			return income;
		} else if (income == null && extraIncome != null) {
			return extraIncome;
		}
		return income.add(extraIncome);
	}

	public String getTotalIncomeFormated() {
		return MoneyUtil.thousandSeperated(getTotalIncome());
	}

	public BigDecimal getTotalExpenses() {
		totalExpenses = BigDecimal.ZERO;
		for (ExpenseItem expense : expenses) {
			totalExpenses = totalExpenses.add(expense.amount);
		}
		return totalExpenses;
	}

	public String getTotalExpensesFormated() {
		return MoneyUtil.thousandSeperated(getTotalExpenses());
	}

	public BigDecimal getTotalInvestments() {
		totalInvestments = BigDecimal.ZERO;
		for (InvestmentItem investment : investments) {
			totalInvestments = totalInvestments.add(investment.amount);
		}
		return totalInvestments;
	}

	public String getTotalInvestmentsFormated() {
		return MoneyUtil.thousandSeperated(getTotalInvestments());
	}

	public BigDecimal getTotalMoneyLeft() {
		if (getTotalIncome().equals(BigDecimal.ZERO)) {
			totalMoneyLeft = BigDecimal.ZERO;
		} else {
			totalMoneyLeft = getTotalIncome().subtract(getTotalSpendings());
		}
		return totalMoneyLeft;
	}

	public String getTotalMoneyLeftFormated() {
		return MoneyUtil.thousandSeperated(getTotalMoneyLeft());

	}

	public boolean isMoneyGoingOutGreaterThanMoneyComingIn() {
		if (getTotalSpendings().compareTo(getTotalIncome()) == 1) { // greater than
			return true;
		}
		return false;
	}

	public BigDecimal getTotalSpendings() {
		return getTotalExpenses().add(getTotalInvestments());
	}

	public List<ExpenseItem> getRecurringExpenses() {
		List<ExpenseItem> recurringExpenses = new ArrayList<>();
		for (ExpenseItem expense : expenses) {
			if (expense.getRecurring()) {
				recurringExpenses.add(expense);
			}
		}
		return recurringExpenses;
	}

	public List<InvestmentItem> getRecurringInvestments() {
		List<InvestmentItem> recurringInvestments = new ArrayList<>();
		for (InvestmentItem investment : investments) {
			if (investment.getRecurring()) {
				recurringInvestments.add(investment);
			}
		}
		return recurringInvestments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonthName(Month month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getExtraIncome() {
		return extraIncome;
	}

	public void setExtraIncome(BigDecimal extraIncome) {
		this.extraIncome = extraIncome;
	}

	public List<ExpenseItem> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<ExpenseItem> expenses) {
		this.expenses = expenses;
	}

	public List<InvestmentItem> getInvestments() {
		return investments;
	}

	public void setInvestments(List<InvestmentItem> investments) {
		this.investments = investments;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setTotalExpenses(BigDecimal totalCost) {
		this.totalExpenses = totalCost;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public void setTotalInvestments(BigDecimal totalInvestments) {
		this.totalInvestments = totalInvestments;
	}

	public void setTotalMoneyLeft(BigDecimal totalMoneyLeft) {
		this.totalMoneyLeft = totalMoneyLeft;
	}

	@Override
	public String toString() {
		return "MonthlyBudget [id=" + id + ", year=" + year + ", month=" + month + ", income=" + income
				+ ", extraIncome=" + extraIncome + "]";
	}

}
