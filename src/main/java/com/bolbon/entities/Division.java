package com.bolbon.entities;

import java.util.List;
import java.util.Random;

import com.bolbon.utils.Tools;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

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

	@OneToMany(mappedBy = "division")
	private List<Team> teams;
	
	@Transient
	private Table table;

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

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void roundRobinSimulator() {
		Tools.ordenarAleatoriamente(teams); // Se sortea el orden
		List<List<MatchResult>> calendarFirstLeg = roundRobinGenerator(teams);
		Collections.reverse(teams);
		List<List<MatchResult>> calendarSecondLeg = roundRobinGenerator(teams);
		List<List<MatchResult>> calendar = new ArrayList<>();
		//ESto esta mal no intercala local/visitante bien
		int maxRounds = Math.max(calendarFirstLeg.size(), calendarSecondLeg.size());
		for (int i = 0; i < maxRounds; i+=2) {
		    if (i < calendarFirstLeg.size()) {
		        calendar.add(calendarFirstLeg.get(i)); // agrego los impares
		    }
		    if (i + 1 < calendarSecondLeg.size()) {
		        calendar.add(calendarSecondLeg.get(i+1)); //agrego los pares
		    }
		}
		for (int i = 0; i < maxRounds; i+=2) {
		    if (i < calendarSecondLeg.size()) {
		        calendar.add(calendarSecondLeg.get(i)); //agrego los impares 2da vuelta
		    }
		    if (i + 1 < calendarFirstLeg.size()) {
		        calendar.add(calendarFirstLeg.get(i+1)); //agrego los pares 2da vuelta
		    }
		}// pero si funciona 
		table = new Table(teams);
		roundRobinLoader(calendar);
	}

	public List<List<MatchResult>> roundRobinGenerator(List<Team> teams) {
		List<List<MatchResult>> calendar = new ArrayList<>();
		int numTeams = teams.size();
		int numRounds = numTeams - 1;
		for (int round = 0; round < numRounds; round++) {
			List<MatchResult> roundMatches = new ArrayList<>();
			for (int i = 0; i < numTeams / 2; i++) {
				int team1Index = i;
				int team2Index = numTeams - 1 - i;

				Team team1 = teams.get(team1Index);
				Team team2 = teams.get(team2Index);
				MatchResult match = new MatchResult(team1, team2);
				roundMatches.add(match);
			}
			calendar.add(roundMatches);

			// Rotar los equipos para la próxima ronda
			Team lastTeam = teams.remove(teams.size() - 1);
			teams.add(1, lastTeam);
		}
		return calendar;
	}

	public void roundRobinLoader(List<List<MatchResult>> calendar) {
		for (int i = 0; i < calendar.size(); i++) {	//Para cada fecha
//			System.out.println("Fecha "+(i+1));
			List<MatchResult> fecha = calendar.get(i);
			for (int j = 0; j < fecha.size(); j++) { //Para cada partido de la fecha
				int[] goles = new int[2];
				MatchResult partido = fecha.get(j);
				goles = Match.simulationMatch(partido); //esto modifica el partido con los goles
				table.updateTable(partido.getHome(), goles[0], goles[1], Match.puntosPartido(goles[0], goles[1]));
				table.updateTable(partido.getAway(), goles[1], goles[0], Match.puntosPartido(goles[1], goles[0]));
//				System.out.println(partido);
			}
//			System.out.println();
//			System.out.println(table);
//			System.out.println();
		}
		
	}
	
}
