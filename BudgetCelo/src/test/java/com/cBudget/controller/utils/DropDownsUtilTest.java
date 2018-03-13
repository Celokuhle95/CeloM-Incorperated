package com.cBudget.controller.utils;

import junit.framework.TestCase;

public class DropDownsUtilTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
	}
	
	public void testCapitaliseFirstLetterWorksForALowerCaseFirstLetter() {
		assertEquals("Lowercase", DropDownsUtil.capitaliseFirstLetter("Lowercase"));
	}
	
	public void testCapitaliseFirstLetterWorksForAnUpperCaseFirstLetter() {
		assertEquals("Lowercase", DropDownsUtil.capitaliseFirstLetter("lowercase"));
	}
	
	public void testGetInCapAndLowercaseWorksForAllUppercase() {
		assertEquals("Lowercase", DropDownsUtil.getInCapAndLowercase("LOWERCASE"));
	}
	
	public void testGetInCapAndLowercaseWorksForUperAndLowercases() {
		assertEquals("Lowercase", DropDownsUtil.getInCapAndLowercase("LoweRCASE"));
	}
	
	public void testContainsMultipleWordsShouldBeFalse() {
		assertEquals(false, DropDownsUtil.containsMultipleWords("ONE"));
		assertEquals(false, DropDownsUtil.containsMultipleWords("MILLIONS"));
	}
	
	public void testContainsMultipleWordsShouldBeTrue() {
		assertEquals(true, DropDownsUtil.containsMultipleWords("ONE_TWO"));
		assertEquals(true, DropDownsUtil.containsMultipleWords("MILLIONS_BILLIONS_TRILLIONS"));
	}
	
	
}
