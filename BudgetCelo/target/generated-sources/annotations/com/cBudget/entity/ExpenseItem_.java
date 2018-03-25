package com.cBudget.entity;

import com.cBudget.entity.enums.ExpenseType;
import com.cBudget.entity.enums.NecessityType;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExpenseItem.class)
public abstract class ExpenseItem_ extends com.cBudget.entity.BudgetItem_ {

	public static volatile SingularAttribute<ExpenseItem, NecessityType> necessityLevel;
	public static volatile ListAttribute<ExpenseItem, ExpenseTracker> trackers;
	public static volatile SingularAttribute<ExpenseItem, ExpenseType> expenseType;
	public static volatile SingularAttribute<ExpenseItem, MonthlyBudget> monthlyBudget;
	public static volatile SingularAttribute<ExpenseItem, Boolean> isRecurring;
	public static volatile SingularAttribute<ExpenseItem, Boolean> completed;

}

