package com.cBudget.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tracker.class)
public abstract class Tracker_ {

	public static volatile SingularAttribute<Tracker, LocalDate> date;
	public static volatile SingularAttribute<Tracker, BigDecimal> amount;
	public static volatile SingularAttribute<Tracker, String> Summary;
	public static volatile SingularAttribute<Tracker, Integer> id;
	public static volatile SingularAttribute<Tracker, LocalTime> time;

	public static final String DATE = "date";
	public static final String AMOUNT = "amount";
	public static final String SUMMARY = "Summary";
	public static final String ID = "id";
	public static final String TIME = "time";

}

