package com.cBudget.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.cBudget.controller.utils.ColorUtil;
import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.entity.ExpenseItem;
import com.cBudget.entity.ExpenseTracker;
import com.cBudget.service.ExpenseService;

@Named
@SessionScoped
public class ExpenseTrackerController implements Serializable {

	private static final long serialVersionUID = 1L;

	private ExpenseItem expense = new ExpenseItem();

	private ExpenseTracker tracker = new ExpenseTracker();

	private BarChartModel barChart;

	@Inject
	private ExpenseService service;

	public String save() {
		service.update(expense);
		return JsfUtil.redirectable("/views/monthlyBudget/view");
	}

	public void addTracker() {
		tracker.setExpense(expense);
		expense.getTrackers().add(tracker);
		reset();
		RequestContext.getCurrentInstance().update("expenseTrackerDialogForm");
		RequestContext.getCurrentInstance().execute("PF('expenseTrackerDialog').hide()");
	}

	public void createBarChart() {
		barChart = new BarChartModel();
		barChart.setLegendPosition("ne");
		barChart.setAnimate(true);
		barChart.setBarMargin(5);
		barChart.setExtender("ext");
		Axis yAxis = barChart.getAxis(AxisType.Y);
		yAxis.setLabel("R");

		initBarChart();
	}

	public void initBarChart() {
		barChart.clear();
		ChartSeries allocated = addChartData("Allocated", expense.getAmount());
		ChartSeries spent = addChartData("Spent", expense.getTotalTracked());

		barChart.addSeries(allocated);
		barChart.addSeries(spent);
	}

	private ChartSeries addChartData(String label, BigDecimal value) {
		ChartSeries series = new ChartSeries();
		series.set("Spent", value);
		series.setLabel(label);
		return series;
	}

	public String getTrackedTotalColor() {
		if (expense.getTotalTracked().compareTo(expense.getAmount()) == 1) {// greater than
			return ColorUtil.UNACCEPTED;
		}
		return ColorUtil.ACCEPTED;
	}

	public String initTracker() {
		reset();
		if (expense.getTrackers() == null) {
			expense.setTrackers(new ArrayList<>());
		}
		return JsfUtil.redirectable("/views/tracker/expense");
	}

	public void reset() {
		tracker = new ExpenseTracker();
	}

	public ExpenseItem getExpense() {
		return expense;
	}

	public void setExpense(ExpenseItem expense) {
		this.expense = expense;
	}

	public ExpenseTracker getTracker() {
		return tracker;
	}

	public void setTracker(ExpenseTracker tracker) {
		this.tracker = tracker;
	}

	public BarChartModel getBarChart() {
		initBarChart();
		return barChart;
	}

	public void setBarChart(BarChartModel barChart) {
		this.barChart = barChart;
	}

}
