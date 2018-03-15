package com.cBudget.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.entity.UserAuthentication;
import com.cBudget.service.AuthenticationService;

@Named
@SessionScoped
public class AuthenticationController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AuthenticationService authService;
	
	private UserAuthentication authentication;
	
	private boolean loggedIn;
	
	public String login() {
		if(authService.canLogin(authentication)) {
			getCurrentSession().setAttribute("currentUser", authentication);
			return JsfUtil.redirectable("/views/monthlyBudget/list");
		} else {
			JsfUtil.addErrorMessage("Email/Password Incorrect");
			return "";
		}
	}
	
	public boolean isLoggedIn() {
		HttpSession session = getCurrentSession();
		if(session.getAttribute("currentUser") == null) {
			loggedIn = false;
		} else {
			loggedIn = true;
		}
		return loggedIn;
	}

	public String logout() {
		HttpSession session = getCurrentSession();
		session.invalidate();
		return goToLogin();
	}
	
	public String goToLogin() {
		return JsfUtil.redirectable("/login");
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public UserAuthentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(UserAuthentication authentication) {
		this.authentication = authentication;
	}
	
	private HttpSession getCurrentSession() {
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext();
	}

}
