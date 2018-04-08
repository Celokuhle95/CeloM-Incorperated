package com.cBudget.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.cBudget.dao.UserDAO;
import com.cBudget.entity.User;
import com.cBudget.service.UserService;

@Stateless
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO userDAO;

	@Override
	public void register(User user) {
		userDAO.create(user);
	}

	@Override
	public void findByMail(String email) {
		userDAO.findByMail(email);
	}


}
