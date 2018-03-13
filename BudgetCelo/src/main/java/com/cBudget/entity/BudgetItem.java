package com.cBudget.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.cBudget.controller.utils.MoneyUtil;

@MappedSuperclass
public class BudgetItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column
	protected String name;

	@Column
	protected BigDecimal amount;

	public BudgetItem(String name, BigDecimal amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public BudgetItem() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAmountFormated() {
		return MoneyUtil.thousandSeperated(amount);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
