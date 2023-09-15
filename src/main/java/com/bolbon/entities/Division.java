package com.bolbon.entities;

import java.util.List;
import java.util.ArrayList;

public class Division {
	private int idDivision;
	private String name;
    private List<Team> teams;
    
    public Division(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
    }

	public int getIdDivision() {
		return idDivision;
	}

	public void setIdDivision(int idDivision) {
		this.idDivision = idDivision;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
    
    
}
