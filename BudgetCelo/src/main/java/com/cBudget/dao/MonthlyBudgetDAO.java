package com.cBudget.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cBudget.controller.utils.PasswordUtil;
import com.cBudget.entity.MonthlyBudget;
import com.cBudget.entity.UserAuthentication;
import com.cBudget.entity.enums.BudgetDate;
import com.cBudget.entity.enums.Month;

@Named
@RequestScoped
public class MonthlyBudgetDAO extends BaseDAO<MonthlyBudget> {
	
	public MonthlyBudgetDAO() {
		super(MonthlyBudget.class);
	}
	
	public List<Integer> getBudgetingYears() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Integer> q = cb.createQuery(Integer.class);
		Root<MonthlyBudget> budget = q.from(MonthlyBudget.class);
		Predicate userPredicate = getUserPredicate(cb, budget);
		q.select(budget.get("year")).where(userPredicate).distinct(true).orderBy(cb.desc(budget.get("year")));
		return createTypedQuery(q).getResultList();
	}

	public List<MonthlyBudget> getBudgetsByYear(Integer year) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MonthlyBudget> query = cb.createQuery(MonthlyBudget.class);
		Root<MonthlyBudget> budget = query.from(MonthlyBudget.class);

		Predicate userPredicate = getUserPredicate(cb, budget);
		Predicate yearPredicate = cb.equal(budget.get("year"), year);

		query.select(budget).where(cb.and(userPredicate, yearPredicate));

		query.orderBy(cb.desc(budget.get("month")));
		return createTypedQuery(query).getResultList();
	}
	
	private Predicate getUserPredicate(CriteriaBuilder cb, Root<MonthlyBudget> budgetRoot) {
		UserAuthentication authentication = PasswordUtil.getCurrentUserAuthentication();
		return cb.equal(budgetRoot.get("owner").get("email"), authentication.getUsername());
	}
	
	public boolean alreadyAdded(BudgetDate bDate) {
		List<MonthlyBudget> resultList = findByBudgetDate(bDate);
		return !resultList.isEmpty();
	}

	public List<MonthlyBudget> findByBudgetDate(BudgetDate bDate) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MonthlyBudget> q = cb.createQuery(MonthlyBudget.class);
		Root<MonthlyBudget> budget = q.from(MonthlyBudget.class);
		Predicate userPredicate = getUserPredicate(cb, budget);
		Predicate monthEquality = cb.equal(budget.get("month"), bDate.getMonth());
		Predicate yearEquality = cb.equal(budget.get("year"), bDate.getYear());
		q.select(budget).where(cb.and(userPredicate, monthEquality, yearEquality));
		List<MonthlyBudget> resultList = createTypedQuery(q).getResultList();
		return resultList;
	}

	public MonthlyBudget getPreceedingBudget(BudgetDate bDate) {
		int numMonthsToGoBack = 6;
		for (int i = 1; i <= numMonthsToGoBack; i++) {
			bDate = getPreviousBudgetDate(bDate); //keep moving backwards for max 6 months
			List<MonthlyBudget> monthlyBudgets = findByBudgetDate(bDate);
			if(!monthlyBudgets.isEmpty()) {
				return monthlyBudgets.get(0);
			}
		}
		return null;
	}
	
	private BudgetDate getPreviousBudgetDate(BudgetDate bDate) {
		if(bDate.getMonth().equals(Month.JANUARY)) {
			System.out.println("It Is Jan");
			BudgetDate budgetDate = new BudgetDate(Month.DECEMBER, getPreviousYear(bDate.getYear()));
			System.out.println("WHo come after: " + budgetDate.getYear() + " " + budgetDate.getMonth());
			return budgetDate;
		}
		return new BudgetDate(getPreviosMonth(bDate.getMonth()), bDate.getYear());
	}
	
	private static Month getPreviosMonth(Month month) {
		return Month.values()[month.ordinal() - 1];
	}

	private int getPreviousYear(int currentYear) {
		return --currentYear;
	}
	
}
