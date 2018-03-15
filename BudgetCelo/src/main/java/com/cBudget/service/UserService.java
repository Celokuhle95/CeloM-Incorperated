package com.cBudget.service;

import javax.ejb.Local;

import com.cBudget.entity.User;

@Local
public interface UserService extends BaseService<User>{
	
	void register(User user);
	
	void findByMail(String email);
	
}
