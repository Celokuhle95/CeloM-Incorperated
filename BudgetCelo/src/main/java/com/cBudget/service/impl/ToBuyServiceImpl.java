package com.cBudget.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.ToBuyDAO;
import com.cBudget.entity.ToBuyItem;
import com.cBudget.service.ToBuyService;

@Stateless
public class ToBuyServiceImpl implements ToBuyService{
	
	@Inject
	private ToBuyDAO toBuyDAO;

	@Override
	public void edit(ToBuyItem toBuy) {
		toBuyDAO.edit(toBuy);
	}

	@Override
	public void update(List<ToBuyItem> toBuyItems) {
		toBuyDAO.update(toBuyItems);
	}

}
