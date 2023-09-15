package com.bolbon.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.HashMap;

@Entity
@Getter @Setter 
@Table(name="player")
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlayer;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@Column(name="position")
	private int position; //1-11
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="team")
	private Team team;
	@OneToOne
	@JoinColumn(name = "abilities")
    private Abilities abilities;//habilidades
	
	@Column(name="rating")
	private int rating;
	
	public Player() {
		
	}
	
	public Player(int idPlayer, String name, int age, int position, Team team, Abilities abilities) {
		super();
		this.idPlayer = idPlayer;
		this.name = name;
		this.age = age;
		this.position = position;
		this.team = team;
		this.abilities = abilities;
	}


	public Player(String name, int age, int position) {
		super();
		this.name = name;
		this.age = age;
		this.position = position;
	}

	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	protected int getIdPlayer() {
		return idPlayer;
	}
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	public Abilities getAbilities() {
		return abilities;
	}

	public void setAbilities(Abilities abilities) {
		this.abilities = abilities;
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}


	/*aca hacer un calculador de ratings segun posicion, basandose en sus habilidades*/
	public void calcularRating() {
		rating= abilities.ratingFinal(position);
	}

	@Override
	public String toString() {
		return "Player [idPlayer=" + idPlayer + ", name=" + name + ", age=" + age + ", position=" + position + ", rating=" + rating + "]";
	}
	
	
}
