package com.cBudget.entity;

import com.cBudget.entity.enums.Month;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonthlyBudget.class)
public abstract class MonthlyBudget_ {

	public static volatile SingularAttribute<MonthlyBudget, BigDecimal> income;
	public static volatile SingularAttribute<MonthlyBudget, User> owner;
	public static volatile SingularAttribute<MonthlyBudget, BigDecimal> extraIncome;
	public static volatile SingularAttribute<MonthlyBudget, Month> month;
	public static volatile SingularAttribute<MonthlyBudget, Integer> year;
	public static volatile SingularAttribute<MonthlyBudget, Integer> id;
	public static volatile ListAttribute<MonthlyBudget, InvestmentItem> investments;
	public static volatile ListAttribute<MonthlyBudget, ExpenseItem> expenses;

	public static final String INCOME = "income";
	public static final String OWNER = "owner";
	public static final String EXTRA_INCOME = "extraIncome";
	public static final String MONTH = "month";
	public static final String YEAR = "year";
	public static final String ID = "id";
	public static final String INVESTMENTS = "investments";
	public static final String EXPENSES = "expenses";

}

