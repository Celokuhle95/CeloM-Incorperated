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
import com.cBudget.entity.InvestmentItem;
import com.cBudget.entity.InvestmentTracker;
import com.cBudget.service.InvestmentService;

@Named
@SessionScoped
public class InvestmentTrackerController implements Serializable{

	private static final long serialVersionUID = 1L;

	private InvestmentItem investment = new InvestmentItem();
	
	private InvestmentTracker tracker = new InvestmentTracker();
	
	private BarChartModel barChart;
	
	@Inject
	private InvestmentService service;
	
	public String save() {
		service.edit(investment);
		return JsfUtil.redirectable("/views/monthlyBudget/view");
	}
	
	public void addTracker() {
		tracker.setInvestment(investment);
		investment.getTrackers().add(tracker);
		reset();
		RequestContext.getCurrentInstance().update("investmentTrackerDialogForm");
		RequestContext.getCurrentInstance().execute("PF('investmentTrackerDialog').hide()");
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
		ChartSeries allocated = addChartData("Allocated", investment.getAmount());
		ChartSeries spent = addChartData("Invested", investment.getTotalTracked());
		
		barChart.addSeries(allocated);
		barChart.addSeries(spent);
	}
	
	private ChartSeries addChartData(String label, BigDecimal value) {
		ChartSeries series = new ChartSeries();
		series.set("Invested", value);
		series.setLabel(label);
		return series;
	}

	public String getTrackedTotalColor() {
		if (investment.getTotalTracked().compareTo(investment.getAmount()) == 1) {// greater than
			return ColorUtil.UNACCEPTED;
		}
		return ColorUtil.ACCEPTED;
	}
	
	public String initTracker() {
		reset();
		if (investment.getTrackers() == null) {
			investment.setTrackers(new ArrayList<>());
		}
		return JsfUtil.redirectable("/views/tracker/investment");
	}

	
	public void reset() {
		tracker = new InvestmentTracker();
	}

	public InvestmentItem getInvestment() {
		return investment;
	}

	public void setInvestment(InvestmentItem investment) {
		this.investment = investment;
	}

	public InvestmentTracker getTracker() {
		return tracker;
	}

	public void setTracker(InvestmentTracker tracker) {
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
