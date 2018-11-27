package com.cBudget.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cBudget.entity.enums.ImportanceLevel;
import com.cBudget.entity.enums.Month;
import com.cBudget.entity.enums.ToBuyType;

@Entity
@Table(name="ToBuy")
public class ToBuyItem extends BudgetItem {

	@Enumerated(EnumType.STRING)
	private ToBuyType toBuyType;
	
	@Column
	private String description;

	@Enumerated(EnumType.STRING)
	private ImportanceLevel importanceLevel;

	@Column
	private Integer buyYear;
	
	@Column
	private Month buyMonth;
	
	@Column
	private boolean completed;
	
	@Column
	private LocalDate dateCreated;
	
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "owner_id")
	private User owner;

	public ToBuyItem(ToBuyType toBuyType, String name, ImportanceLevel importanceLevel, BigDecimal amount,
			Integer buyYear, Month buyMonth) {
		super(name, amount);
		this.toBuyType = toBuyType;
		this.importanceLevel = importanceLevel;
		this.buyYear = buyYear;
		this.buyMonth = buyMonth;
		this.dateCreated = LocalDate.now();
		this.dateCreated = LocalDate.now();
	}

	public ToBuyItem() {
		this.dateCreated = LocalDate.now();
	}

	public ImportanceLevel getImportanceLevel() {
		return importanceLevel;
	}

	public void setImportanceLevel(ImportanceLevel priorityLevel) {
		this.importanceLevel = priorityLevel;
	}

	public ToBuyType getToBuyType() {
		return toBuyType;
	}
	
	public void setToBuyType(ToBuyType toBuyType) {
		this.toBuyType = toBuyType;
	}

	public Month getBuyMonth() {
		return buyMonth;
	}

	public void setBuyMonth(Month buyMonth) {
		this.buyMonth = buyMonth;
	}

	public int getBuyYear() {
		return buyYear;
	}

	public void setBuyYear(Integer buyYear) {
		this.buyYear = buyYear;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
