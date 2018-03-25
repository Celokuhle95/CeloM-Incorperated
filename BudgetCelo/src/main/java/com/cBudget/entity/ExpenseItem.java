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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.cBudget.controller.utils.MoneyUtil;
import com.cBudget.entity.enums.ExpenseType;
import com.cBudget.entity.enums.NecessityType;

@Entity
public class ExpenseItem extends BudgetItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	private ExpenseType expenseType;

	@Enumerated(EnumType.STRING)
	private NecessityType necessityLevel;

	@Column
	private boolean isRecurring;
	
	@OneToMany(mappedBy = "expense", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ExpenseTracker> trackers = new ArrayList<>();
	
	@ManyToOne
	private MonthlyBudget monthlyBudget;
	
	@Column
	private boolean completed;

	public ExpenseItem(ExpenseType expenseType, NecessityType necessityLevel, String name, BigDecimal amount,
			boolean isRecurring) {
		super(name, amount);
		this.necessityLevel = necessityLevel;
		this.expenseType = expenseType;
		this.isRecurring = isRecurring;
		this.trackers = new ArrayList<>();
		this.completed = false;
	}

	public ExpenseItem() {
		this.isRecurring = false;
		this.completed = false;
	}
	
	public BigDecimal getTotalTracked() {
		BigDecimal total = BigDecimal.ZERO;
		for (ExpenseTracker tracker : trackers) {
			total = total.add(tracker.getAmount());
		}
		return total;
	}
	
	public String getTotalTrackedFormatted() {
		return MoneyUtil.thousandSeperated(getTotalTracked());
	}

	public NecessityType getNecessityLevel() {
		return necessityLevel;
	}

	public void setNecessityLevel(NecessityType necessityLevel) {
		this.necessityLevel = necessityLevel;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}

	public boolean getRecurring() {
		return isRecurring;
	}

	public void setRecurring(boolean isRecurring) {
	 	this.isRecurring = isRecurring;
	}

	public MonthlyBudget getMonthlyBudget() {
		return monthlyBudget;
	}

	public void setMonthlyBudget(MonthlyBudget monthlyBudget) {
		this.monthlyBudget = monthlyBudget;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public List<ExpenseTracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<ExpenseTracker> trackers) {
		this.trackers = trackers;
	}

	@Override
	public String toString() {
		return "ExpenseItem [expenseType=" + expenseType + ", necessityLevel=" + necessityLevel + ", isRecurring="
				+ isRecurring + ", name=" + name + ", amount=" + amount + "]";
	}

}
