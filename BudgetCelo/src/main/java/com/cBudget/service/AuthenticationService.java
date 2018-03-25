package com.cBudget.service;

import javax.ejb.Local;

import com.cBudget.entity.User;
import com.cBudget.entity.UserAuthentication;

@Local
public interface AuthenticationService extends BaseService<UserAuthentication> {
	boolean canLogin(UserAuthentication auth);

	User getCurrentUser();
}
