package com.cBudget.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.cBudget.entity.Theme;

@Named
@ApplicationScoped
public class HeaderController {

	public String currentDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		LocalDate today = LocalDate.now();
		return today.format(formatter);
	}
	
	public List<Theme> getThemes() {
		List<Theme> themes = new ArrayList<>();
		themes.add(new Theme("South-Street", "south-street"));
		themes.add(new Theme("Black-Tie", "black-tie"));
		themes.add(new Theme("Trontastic", "trontastic"));
		return themes;
	}

}
