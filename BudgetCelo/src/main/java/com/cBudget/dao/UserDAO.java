package com.cBudget.dao;

import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cBudget.entity.User;

@Named
public class UserDAO extends BaseDAO<User> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public UserDAO() {
		super(User.class);
	}

	public User findByMail(String email) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> q = cb.createQuery(User.class);
		Root<User> user = q.from(User.class);
		q.select(user).where(cb.equal(user.get("email"), email));
		TypedQuery<User> tq = getEntityManager().createQuery(q);
		return tq.getSingleResult();
	}
}
