package com.cBudget.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.AuthenticationDAO;
import com.cBudget.entity.UserAuthentication;

@Stateless
public class AuthenticatiServiceImpl implements AuthenticationService{
	
	@Inject
	private AuthenticationDAO authenticationDAO;

	@Override
	public boolean canLogin(UserAuthentication auth) {
		return authenticationDAO.canLogin(auth);
	}

}
