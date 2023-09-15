package com.bolbon.entities;

import java.util.List;
import java.util.ArrayList;

public class Federation {
	private String name;
	private List<Division> divisions;

	public Federation(String name) {
		this.name = name;
		this.divisions = new ArrayList<>();
		// Agregar las 5 divisiones de ligas
		for (int i = 1; i <= 5; i++) {
			divisions.add(new Division("Liga " + i));
		}
	}
	
	public List<Team> getAllTeams(){
		List<Team> teams = new ArrayList<>();
		for (int i=0;i<divisions.size();i++) {
			teams.addAll(divisions.get(i).g
		}
		return 
	}
}
