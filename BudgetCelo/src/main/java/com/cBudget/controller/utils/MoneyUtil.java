package com.cBudget.controller.utils;

import java.math.BigDecimal;
import java.math.MathContext;

public class MoneyUtil {
	
	public final static BigDecimal HUNDRED = new BigDecimal("100");

	public static String thousandSeperated(BigDecimal money) {
		return String.format("%,.2f", money);
	}
	
	public static String twoDecimals(BigDecimal money) {
		return String.format("%.2f", money);
	}
	
	public static BigDecimal format(BigDecimal money) {
		return new BigDecimal(twoDecimals(money));
	}
	
	public static String noDecimals(BigDecimal money) {
		return String.format("%,.0f", money);
	}
	
	public static BigDecimal divide(BigDecimal toDivide, BigDecimal divisor) {
		BigDecimal answer = toDivide.divide(divisor,MathContext.DECIMAL128);
		return answer;
	}
	
	public static BigDecimal percentage(BigDecimal of, BigDecimal against) {
		BigDecimal divide = divide(of, against);
		return divide.multiply(HUNDRED);
	}
	
	
}
