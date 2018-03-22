package com.cBudget.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.cBudget.controller.utils.JsfUtil;
import com.cBudget.controller.utils.PasswordUtil;
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
		String hashedPassword = PasswordUtil.generateHash(authentication.getPassword());
		authentication.setPassword(hashedPassword);
		if(authService.canLogin(authentication)) {
			PasswordUtil.getCurrentSession().setAttribute("currentUser", authentication);
			reset();
			return JsfUtil.redirectable("/views/monthlyBudget/list");
		} else {
			JsfUtil.addErrorMessage("Email/Password Incorrect");
			return "";
		}
	}
	
	public void authenticate() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		if(isSecuredPage(context)) {
			System.out.println("SERVLTE pATH: ");
			if(!isLoggedIn()) {
				try {
					context.redirect(context.getRequestContextPath() + "/login.xhtml");
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private boolean isSecuredPage(ExternalContext context) {
		String servletPath = context.getRequestServletPath();
		if(servletPath.contains("login")) {
			System.out.println("MYEZA");
			return false;
		}
		if(servletPath.contains("user/create")) {
			return false;
		}
		System.out.println("CELO");
		return true;
	}
	
	private void reset() {
		authentication = new UserAuthentication();
	}
	
	public boolean isLoggedIn() {
		loggedIn = PasswordUtil.isAuthenticated();
		return loggedIn;
	}

	public String logout() {
		HttpSession session = PasswordUtil.getCurrentSession();
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
	


}
