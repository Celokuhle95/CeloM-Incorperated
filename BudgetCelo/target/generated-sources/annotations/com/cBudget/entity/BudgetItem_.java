package com.cBudget.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BudgetItem.class)
public abstract class BudgetItem_ {

	public static volatile SingularAttribute<BudgetItem, BigDecimal> amount;
	public static volatile SingularAttribute<BudgetItem, String> name;
	public static volatile SingularAttribute<BudgetItem, Integer> id;

}

