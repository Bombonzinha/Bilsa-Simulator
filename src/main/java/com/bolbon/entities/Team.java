package com.bolbon.entities;

import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "team")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTeam;
	@Column(name = "name")
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	private Set<Player> players = new HashSet<>();
	@Column(name = "rating")
	private int rating;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "federation_id")
	private Federation federation;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "division_id")
	private Division division;

	@OneToMany(mappedBy = "home", fetch = FetchType.EAGER)
	private List<MatchResult> homeMatches;

	@OneToMany(mappedBy = "away", fetch = FetchType.EAGER)
	private List<MatchResult> awayMatches;

	@Column(name = "matches_played")
	private int matchesPlayed;

	@Column(name = "matches_won")
	private int matchesWon;

	@Column(name = "matches_drawn")
	private int matchesDrawn;

	@Column(name = "matches_lost")
	private int matchesLost;

	@Column(name = "goals_for")
	private int goalsFor;

	@Column(name = "goals_against")
	private int goalsAgainst;

	public Team() {
		super();
	}

	public Team(int idTeam, String name, Set<Player> players, Federation federation, Division division) {
		super();
		this.idTeam = idTeam;
		this.name = name;
		this.players = players;
		this.federation = federation;
		this.division = division;
		this.homeMatches = new ArrayList<>();
		this.awayMatches = new ArrayList<>();
		this.matchesPlayed = 0;
		this.matchesWon = 0;
		this.matchesDrawn = 0;
		this.matchesLost = 0;
		this.goalsFor = 0;
		this.goalsAgainst = 0;

	}

	public Team(int idTeam, String name, Federation federation, Division division) {
		super();
		this.idTeam = idTeam;
		this.name = name;
		this.players = new HashSet<>();
		this.federation = federation;
		this.division = division;
		this.homeMatches = new ArrayList<>();
		this.awayMatches = new ArrayList<>();
		this.matchesPlayed = 0;
		this.matchesWon = 0;
		this.matchesDrawn = 0;
		this.matchesLost = 0;
		this.goalsFor = 0;
		this.goalsAgainst = 0;
	}

	public Team(String name, Federation federation, Division division) {
		super();
		this.name = name;
		this.players = new HashSet<>();
		this.federation = federation;
		this.division = division;
		this.homeMatches = new ArrayList<>();
		this.awayMatches = new ArrayList<>();
		this.matchesPlayed = 0;
		this.matchesWon = 0;
		this.matchesDrawn = 0;
		this.matchesLost = 0;
		this.goalsFor = 0;
		this.goalsAgainst = 0;
	}

	public Team(String name) {
		super();
		this.name = name;
		this.players = new HashSet<>();
		this.federation = null;
		this.division = null;
		this.homeMatches = new ArrayList<>();
		this.awayMatches = new ArrayList<>();
		this.matchesPlayed = 0;
		this.matchesWon = 0;
		this.matchesDrawn = 0;
		this.matchesLost = 0;
		this.goalsFor = 0;
		this.goalsAgainst = 0;
	}

	public int getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Federation getFederation() {
		return federation;
	}

	public void setFederation(Federation federation) {
		this.federation = federation;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public List<MatchResult> getHomeMatches() {
		return homeMatches;
	}

	public void setHomeMatches(List<MatchResult> homeMatches) {
		this.homeMatches = homeMatches;
	}

	public List<MatchResult> getAwayMatches() {
		return awayMatches;
	}

	public void setAwayMatches(List<MatchResult> awayMatches) {
		this.awayMatches = awayMatches;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public int getMatchesWon() {
		return matchesWon;
	}

	public void setMatchesWon(int matchesWon) {
		this.matchesWon = matchesWon;
	}

	public int getMatchesDrawn() {
		return matchesDrawn;
	}

	public void setMatchesDrawn(int matchesDrawn) {
		this.matchesDrawn = matchesDrawn;
	}

	public int getMatchesLost() {
		return matchesLost;
	}

	public void setMatchesLost(int matchesLost) {
		this.matchesLost = matchesLost;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(int goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	// para caluclar el rating de todos lso equipos, hay que hacer una federacion
	// con lista de equipos
	public void calcularRating() {
		double rating11 = 0;
		double ratingLeft = 0;
		double ratingFinal = 0;
		List<Player> playerList = new ArrayList<>(players);
		// Ordena la lista en orden descendente según el rating
		playerList.sort((player1, player2) -> Integer.compare(player2.getRating(), player1.getRating()));

		for (int i = 0; i < playerList.size(); i++) {
			Player player = playerList.get(i);
			if (i < 11) {
				rating11 += player.getRating();
			} else {
				ratingLeft += player.getRating();
			}
		}
		rating11 /= 12; // max=90,75
		ratingLeft /= (players.size() - 11) * 10; // max=9,9
		ratingFinal = rating11 + ratingLeft; // max=100,74
		ratingFinal *= 100; // esto lo aumenta a por ejemplo, 9825 que es practicamente imposible
		ratingFinal = Math.max(1000, ratingFinal); // el mínimo es 1000
		rating = (int) Math.min(10000, ratingFinal);// se redondea a 100 si es mayor
	}

	public void wonMatch(int goalsFor, int goalsAgainst) {
		matchesPlayed++;
		matchesWon++;
		this.goalsFor += goalsFor;
		this.goalsAgainst += goalsAgainst;
	}

	public void drawnMatch(int goalsFor, int goalsAgainst) {
		matchesPlayed++;
		matchesDrawn++;
		this.goalsFor += goalsFor;
		this.goalsAgainst += goalsAgainst;
	}

	public void lostMatch(int goalsFor, int goalsAgainst) {
		matchesPlayed++;
		matchesLost++;
		this.goalsFor += goalsFor;
		this.goalsAgainst += goalsAgainst;
	}

	public void playMatchHost(Team opponent, int goalsFor, int goalsAgainst) {
		MatchResult matchResult = new MatchResult(this, opponent, goalsFor, goalsAgainst);
		matchResult.calculateResult();
	}
	//funcion final para obtener el 11 titular y los 12 suplentes
	public List<List<Player>> getMatchPlayers() {
		List<Player> playerList = new ArrayList<>(players); //obtengo la lista de jugadores del equipo
		ordenarPorRating(playerList);	//la ordeno por rating para obtener a los mejores jugadores
		List<Player> titulares = obtainFirstEleven(playerList); //obtengo el once titular
		playerList.removeAll(titulares); //filtro los titulares para buscar los suplentes
		List<Player> substitutes = buscarJugadoresSuplentes(playerList); //obtengo los suplentes
		List<List<Player>> result = new ArrayList<>();
	    result.add(titulares);
	    result.add(substitutes);
		return result;
	}
	//funcion para obtener el once titular
	private List<Player> obtainFirstEleven(List<Player> playerList) {
		List<Player> firstEleven = new ArrayList<>();
		firstEleven.add(buscarArquero(playerList));
		firstEleven.addAll(buscarJugadoresTitulares(playerList, 1, 4));
		firstEleven.addAll(buscarJugadoresTitulares(playerList, 2, 3));
		firstEleven.addAll(buscarJugadoresTitulares(playerList, 3, 3));
		return firstEleven;
	}
	//funcion para buscar al arquero
	private Player buscarArquero(List<Player> playerList) {
		List<Player> players = buscarJugadores(playerList, 0); // Busco a los arqueros (pos 0)
		ordenarPorRating(players); // ordeno la LISTA DE ARQUEROS por rating
		return players.get(0); // retorno el arquero con más rating
	}
	// esto busca al mejor/es jugador/es por posición para el equipo titular
	private List<Player> buscarJugadoresTitulares(List<Player> playerList, int posicion, int cantidad) {
		List<Player> playersPosition = buscarJugadores(playerList, posicion);
		ordenarPorRating(playersPosition);
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < cantidad; i++) {
			players.add(playersPosition.get(i));
		}
		return players;
	}
	//funcion para obtener a los suplentes
	private List<Player> buscarJugadoresSuplentes(List<Player> playerList) {
		ordenarPorPosicion(playerList); //ordeno la lista de suplentes por posicion
		List<Player> substitutes = new ArrayList<>(); //obtengo los suplentes de la posicion p
		for (int p = 0; p< 4;p++) { //para cada posicion
			List<Player> players = buscarJugadores(playerList, p); //obtengo los suplentes de la posicion p
			if (p==0) { //2 arqueros
				for (int i = 0; i < 2 && i<players.size(); i++) {
					substitutes.add(players.get(i));
				}
			} else if(p==1) { //hasta 4 defensores
				for (int i = 0; i < 4 && i<players.size(); i++) {
					substitutes.add(players.get(i));
				}
			} else { //hasta 3 medios/delanteros
				for (int i = 0; i < 3 && i<players.size(); i++) {
					substitutes.add(players.get(i));
				}
			}
		}
		return substitutes;
	}
	// esto busca jugadores por posición
	public List<Player> buscarJugadores(List<Player> playerList, int posicion) {
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < playerList.size(); i++) {
			Player aux = playerList.get(i);
			if (aux.getPosition() == posicion) {
				players.add(aux);
			}
		}
		return players;
	}

	public void ordenarPorRating(List<Player> playerList) {
		playerList.sort((player1, player2) -> Integer.compare(player2.getRating(), player1.getRating()));
	}

	public void ordenarPorPosicion(List<Player> playerList) {
		playerList.sort((player1, player2) -> Integer.compare(player1.getPosition(), player2.getPosition()));
	}

	@Override
	public String toString() {
		return "Team [idTeam=" + idTeam + ", name=" + name + ", players=" + players + ", rating=" + rating + ", league="
				+ division.getName() + "]";
	}

}
