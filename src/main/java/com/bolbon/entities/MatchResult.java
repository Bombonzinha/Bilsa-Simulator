package com.bolbon.entities;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class MatchResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMatchResult;
	
	@ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team home;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team away;
    
    @Column(name = "goals_home")
    private int goalsHome;

    @Column(name = "goals_away")
    private int goalsAway;

	public MatchResult() {
		super();
	}

	public MatchResult(Team home, Team away, int goalsHome, int goalsAway) {
		this.home = home;
		this.away = away;
		this.goalsHome = goalsHome;
		this.goalsAway = goalsAway;
		calculateResult();
	}
	public MatchResult(Team home, Team away) {
		this.home = home;
		this.away = away;
		this.goalsHome = -1;
		this.goalsAway = -1;
	}

	public int getIdMatchResult() {
		return idMatchResult;
	}

	public void setIdMatchResult(int idMatchResult) {
		this.idMatchResult = idMatchResult;
	}

	public Team getHome() {
		return home;
	}

	public Team getAway() {
		return away;
	}

	public int getGoalsHome() {
		return goalsHome;
	}

	public int getGoalsAway() {
		return goalsAway;
	}

	public void setHome(Team home) {
		this.home = home;
	}

	public void setAway(Team away) {
		this.away = away;
	}

	public void setGoalsHome(int goalsHome) {
		this.goalsHome = goalsHome;
	}

	public void setGoalsAway(int goalsAway) {
		this.goalsAway = goalsAway;
	}
	//esto es para cuando el partido no se inicializó con goles
	public void simulateMatch(int goalsHome, int goalsAway) {
		this.goalsHome=goalsHome;
		this.goalsAway=goalsAway;
		calculateResult();
	}

	public void calculateResult() {
		if (goalsHome > goalsAway) {
			home.wonMatch(goalsHome, goalsAway);
			away.lostMatch(goalsAway, goalsHome);
		} else if (goalsHome < goalsAway) {
			home.lostMatch(goalsHome, goalsAway);
			away.wonMatch(goalsAway, goalsHome);
		} else {
			home.drawnMatch(goalsHome, goalsAway);
			away.drawnMatch(goalsAway, goalsHome);
		}
		home.getHomeMatches().add(this);
		away.getAwayMatches().add(this);
	}

	@Override
	public String toString() {
		return home.getName() + " " + goalsHome + " - " + goalsAway + " " + away.getName();
	}

}
