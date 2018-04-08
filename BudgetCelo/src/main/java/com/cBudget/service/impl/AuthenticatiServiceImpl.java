package com.cBudget.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.AuthenticationDAO;
import com.cBudget.dao.UserDAO;
import com.cBudget.entity.User;
import com.cBudget.entity.UserAuthentication;
import com.cBudget.service.AuthenticationService;

@Stateless
public class AuthenticatiServiceImpl implements AuthenticationService{
	
	@Inject
	private AuthenticationDAO authenticationDAO;
	
	@Inject
	private UserDAO userDAO;

	@Override
	public boolean canLogin(UserAuthentication auth) {
		return authenticationDAO.canLogin(auth);
	}

	@Override
	public User getCurrentUser() {
		return userDAO.getCurrentUser();
	}

}
