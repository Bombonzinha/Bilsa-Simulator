package com.bolbon.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

@Entity
public class Federation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFederation;
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "federation")
	private List<Division> divisions;

	@OneToMany(mappedBy = "federation")
	private List<Team> teams;

	public Federation() {
		super();
	}

	public Federation(String name) {
		this.name = name;
		this.divisions = new ArrayList<>();
		this.teams = new ArrayList<>();
	}

	public int getIdFederation() {
		return idFederation;
	}

	public void setIdFederation(int idFederation) {
		this.idFederation = idFederation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Division> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<Division> divisions) {
		this.divisions = divisions;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
