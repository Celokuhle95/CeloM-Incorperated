package com.cBudget.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.cBudget.controller.utils.DropDownsUtil;
import com.cBudget.entity.enums.InvestmentType;
import com.cBudget.entity.enums.RiskLevel;
import com.cBudget.entity.enums.comparators.EnumComparator;

/**
 * 
 * @author Celokuhle Myeza
 *
 */

@Named
@RequestScoped
public class InvestmentController {

	public SelectItem[] getTypes() {
		Map<Integer, Object> investmentTypes = new TreeMap<>(EnumComparator.get());
		for (InvestmentType investmentType : InvestmentType.values()) {
			investmentTypes.put(investmentType.ordinal(), investmentType);
		}
		return DropDownsUtil.getSelectItems(investmentTypes);
	}

	public SelectItem[] getRiskLevels() {
		Map<Integer, Object> riskLevels = new TreeMap<>(EnumComparator.get());
		for (RiskLevel riskLevel : RiskLevel.values()) {
			riskLevels.put(riskLevel.ordinal(), riskLevel);
		}
		return DropDownsUtil.getSelectItems(riskLevels);
	}

}
