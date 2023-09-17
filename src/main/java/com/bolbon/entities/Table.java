package com.bolbon.entities;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Table {
	private int idTable;
    private List<String> teams;
    private List<Integer> played;
    private List<Integer> wins;
    private List<Integer> draws;
    private List<Integer> loses;
    private List<Integer> goalsFor;
    private List<Integer> goalsAgainst;
    private List<Integer> difference;
    private List<Integer> points;

    public Table(List<Team> team) {
        this.teams = getTeamsName(team);
        this.played = new ArrayList<>(teams.size());
        this.wins = new ArrayList<>(teams.size());
        this.draws = new ArrayList<>(teams.size());
        this.loses = new ArrayList<>(teams.size());
        this.goalsFor = new ArrayList<>(teams.size());
        this.goalsAgainst = new ArrayList<>(teams.size());
        this.difference = new ArrayList<>(teams.size());
        this.points = new ArrayList<>(teams.size());

        for (int i = 0; i < teams.size(); i++) {
            played.add(0);
            wins.add(0);
            draws.add(0);
            loses.add(0);
            goalsFor.add(0);
            goalsAgainst.add(0);
            difference.add(0);
            points.add(0);
        }
    }

    private List<String> getTeamsName(List<Team> team) {
    	List<String> list = new ArrayList<>();
    	for(int i = 0; i<team.size();i++) {
    		list.add(team.get(i).getName());
    	}
    	return list;
	}

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}

	public List<Integer> getPlayed() {
		return played;
	}

	public void setPlayed(List<Integer> played) {
		this.played = played;
	}

	public List<Integer> getWins() {
		return wins;
	}

	public void setWins(List<Integer> wins) {
		this.wins = wins;
	}

	public List<Integer> getDraws() {
		return draws;
	}

	public void setDraws(List<Integer> draws) {
		this.draws = draws;
	}

	public List<Integer> getLoses() {
		return loses;
	}

	public void setLoses(List<Integer> loses) {
		this.loses = loses;
	}

	public List<Integer> getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(List<Integer> goalsFor) {
		this.goalsFor = goalsFor;
	}

	public List<Integer> getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(List<Integer> goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public List<Integer> getDifference() {
		return difference;
	}

	public void setDifference(List<Integer> difference) {
		this.difference = difference;
	}

	public List<Integer> getPoints() {
		return points;
	}

	public void setPoints(List<Integer> points) {
		this.points = points;
	}

	public void updateTable(Team team, int goalsFor, int goalsAgainst, int points) {
        int teamIndex = getTeamIndex(team);
    	int playedValue = played.get(teamIndex) + 1;
        int goalsForValue = this.goalsFor.get(teamIndex) + goalsFor;
        int goalsAgainstValue = this.goalsAgainst.get(teamIndex) + goalsAgainst;
        int differenceValue = goalsForValue - goalsAgainstValue;
        int pointsValue = this.points.get(teamIndex) + points;

        played.set(teamIndex, playedValue);
        this.goalsFor.set(teamIndex, goalsForValue);
        this.goalsAgainst.set(teamIndex, goalsAgainstValue);
        difference.set(teamIndex, differenceValue);
        this.points.set(teamIndex, pointsValue);

        if (points == 3) {
            wins.set(teamIndex, wins.get(teamIndex) + 1);
        } else if (points == 1) {
            draws.set(teamIndex, draws.get(teamIndex) + 1);
        } else {
            loses.set(teamIndex, loses.get(teamIndex) + 1);
        }
    }
    
    public int getTeamIndex(Team team) {
    	int index = -1;
    	int i=0;
    	while(index == -1 && i< teams.size()) {
    		if (teams.get(i) == team.getName()) {
    			index=i;
    		}
    		i++;
    	}
    	return index;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        // Crear una lista de objetos que contengan los datos de cada equipo junto con sus puntos
        List<TeamStats> teamStatsList = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            TeamStats teamStats = new TeamStats(
                teams.get(i),
                played.get(i),
                wins.get(i),
                draws.get(i),
                loses.get(i),
                goalsFor.get(i),
                goalsAgainst.get(i),
                difference.get(i),
                points.get(i)
            );
            teamStatsList.add(teamStats);
        }

        // Ordenar la lista de objetos por puntos (de mayor a menor)
        Collections.sort(teamStatsList, (t1, t2) -> t2.getPoints() - t1.getPoints());

        // Agregar encabezados de columnas
        sb.append(String.format("%-20s %-6s %-6s %-6s %-6s %-10s %-10s %-10s %-6s%n", 
                "Team", "P", "W", "D", "L", "GF", "GA", "Dif", "Pts"));
        
        // Iterar sobre los equipos ordenados y agregar sus estadísticas
        for (TeamStats teamStats : teamStatsList) {
            sb.append(String.format("%-20s %-6d %-6d %-6d %-6d %-10d %-10d %-10d %-6d%n",
                    teamStats.getTeam(), teamStats.getPlayed(), teamStats.getWins(), teamStats.getDraws(),
                    teamStats.getLoses(), teamStats.getGoalsFor(), teamStats.getGoalsAgainst(),
                    teamStats.getDifference(), teamStats.getPoints()));
        }
        
        return sb.toString();
    }

    // Clase auxiliar para contener los datos de cada equipo junto con sus puntos
    private static class TeamStats {
        private String team;
        private int played;
        private int wins;
        private int draws;
        private int loses;
        private int goalsFor;
        private int goalsAgainst;
        private int difference;
        private int points;

        public TeamStats(String team, int played, int wins, int draws, int loses,
                         int goalsFor, int goalsAgainst, int difference, int points) {
            this.team = team;
            this.played = played;
            this.wins = wins;
            this.draws = draws;
            this.loses = loses;
            this.goalsFor = goalsFor;
            this.goalsAgainst = goalsAgainst;
            this.difference = difference;
            this.points = points;
        }

        public String getTeam() {
            return team;
        }

        public int getPlayed() {
            return played;
        }

        public int getWins() {
            return wins;
        }

        public int getDraws() {
            return draws;
        }

        public int getLoses() {
            return loses;
        }

        public int getGoalsFor() {
            return goalsFor;
        }

        public int getGoalsAgainst() {
            return goalsAgainst;
        }

        public int getDifference() {
            return difference;
        }

        public int getPoints() {
            return points;
        }
    }
    
}



