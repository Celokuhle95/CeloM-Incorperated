package com.cBudget.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.cBudget.controller.utils.MoneyUtil;

@MappedSuperclass
public class Tracker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String Summary;

	@Column
	private BigDecimal amount;

	@Column
	private LocalDate date;
	
	@Column
	private LocalTime time;
	
	public Tracker() {
		date = LocalDate.now();
		time = LocalTime.now();
	}

	public LocalDate getDate() {
		return date;
	}
	
	public String getDateFormatted() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		return date.format(formatter);
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public String getAmountFormatted() {
		return MoneyUtil.thousandSeperated(getAmount());
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public LocalTime getTime() {
		return time;
	}
	public String getTimeFormatted() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return time.format(formatter);
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	protected Integer getId() {
		return id;
	}
	
}
