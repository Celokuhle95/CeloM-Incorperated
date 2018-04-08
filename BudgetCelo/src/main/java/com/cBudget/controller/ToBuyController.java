package com.cBudget.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.cBudget.controller.utils.BudgetDateUtil;
import com.cBudget.controller.utils.DropDownsUtil;
import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.entity.ToBuyItem;
import com.cBudget.entity.enums.BudgetDate;
import com.cBudget.entity.enums.ImportanceLevel;
import com.cBudget.entity.enums.ToBuyType;
import com.cBudget.entity.enums.comparators.EnumComparator;
import com.cBudget.service.AuthenticationService;
import com.cBudget.service.ToBuyService;

@Named
@SessionScoped
public class ToBuyController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ToBuyService toBuyService;
	
	@Inject
	private AuthenticationService authenticationService;
	
	private List<ToBuyItem> toBuyItems = new ArrayList<>();
	
	private ToBuyItem toBuyItem = new ToBuyItem();
	
	public void save() {
		toBuyService.update(toBuyItems);
	}
	
	public void addItem() {
		if(isValid()) {
			toBuyItem.setOwner(authenticationService.getCurrentUser());
			toBuyItems.add(toBuyItem);
			//hide the dialog
			reset();
		} else {
			JsfUtil.addErrorMessage("You must choose a Year and Month that are not in the past");
		}
	}
	
	private boolean isValid() {
		if(BudgetDateUtil.isYearValid(toBuyItem.getBuyYear())) {
			BudgetDate budgetDate = new BudgetDate(toBuyItem.getBuyMonth(), toBuyItem.getBuyYear());
			if(BudgetDateUtil.isMonthValid(budgetDate)) {
				return true;
			} 
			return false;
		} else {
			return false;
		}
	}
	
	private void reset() {
		toBuyItem = new ToBuyItem();
	}
	
	public SelectItem[] getTypes() {
		Map<Integer, Object> toBuyTypes = new TreeMap<>(EnumComparator.get());
		for (ToBuyType toBuyType : ToBuyType.values()) {
			toBuyTypes.put(toBuyType.ordinal(), toBuyType);
		}
		return DropDownsUtil.getSelectItems(toBuyTypes);
	}
	
	public SelectItem[] getImportanceLevels() {
		Map<Integer, Object> importanceLevels = new TreeMap<>(EnumComparator.get());
		for (ImportanceLevel importanceLevel : ImportanceLevel.values()) {
			importanceLevels.put(importanceLevel.ordinal(), importanceLevel);
		}
		return DropDownsUtil.getSelectItems(importanceLevels);
	}

	public ToBuyItem getToBuyItem() {
		return toBuyItem;
	}

	public void setToBuyItem(ToBuyItem toBuyItem) {
		this.toBuyItem = toBuyItem;
	}

	public List<ToBuyItem> getToBuyItems() {
		return toBuyItems;
	}

	public void setToBuyItems(List<ToBuyItem> toBuyItems) {
		this.toBuyItems = toBuyItems;
	}
	
}
