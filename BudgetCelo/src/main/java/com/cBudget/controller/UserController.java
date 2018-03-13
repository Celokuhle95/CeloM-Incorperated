package com.cBudget.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.entity.User;
import com.cBudget.service.UserService;

@Named
@SessionScoped
public class UserController implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	@Inject
	private UserService userService;

	public String register() {
		userService.register(user);
		return JsfUtil.redirectable("");
	}

	public String login() {
		//login logic using httpSession
		return JsfUtil.redirectable("");
	}
	
	public String logout() {
		//Do logout logic including invalidating httpSession
		return JsfUtil.redirectable("views/user/login");
	}

	public String goToLogin() {
		return JsfUtil.redirectable("views/user/login");
	}

	public String goToRegister() {
		return JsfUtil.redirectable("views/user/register");
	}
}
