package com.cBudget.entity;

import com.cBudget.entity.enums.ExpenseType;
import com.cBudget.entity.enums.ImportanceLevel;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ToBuyItem.class)
public abstract class ToBuyItem_ extends com.cBudget.entity.BudgetItem_ {

	public static volatile SingularAttribute<ToBuyItem, ImportanceLevel> priorityLevel;
	public static volatile SingularAttribute<ToBuyItem, ExpenseType> ExpenseType;
	public static volatile SingularAttribute<ToBuyItem, String> buyMonth;
	public static volatile SingularAttribute<ToBuyItem, Integer> buyYear;

}

