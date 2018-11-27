package com.cBudget.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cBudget.entity.UserAuthentication;

@Named
@RequestScoped
public class AuthenticationDAO extends BaseDAO<UserAuthentication> {

	protected AuthenticationDAO() {
		super(UserAuthentication.class);
	}

	public boolean canLogin(UserAuthentication auth) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UserAuthentication> q = cb.createQuery(UserAuthentication.class);
		Root<UserAuthentication> userAuth = q.from(UserAuthentication.class);

		Predicate userPredicate = cb.equal(userAuth.get("username"), auth.getUsername());
		q.select(userAuth).where(userPredicate);

		try {
			TypedQuery<UserAuthentication> tq = createTypedQuery(q);
			UserAuthentication result = tq.getSingleResult();

			if (result.getPassword().equals(auth.getPassword())) {
				return true;
			} else {
				return false;
			}
		} catch (NoResultException e) {
			System.out.println(e);
			return false;
		}

	}

}
