package com.cBudget.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RegisteredUser")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String surname;
	
	@Column
	private String email;
	
	@Column
	private String IDNumber;
	
	@Column
	private LocalDate dob;
	
	@Column
	private BigDecimal income;
	
	@Column
	private String profileImage;
	
	@OneToMany
	private List<MonthlyBudget> budgets;

	protected Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getSurname() {
		return surname;
	}

	protected void setSurname(String surname) {
		this.surname = surname;
	}

	protected String getIDNumber() {
		return IDNumber;
	}

	protected void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	protected LocalDate getDob() {
		return dob;
	}

	protected void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	protected String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected BigDecimal getIncome() {
		return income;
	}

	protected void setIncome(BigDecimal income) {
		this.income = income;
	}

	protected String getProfileImage() {
		return profileImage;
	}

	protected void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	protected List<MonthlyBudget> getBudgets() {
		return budgets;
	}

	protected void setBudgets(List<MonthlyBudget> budgets) {
		this.budgets = budgets;
	}

}
