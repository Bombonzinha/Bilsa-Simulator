package com.bolbon.entities;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter 
@Table(name="team")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTeam;
	@Column(name="name")
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy="team")
	private Set<Player> players = new HashSet<>();
	@Column(name="rating")
	private int rating;
	
	public Team() {
		super();
	}
	public Team(int idTeam, String name, Set<Player> players) {
		super();
		this.idTeam = idTeam;
		this.name = name;
		this.players = players;
	}
	public Team(int idTeam, String name) {
		super();
		this.idTeam = idTeam;
		this.name = name;
	}
	
	public Team(String name) {
		super();
		this.name = name;
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
	//para caluclar el rating de todos lso equipos, hay que hacer una federacion con lista de equipos
	public void calcularRating() {
		double rating11 = 0;
		double ratingLeft = 0;
		double ratingFinal=0;
		List<Player> playerList = new ArrayList<>(players);
		// Ordena la lista en orden descendente según el rating
	    playerList.sort((player1, player2) -> Integer.compare(player2.getRating(), player1.getRating()));
	    
	    for (int i = 0; i < playerList.size(); i++) {
	    	Player player = playerList.get(i);
	    	if (i<11) {
	    		rating11 += player.getRating(); 
	    	} else {
	    		ratingLeft += player.getRating(); 
	    	}
		}
	    rating11 /= 12;	//max=90,75
	    ratingLeft /= (players.size()-11)*10; //max=9,9
	    ratingFinal=rating11+ratingLeft; //max=100,74
	    ratingFinal*=100; //esto lo aumenta a por ejemplo, 9825 que es practicamente imposible
	    ratingFinal= Math.max(1000, ratingFinal);	//el mínimo es 1000
	    rating = (int) Math.min(10000, ratingFinal);//se redondea a 100 si es mayor
	}
	@Override
	public String toString() {
		return "Team [idTeam=" + idTeam + ", name=" + name + ", players=" + players + ", rating=" + rating + "]";
	}
	
	
}
