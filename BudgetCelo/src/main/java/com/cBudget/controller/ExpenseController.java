package com.cBudget.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.cBudget.controller.utils.DropDownsUtil;
import com.cBudget.entity.enums.ExpenseType;
import com.cBudget.entity.enums.NecessityType;
import com.cBudget.entity.enums.comparators.EnumComparator;

/**
 * 
 * @author Celokuhle.myeza
 *
 */

@Named
@RequestScoped
public class ExpenseController {

	public ExpenseController() {
	}

	public SelectItem[] getTypes() {
		Map<Integer, Object> expenseTypes = new TreeMap<>(EnumComparator.get());
		for (ExpenseType expenseType : ExpenseType.values()) {
			expenseTypes.put(expenseType.ordinal(), expenseType);
		}
		return DropDownsUtil.getSelectItems(expenseTypes);
	}

	public SelectItem[] getNecessityLevels() {
		Map<Integer, Object> necessityLevels = new TreeMap<>(EnumComparator.get());
		for (NecessityType necessityLevel : NecessityType.values()) {
			necessityLevels.put(necessityLevel.ordinal(), necessityLevel);
		}
		return DropDownsUtil.getSelectItems(necessityLevels);
	}

}
