package com.cBudget.entity;

import com.cBudget.entity.enums.InvestmentType;
import com.cBudget.entity.enums.RiskLevel;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InvestmentItem.class)
public abstract class InvestmentItem_ extends com.cBudget.entity.BudgetItem_ {

	public static volatile SingularAttribute<InvestmentItem, BigDecimal> interestRate;
	public static volatile ListAttribute<InvestmentItem, InvestmentTracker> trackers;
	public static volatile SingularAttribute<InvestmentItem, Integer> period;
	public static volatile SingularAttribute<InvestmentItem, RiskLevel> riskLevel;
	public static volatile SingularAttribute<InvestmentItem, MonthlyBudget> monthlyBudget;
	public static volatile SingularAttribute<InvestmentItem, Boolean> isRecurring;
	public static volatile SingularAttribute<InvestmentItem, InvestmentType> investmentType;
	public static volatile SingularAttribute<InvestmentItem, Boolean> completed;

	public static final String INTEREST_RATE = "interestRate";
	public static final String TRACKERS = "trackers";
	public static final String PERIOD = "period";
	public static final String RISK_LEVEL = "riskLevel";
	public static final String MONTHLY_BUDGET = "monthlyBudget";
	public static final String IS_RECURRING = "isRecurring";
	public static final String INVESTMENT_TYPE = "investmentType";
	public static final String COMPLETED = "completed";

}

