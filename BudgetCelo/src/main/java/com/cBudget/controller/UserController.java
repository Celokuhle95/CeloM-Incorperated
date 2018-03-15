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

	@Inject
	private UserService userService;
	
	private User user;

	public String register() {
//		userService.register(user);
//		return JsfUtil.redirectable("/login");
		return "";
	}

	public String goToRegister() {
		return JsfUtil.redirectable("/views/user/create");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
