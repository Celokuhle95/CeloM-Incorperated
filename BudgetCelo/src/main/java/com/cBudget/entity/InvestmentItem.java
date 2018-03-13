package com.cBudget.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cBudget.entity.enums.InvestmentType;
import com.cBudget.entity.enums.RiskLevel;

@Entity
public class InvestmentItem extends BudgetItem {

	@Enumerated(EnumType.STRING)
	private InvestmentType investmentType;

	@Enumerated(EnumType.STRING)
	private RiskLevel riskLevel;

	@Column
	private BigDecimal interestRate;

	@Column
	private boolean isRecurring;
	
	@Column
	private Integer period;
	
	@Transient //map correct
	private boolean completed;
	
	@Transient
	private List<InvestementTracker> trackers;
	
	@ManyToOne
	private MonthlyBudget monthlyBudget;

	public InvestmentItem(InvestmentType investmentType, RiskLevel riskLevel, String name, BigDecimal amount,
			BigDecimal interestRate, boolean isRecurring, Integer period) {
		super(name, amount);
		this.investmentType = investmentType;
		this.riskLevel = riskLevel;
		this.interestRate = interestRate;
		this.isRecurring = isRecurring;
		this.period = period;
		this.completed = false;
		this.trackers = new ArrayList<>();
	}

	public InvestmentItem() {
		this.isRecurring = false;
	}

	public RiskLevel getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(RiskLevel riskLevel) {
		this.riskLevel = riskLevel;
	}

	public InvestmentType getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
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

	public List<InvestementTracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<InvestementTracker> trackers) {
		this.trackers = trackers;
	}
	
}
