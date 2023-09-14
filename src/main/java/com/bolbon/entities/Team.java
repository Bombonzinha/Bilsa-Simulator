package com.bolbon.entities;

import java.util.Set;
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
		setRating(calcularRating());
	}
	public Team(int idTeam, String name) {
		super();
		this.idTeam = idTeam;
		this.name = name;
		setRating(calcularRating());
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
	
	public int calcularRating() {
		return 10;
	}
	@Override
	public String toString() {
		return "Team [idTeam=" + idTeam + ", name=" + name + ", players=" + players + ", rating=" + rating + "]";
	}
	
	
}
