package com.cBudget.entity.metamodel;

import java.math.BigDecimal;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.cBudget.entity.ExpenseItem;
import com.cBudget.entity.InvestmentItem;
import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.enums.Month;

@StaticMetamodel(MonthlyBudget.class)
public class MonthlyBudget_ {
	
	public static volatile SingularAttribute<MonthlyBudget, Integer> id;
	
	public static volatile SingularAttribute<MonthlyBudget, Integer> year;
	
	public static volatile SingularAttribute<MonthlyBudget, Month> month;
	
	public static volatile SingularAttribute<MonthlyBudget, BigDecimal> income;
	
	public static volatile SingularAttribute<MonthlyBudget, BigDecimal> extraIncome;
	
	public static volatile ListAttribute< MonthlyBudget, ExpenseItem> expenses;
	
	public static volatile ListAttribute< MonthlyBudget, InvestmentItem> investments;
	
}
