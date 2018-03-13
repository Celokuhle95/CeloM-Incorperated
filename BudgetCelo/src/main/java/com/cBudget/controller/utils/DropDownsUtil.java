package com.cBudget.controller.utils;

import java.io.Serializable;
import java.util.Map;

import javax.faces.model.SelectItem;

public class DropDownsUtil  implements Serializable {

	private static final long serialVersionUID = 1L;

	public static SelectItem[] getSelectItems(Map<Integer, Object> items) {
		SelectItem[] selectItems = new SelectItem[items.size()];
		int i = 0;
		for (Integer key : items.keySet()) {
			Object value = items.get(key);
			selectItems[i++] = new SelectItem(value, value.toString());
		}
		return selectItems;
	}

	public static String cleanUp(String enumName) {
		if (containsMultipleWords(enumName)) {
			return handleMultipleWords(enumName);
		}
		return getInCapAndLowercase(enumName);
	}

	public static String getInCapAndLowercase(String uppercase) {
		return capitaliseFirstLetter(uppercase.toLowerCase());
	}

	public static String capitaliseFirstLetter(String lowerCase) {
		char capatilisedFirstLetter = Character.toUpperCase(lowerCase.charAt(0));
		String restOfTheWord = lowerCase.substring(1, lowerCase.length());
		return capatilisedFirstLetter + restOfTheWord;
	}

	public static boolean containsMultipleWords(String line) {
		return line.contains("_");
	}

	public static String handleMultipleWords(String line) {
		String[] words = line.split("_");
		String monthInCaps = "";
		for (String word : words) {
			monthInCaps += getInCapAndLowercase(word + " ");
		}
		return monthInCaps;
	}

}
