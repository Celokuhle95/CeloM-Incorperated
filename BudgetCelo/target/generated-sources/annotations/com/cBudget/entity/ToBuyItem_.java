package com.cBudget.entity;

import com.cBudget.entity.enums.ImportanceLevel;
import com.cBudget.entity.enums.Month;
import com.cBudget.entity.enums.ToBuyType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ToBuyItem.class)
public abstract class ToBuyItem_ extends com.cBudget.entity.BudgetItem_ {

	public static volatile SingularAttribute<ToBuyItem, User> owner;
	public static volatile SingularAttribute<ToBuyItem, ImportanceLevel> importanceLevel;
	public static volatile SingularAttribute<ToBuyItem, Boolean> completed;
	public static volatile SingularAttribute<ToBuyItem, ToBuyType> toBuyType;
	public static volatile SingularAttribute<ToBuyItem, Month> buyMonth;
	public static volatile SingularAttribute<ToBuyItem, Integer> buyYear;

}

