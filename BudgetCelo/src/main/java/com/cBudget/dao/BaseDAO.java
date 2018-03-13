package com.cBudget.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

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

	@SuppressWarnings("unchecked")
	public List<T> findAll(String entityName) {
		Query query = entityManager.createQuery("SELECT e from " + entityName + " e", entityClass);
		return query.getResultList();
	}
	
	protected <E> TypedQuery<E> createTypedQuery(CriteriaQuery<E> q) {
		return getEntityManager().createQuery(q);
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
}
