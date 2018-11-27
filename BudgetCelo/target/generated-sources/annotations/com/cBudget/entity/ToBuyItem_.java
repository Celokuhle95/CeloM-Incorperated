package com.cBudget.entity;

import com.cBudget.entity.enums.ImportanceLevel;
import com.cBudget.entity.enums.Month;
import com.cBudget.entity.enums.ToBuyType;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ToBuyItem.class)
public abstract class ToBuyItem_ extends com.cBudget.entity.BudgetItem_ {

	public static volatile SingularAttribute<ToBuyItem, User> owner;
	public static volatile SingularAttribute<ToBuyItem, LocalDate> dateCreated;
	public static volatile SingularAttribute<ToBuyItem, ImportanceLevel> importanceLevel;
	public static volatile SingularAttribute<ToBuyItem, String> description;
	public static volatile SingularAttribute<ToBuyItem, Boolean> completed;
	public static volatile SingularAttribute<ToBuyItem, ToBuyType> toBuyType;
	public static volatile SingularAttribute<ToBuyItem, Month> buyMonth;
	public static volatile SingularAttribute<ToBuyItem, Integer> buyYear;

	public static final String OWNER = "owner";
	public static final String DATE_CREATED = "dateCreated";
	public static final String IMPORTANCE_LEVEL = "importanceLevel";
	public static final String DESCRIPTION = "description";
	public static final String COMPLETED = "completed";
	public static final String TO_BUY_TYPE = "toBuyType";
	public static final String BUY_MONTH = "buyMonth";
	public static final String BUY_YEAR = "buyYear";

}

