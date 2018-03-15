package com.cBudget.dao;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.cBudget.entity.ExpenseItem;

@Named
@RequestScoped
public class ExpenseDAO extends BaseDAO<ExpenseItem> implements Serializable{

	private static final long serialVersionUID = 1L;

	public ExpenseDAO() {
		super(ExpenseItem.class);
	}

}
