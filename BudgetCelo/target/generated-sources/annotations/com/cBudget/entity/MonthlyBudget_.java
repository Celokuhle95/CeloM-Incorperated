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
	public static volatile SingularAttribute<MonthlyBudget, BigDecimal> extraIncome;
	public static volatile SingularAttribute<MonthlyBudget, Month> month;
	public static volatile SingularAttribute<MonthlyBudget, Integer> year;
	public static volatile SingularAttribute<MonthlyBudget, Integer> id;
	public static volatile ListAttribute<MonthlyBudget, InvestmentItem> investments;
	public static volatile ListAttribute<MonthlyBudget, ExpenseItem> expenses;

}

