package com.cBudget.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cBudget.entity.MonthlyBudget;

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
		q.select(budget.get("year")).distinct(true).orderBy(cb.desc(budget.get("year")));
		return createTypedQuery(q).getResultList();
	}
	
	public List<MonthlyBudget> getBudgetsByYear(Integer year) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<MonthlyBudget> query = cb.createQuery(MonthlyBudget.class);
		Root<MonthlyBudget> budget = query.from(MonthlyBudget.class);
		query.select(budget).where(cb.equal(budget.get("year"), year));
		return createTypedQuery(query).getResultList();
	}
	
	//TO DELETE
	@SuppressWarnings("unchecked")
	public List<List<MonthlyBudget>> getMonthlyBudgetsGroupedByYearsDescending() {
		String yearsQueryString = "SELECT distinct(e.year) FROM MonthlyBudget e ORDER BY e.year DESC";
		Query yearsQuery = getEntityManager().createQuery(yearsQueryString, Integer.class);
		List<Integer> years = yearsQuery.getResultList();
		
		List<List<MonthlyBudget>> monthlyBudgetsGroupedByYears = new ArrayList<>();
		for (Integer year : years) {
			String budgetQueryString = "SELECT distinct(e) FROM MonthlyBudget e WHERE e.year = :year ORDER BY e.month ASC";
			Query budgetQuery = getEntityManager().createQuery(budgetQueryString, MonthlyBudget.class);
			budgetQuery.setParameter("year", year);
			List<MonthlyBudget> budgets= budgetQuery.getResultList();
			monthlyBudgetsGroupedByYears.add(budgets);		
		}
		return monthlyBudgetsGroupedByYears;
	}

//	@Override
//	public List<Set<MonthlyBudget>> getMonthlyBudgetGroupedByYearsDescending() {
//		List<Set<MonthlyBudget>> monthlyBudgetsGroupedByYears = new ArrayList<>();
//		Query query = getEntityManager().createQuery("SELECT mb FROM MonthlyBudget e ORDER BY mb.year DESC");
//		// would include a WHERE user = user at a later stage
//		MonthlyBudget[] monthlyBudgets = (MonthlyBudget[]) query.getResultList().toArray();
//		if (monthlyBudgets.length != 0) {
//			SortedSet<MonthlyBudget> monthlyBudgetsPerYear = new TreeSet<>(); // pass in my own coparator
//			monthlyBudgetsPerYear.add(monthlyBudgets[0]);
//			for (int i = 1; i < monthlyBudgets.length; i++) {
//				if (isCurrentBudgetWithinTheSameYearAsPrevBudget(monthlyBudgets, i)) {
//					monthlyBudgetsPerYear.add(monthlyBudgets[i]);
//				} else {
//					monthlyBudgetsGroupedByYears.add(monthlyBudgetsPerYear);
//					monthlyBudgetsPerYear = newMontlyBudgetPerYear(monthlyBudgets[i]);
//				}
//			}
//			monthlyBudgetsGroupedByYears.add(monthlyBudgetsPerYear);
//		}
//		return monthlyBudgetsGroupedByYears;
//	}
//
//	private SortedSet<MonthlyBudget> newMontlyBudgetPerYear(MonthlyBudget initialMontlyBudget) {
//		SortedSet<MonthlyBudget> monthlyBudgetsPerYear = new TreeSet<>(); // pass in comparator
//		monthlyBudgetsPerYear.add(initialMontlyBudget);
//		return monthlyBudgetsPerYear;
//	}
//
//	private boolean isCurrentBudgetWithinTheSameYearAsPrevBudget(MonthlyBudget[] monthlyBudgets,
//			int currentBudgetIdex) {
//		return monthlyBudgets[currentBudgetIdex - 1] == monthlyBudgets[currentBudgetIdex];
//	}


}
