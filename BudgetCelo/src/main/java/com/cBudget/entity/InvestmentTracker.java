package com.cBudget.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class InvestmentTracker extends Tracker {

	@ManyToOne
	private InvestmentItem investment;

	public InvestmentItem getInvestment() {
		return investment;
	}

	public void setInvestment(InvestmentItem investment) {
		this.investment = investment;
	}
	
}
