package com.cBudget.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.controller.utils.PasswordsUtil;
import com.cBudget.entity.UserAuthentication;
import com.cBudget.service.AuthenticationService;

@Named
@SessionScoped
public class AuthenticationController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AuthenticationService authService;
	
	private UserAuthentication authentication = new UserAuthentication();
	
	private boolean loggedIn;
	
	public String login() {
		String hashedPassword = PasswordsUtil.generateHash(authentication.getPassword());
		authentication.setPassword(hashedPassword);
		if(authService.canLogin(authentication)) {
			getCurrentSession().setAttribute("currentUser", authentication);
			reset();
			return JsfUtil.redirectable("/views/monthlyBudget/list");
		} else {
			JsfUtil.addErrorMessage("Email/Password Incorrect");
			return "";
		}
	}
	
	private void reset() {
		authentication = new UserAuthentication();
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
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request =(HttpServletRequest) externalContext.getRequest();
		return (HttpSession)request.getSession();
	}

}
