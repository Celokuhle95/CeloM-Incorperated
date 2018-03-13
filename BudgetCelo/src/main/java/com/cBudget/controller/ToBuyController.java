package com.cBudget.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.cBudget.controller.utils.DropDownsUtil;
import com.cBudget.entity.enums.ImportanceLevel;

@Named
@SessionScoped
public class ToBuyController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public SelectItem[] getTypes() {
		List<SelectItem> importanceLevels = new ArrayList<>();
		for (ImportanceLevel importanceLevel : ImportanceLevel.values()) {
			if(DropDownsUtil.containsMultipleWords(importanceLevel.toString())) {
				importanceLevels.add(new SelectItem(importanceLevel, DropDownsUtil.handleMultipleWords(importanceLevel.toString())));
			} else {
				importanceLevels.add(new SelectItem(importanceLevel, DropDownsUtil.getInCapAndLowercase(importanceLevel.toString())));
			}
		}
		return (SelectItem[]) importanceLevels.toArray();
	}
}
