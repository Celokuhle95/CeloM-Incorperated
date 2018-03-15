package com.cBudget.service;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

public interface BaseService<T> {
	
	default void create(T entity) {
		throw new NotImplementedException("Create Method Not Implemented");
	}

	default  void edit(T entity) {
		throw new NotImplementedException("Edit Method Not Implemented");
	}

	default  void remove(T entity) {
		throw new NotImplementedException("Remove Method Not Implemented");
	}

	default  T find(Integer id) {
		throw new NotImplementedException("Find Method Not Implemented");
	}

	default  List<T> findAll()  {
		throw new NotImplementedException("FindAll Method Not Implemented");
	}
	
}
