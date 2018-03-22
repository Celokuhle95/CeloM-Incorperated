package com.cBudget.dao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.cBudget.entity.InvestmentItem;

@Named
@RequestScoped
public class InvestmentDAO extends BaseDAO<InvestmentItem> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public InvestmentDAO() {
		super(InvestmentItem.class);
	}


}
