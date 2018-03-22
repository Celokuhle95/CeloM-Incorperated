/**
 * @author celokuhle.myeza
 * @since March 2018
 */

package com.cBudget.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class VisibilityController implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean showListBudgetsSensitiveInfo;

	private boolean showExpenseSensitiveInfo;

	private boolean showInvestementSensitiveInfo;

	private boolean showRuleSensitiveInfo;

	private boolean showBudgetInfo;

	public void toggleShowListBudgetsSensitiveInfo() {
		showListBudgetsSensitiveInfo = !showListBudgetsSensitiveInfo;
	}
	
	public void toggleShowExpenseSensitiveInfo() {
		showExpenseSensitiveInfo = !showExpenseSensitiveInfo;
	}

	public void toggleShowInvestementSensitiveInfo() {
		showInvestementSensitiveInfo = !showInvestementSensitiveInfo;
	}

	public void toggleShowRuleSensitiveInfo() {
		showRuleSensitiveInfo = !showRuleSensitiveInfo;
	}
	
	public void showAllSensitiveInfo() {
		showExpenseSensitiveInfo = true;
		showInvestementSensitiveInfo = true;
		showRuleSensitiveInfo = true;
		showBudgetInfo = false;
	}

	public void hideAllSensitiveInfo() {
		showExpenseSensitiveInfo = false;
		showInvestementSensitiveInfo = false;
		showRuleSensitiveInfo = false;
		showBudgetInfo = true;
	}

	public boolean isShowExpenseSensitiveInfo() {
		return showExpenseSensitiveInfo;
	}
	
	public String disguiser() {
		return "***";
	}

	//setters and getters
	public boolean isShowListBudgetsSensitiveInfo() {
		return showListBudgetsSensitiveInfo;
	}

	public void setShowListBudgetsSensitiveInfo(boolean showListBudgetsSensitiveInfo) {
		this.showListBudgetsSensitiveInfo = showListBudgetsSensitiveInfo;
	}

	public boolean isShowInvestementSensitiveInfo() {
		return showInvestementSensitiveInfo;
	}

	public void setShowInvestementSensitiveInfo(boolean showInvestementSensitiveInfo) {
		this.showInvestementSensitiveInfo = showInvestementSensitiveInfo;
	}

	public boolean isShowRuleSensitiveInfo() {
		return showRuleSensitiveInfo;
	}

	public void setShowRuleSensitiveInfo(boolean showRuleSensitiveInfo) {
		this.showRuleSensitiveInfo = showRuleSensitiveInfo;
	}

	public boolean isShowBudgetInfo() {
		return showBudgetInfo;
	}

	public void setShowBudgetInfo(boolean showBudgetInfo) {
		this.showBudgetInfo = showBudgetInfo;
	}

	public void setShowExpenseSensitiveInfo(boolean showExpenseSensitiveInfo) {
		this.showExpenseSensitiveInfo = showExpenseSensitiveInfo;
	}

}
