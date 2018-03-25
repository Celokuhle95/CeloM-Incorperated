package com.cBudget.entity;

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
	
	@OneToMany(mappedBy = "investment", cascade = {CascadeType.ALL}, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<InvestmentTracker> trackers = new ArrayList<>();
	
	@ManyToOne
	private MonthlyBudget monthlyBudget;
	
	@Column
	private boolean completed;

	public InvestmentItem(InvestmentType investmentType, RiskLevel riskLevel, String name, BigDecimal amount,
			BigDecimal interestRate, boolean isRecurring, Integer period) {
		super(name, amount);
		this.investmentType = investmentType;
		this.riskLevel = riskLevel;
		this.interestRate = interestRate;
		this.isRecurring = isRecurring;
		this.period = period;
		this.trackers = new ArrayList<>();
		this.completed = false;
	}

	public InvestmentItem() {
		this.completed = false;
		this.isRecurring = false;
	}
	
	public BigDecimal getTotalTracked() {
		BigDecimal total = BigDecimal.ZERO;
		for (InvestmentTracker tracker : trackers) {
			total = total.add(tracker.getAmount());
		}
		return total;
	}
	
	public String getTotalTrackedFormatted() {
		return MoneyUtil.thousandSeperated(getTotalTracked());
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

	public List<InvestmentTracker> getTrackers() {
		return trackers;
	}

	public void setTrackers(List<InvestmentTracker> trackers) {
		this.trackers = trackers;
	}

}
