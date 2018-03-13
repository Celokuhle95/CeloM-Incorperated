package com.cBudget.entity.enums.comparators;

import java.io.Serializable;
import java.util.Comparator;

public class EnumComparator implements Comparator<Integer>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final EnumComparator COMPARATOR = new EnumComparator();
	
	public static EnumComparator get() {
		return COMPARATOR;
	}

	private EnumComparator() {
	}
	
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1.compareTo(o2);
	}

}
