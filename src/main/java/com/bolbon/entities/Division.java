package com.bolbon.entities;

import java.util.List;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Collections;

@Entity
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDivision;
   
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "federation_id")
    private Federation federation;

	@OneToMany (mappedBy = "division")
    private List<Team> teams;
    
    public Division() {
		super();
	}

	public Division(String name, Federation federation) {
		super();
		this.name = name;
		this.federation = federation;
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

	public Federation getFederation() {
		return federation;
	}

	public void setFederation(Federation federation) {
		this.federation = federation;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
    
	public void simulateLeague() {
		List<Team> newList = new ArrayList<>(teams);
		ordenarAleatoriamente(newList); //Se sortea el orden
		
		
	}
	
	public static void ordenarAleatoriamente(List<?> lista) {
        long semilla = System.nanoTime();
        Collections.shuffle(lista, new Random(semilla));
    }
    
}
