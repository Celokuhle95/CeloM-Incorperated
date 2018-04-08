package com.cBudget.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.cBudget.entity.ToBuyItem;

@Named
@RequestScoped
public class ToBuyDAO extends BaseDAO<ToBuyItem> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	protected ToBuyDAO() {
		super(ToBuyItem.class);
	}
	
	public void update(List<ToBuyItem> toBuyItems) {
		for (ToBuyItem toBuyItem : toBuyItems) {
			getEntityManager().merge(toBuyItem);
		}
	}
	
}
