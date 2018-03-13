package com.cBudget.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.cBudget.entity.Theme;

@Named
@ApplicationScoped
public class HeaderController {

	public String currentMonth() {
		return "February";
	}
	
	public Integer currentYear() {
		return 2018;
	}
	
	public List<Theme> getThemes() {
		List<Theme> themes = new ArrayList<>();
		themes.add(new Theme("South-Street", "south-street"));
		themes.add(new Theme("Black-Tie", "black-tie"));
		themes.add(new Theme("Trontastic", "trontastic"));
		return themes;
	}

}
