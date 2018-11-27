package com.cBudget.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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
	
	private List<ToBuyItem> toBuys = new ArrayList<>();
	
	private ToBuyItem toBuy = new ToBuyItem();
	
	public BigDecimal totalTobuys() {
		return null;
	}
	
	public void save() {
		toBuyService.update(toBuys);
	}
	
	public void addItem() {
		if(isValid()) {
			toBuy.setOwner(authenticationService.getCurrentUser());
			toBuys.add(toBuy);
			//hide the dialog
			reset();
		} else {
			JsfUtil.addErrorMessage("You must choose a Year and Month that are not in the past");
		}
	}
	
	private boolean isValid() {
		if(BudgetDateUtil.isYearValid(toBuy.getBuyYear())) {
			BudgetDate budgetDate = new BudgetDate(toBuy.getBuyMonth(), toBuy.getBuyYear());
			if(BudgetDateUtil.isMonthValid(budgetDate)) {
				return true;
			} 
			return false;
		} else {
			return false;
		}
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
	
	public String goToToBuy() {
		return JsfUtil.redirectable("/views/toBuy/list");
	}
	
	public void reset() {
		toBuy = new ToBuyItem();
	}

	public ToBuyItem getToBuy() {
		return toBuy;
	}

	public void setToBuy(ToBuyItem toBuyItem) {
		this.toBuy = toBuyItem;
	}

	public List<ToBuyItem> getToBuys() {
		return toBuys;
	}

	public void setToBuys(List<ToBuyItem> toBuyItems) {
		this.toBuys = toBuyItems;
	}
	
}
