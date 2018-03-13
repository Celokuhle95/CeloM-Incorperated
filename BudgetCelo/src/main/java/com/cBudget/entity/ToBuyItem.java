package com.cBudget.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.cBudget.entity.enums.ExpenseType;
import com.cBudget.entity.enums.ImportanceLevel;

@Entity
public class ToBuyItem extends BudgetItem {

	@Enumerated(EnumType.STRING)
	private ExpenseType ExpenseType;

	@Enumerated(EnumType.STRING)
	private ImportanceLevel priorityLevel;

	@Column
	private String buyMonth;

	@Column
	private Integer buyYear;

	public ToBuyItem(ExpenseType ExpenseType, String name, ImportanceLevel priorityLevel, BigDecimal amount,
			String buyMonth, Integer buyYear) {
		super(name, amount);
		this.ExpenseType = ExpenseType;
		this.priorityLevel = priorityLevel;
		this.buyMonth = buyMonth;
		this.buyYear = buyYear;
	}

	public ToBuyItem() {
	}

	public ImportanceLevel getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(ImportanceLevel priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public ExpenseType getExpenseType() {
		return ExpenseType;
	}

	public void setToBuyItemType(ExpenseType toBuyItemType) {
		this.ExpenseType = toBuyItemType;
	}

	public String getBuyMonth() {
		return buyMonth;
	}

	public void setBuyMonth(String buyMonth) {
		this.buyMonth = buyMonth;
	}

	public int getBuyYear() {
		return buyYear;
	}

	public void setBuyYear(Integer buyYear) {
		this.buyYear = buyYear;
	}

}
