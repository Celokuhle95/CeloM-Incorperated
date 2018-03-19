package com.cBudget.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.controller.utils.PasswordsUtil;
import com.cBudget.entity.User;
import com.cBudget.entity.UserAuthentication;
import com.cBudget.service.UserService;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;

	private User user = new User();

	private Date dateOfBirth;

	private String password;

	private String confirmPassword;

	public String register() {
		if (password.equals(confirmPassword)) {
			UserAuthentication auth = new UserAuthentication();
			auth.setUsername(user.getEmail());
			String saltedAndHashedPassword = PasswordsUtil.generateHash(password);
			auth.setPassword(saltedAndHashedPassword);

			user.setAuthentication(auth);
			user.setDob(getLocalDate(dateOfBirth));
			userService.register(user);
			reset();
			return JsfUtil.redirectable("/login");
		} else {
			JsfUtil.addErrorMessage("Password and Confirm Password do not match.");
			return "";
		}
	}

	private void reset() {
		user = new User();
		dateOfBirth = null;
		password = "";
		confirmPassword = "";
	}

	public String goToRegister() {
		return JsfUtil.redirectable("/views/user/create");
	}

	private LocalDate getLocalDate(Date utilDate) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(utilDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		return LocalDate.of(year, month, day);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPaswsword) {
		this.confirmPassword = confirmPaswsword;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
