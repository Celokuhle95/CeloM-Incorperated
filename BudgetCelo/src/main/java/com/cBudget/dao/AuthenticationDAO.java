package com.cBudget.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cBudget.entity.UserAuthentication;

@Named
@RequestScoped
public class AuthenticationDAO extends BaseDAO<UserAuthentication>{

	protected AuthenticationDAO() {
		super(UserAuthentication.class);
	}
	
	public boolean canLogin(UserAuthentication auth) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserAuthentication> q = cb.createQuery(UserAuthentication.class);
		Root<UserAuthentication> userAuth = q.from(UserAuthentication.class);
		q.select(userAuth).where(cb.equal(userAuth.get("username"), auth.getUsername()));
		try {
			TypedQuery<UserAuthentication> tq = createTypedQuery(q);
			UserAuthentication singleResult = tq.getSingleResult();
			if(singleResult.getPassword().equals(auth.getUsername())) {
				return true;
			} else {
				return false;
			}
		} catch (NoResultException e) {
			return false;
		}
		
		
	}

}
