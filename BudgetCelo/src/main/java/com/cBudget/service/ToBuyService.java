package com.cBudget.service;

import java.util.List;

import javax.ejb.Local;

import com.cBudget.entity.ToBuyItem;

@Local
public interface ToBuyService extends BaseService<ToBuyItem>{

	void update(List<ToBuyItem> toBuyItems);
}
