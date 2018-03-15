package com.cBudget.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author celokuhle.myeza
 */
public class BaseDAO<T> {

	@PersistenceContext(unitName = "cBudgetPersistenceUnit")
	private EntityManager entityManager;

	private Class<T> entityClass;

	protected BaseDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
		entityManager.persist(entity);
	}

	public void edit(T entity) {
		System.out.println("UPDATE CALLED");
		entityManager.merge(entity);
	}

	public void remove(T entity) {
		entityManager.remove(entity);
	}

	public T find(Integer id) {
		return entityManager.find(entityClass, id);
	}

	public List<T> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityClass);
		Root<T> entity = q.from(entityClass);
		q.select(entity);
		return createTypedQuery(q).getResultList();
	}
	
	protected <E> TypedQuery<E> createTypedQuery(CriteriaQuery<E> q) {
		return getEntityManager().createQuery(q);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
}
