package com.cBudget.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.InvestmentDAO;
import com.cBudget.entity.InvestmentItem;
import com.cBudget.service.InvestmentService;

@Stateless
public class InvestmentServiceImpl implements InvestmentService{

	@Inject
	private InvestmentDAO investmentDAO;
	
	@Override
	public void edit(InvestmentItem investment) {
		investmentDAO.edit(investment);
	}
}
