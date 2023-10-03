package com.bolbon.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "abilities")
public class Abilities {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAbilities;
	@OneToOne
	@JoinColumn(name = "player")
	private Player player;

	@Column(name = "attack")
	private int attack;

	@Column(name = "defence")
	private int defence;
	
	@Column(name = "strength")
	private int strength;

	@Column(name = "stamina")
	private int stamina;
	
	@Column(name = "speed")
	private int speed;
	
	@Column(name = "agility")
	private int agility;
	
	@Column(name = "dribble")
	private int dribble;

	@Column(name = "shortPass")
	private int shortPass;
	
	@Column(name = "longPass")
	private int longPass;

	@Column(name = "shot")
	private int shot;
	
	@Column(name = "jump")
	private int jump;

	@Column(name = "technique")
	private int technique;

	public Abilities() {
		super();
	}

	public Abilities(int idAbility, Player player, int attack, int defence, int strength, int stamina, int speed,
			int agility, int dribble, int shortPass, int longPass, int shot, int jump, int technique) {
		super();
		this.idAbilities = idAbility;
		this.player = player;
		this.attack = attack;
		this.defence = defence;
		this.strength = strength;
		this.stamina = stamina;
		this.speed = speed;
		this.agility = agility;
		this.dribble = dribble;
		this.shortPass = shortPass;
		this.longPass = longPass;
		this.shot = shot;
		this.jump = jump;
		this.technique = technique;
	}

	public Abilities(int attack, int dribble, int technique, int shot, int shortPass, int longPass, int speed,
			int agility, int jump, int stamina, int strength, int defence) {
		super();
		this.attack = attack;
		this.dribble = dribble;
		this.technique = technique;
		this.shot = shot;
		this.shortPass = shortPass;
		this.longPass = longPass;
		this.speed = speed;
		this.agility = agility;
		this.jump = jump;
		this.stamina = stamina;
		this.strength = strength;
		this.defence = defence;
	}

	public int getIdAbility() {
		return idAbilities;
	}

	public void setIdAbility(int idAbility) {
		this.idAbilities = idAbility;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getDribble() {
		return dribble;
	}

	public void setDribble(int dribble) {
		this.dribble = dribble;
	}

	public int getShortPass() {
		return shortPass;
	}

	public void setShortPass(int shortPass) {
		this.shortPass = shortPass;
	}

	public int getLongPass() {
		return longPass;
	}

	public void setLongPass(int longPass) {
		this.longPass = longPass;
	}

	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}

	public int getJump() {
		return jump;
	}

	public void setJump(int jump) {
		this.jump = jump;
	}

	public int getTechnique() {
		return technique;
	}

	public void setTechnique(int technique) {
		this.technique = technique;
	}

	public int accuracy() {
		float result;
		result = (shot + (longPass + shortPass) / 2) / 2;
		return (int) result;
	}

	public int pacing() {
		float result;
		result = (speed + dribble + agility) / 3;
		return (int) result;
	}

	public int physique() {
		float result;
		result = (strength + jump + stamina) / 3;
		return (int) result;
	}

	public int technique() {
		float result;
		result = (dribble + technique) / 2;
		return (int) result;
	}

	public int calculateRating(double[] weights) {
		int[] attributes = { attack, defence, strength, stamina, speed, agility, dribble, shortPass, longPass, shot,
				jump, technique };
		double rating = 0;
		double totalWeight = 0;
		double[] adjustedWeights = new double[weights.length];
		for (int i = 0; i < weights.length; i++) {
	        totalWeight += weights[i];
	    }
		for (int i = 0; i < adjustedWeights.length; i++) {
            adjustedWeights[i] = weights[i] * 100.0 / totalWeight;
        }
		for (int i = 0; i < adjustedWeights.length; i++) {
	        rating += attributes[i] * adjustedWeights[i];
	    }
		rating /= 100;
		return (int) rating;
	}

	public int ratingFinal(int pos) {
		double[] weights;
		switch (pos) { 				 /*atq  def   str   sta  spd   agi   dri  shp   lnp    sht  jum   tec                                        */
		case 0: weights = new double[]{0.0, 0.35, 0.07, 0.0, 0.04, 0.1, 0.01, 0.02, 0.02, 0.03, 0.35, 0.01};
		break;
		case 1: weights = new double[]{0.0, 0.30, 0.25, 0.05, 0.01, 0.05, 0.01, 0.02, 0.04, 0.01, 0.15, 0.01};
		break;
		case 2: weights = new double[]{0.06, 0.06, 0.07, 0.09, 0.06, 0.09, 0.12, 0.12, 0.12, 0.07, 0.02, 0.12};
		break;
		case 3: weights = new double[]{0.1667, 0.0, 0.1333, 0.05, 0.1167, 0.1, 0.1167, 0.05, 0.0167, 0.1667, 0.1333, 0.1167};
		break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + pos);
		}
		return calculateRating(weights);
	}

	@Override
	public String toString() {
		return "Abilities id " + idAbilities + " atk=" + attack + " def=" + defence + " str="
				+ strength + " sta=" + stamina + " spd=" + speed + " agi=" + agility + " dri="
				+ dribble + " shP=" + shortPass + " lgP=" + longPass + " sht=" + shot + " jum=" + jump
				+ " tec=" + technique;
	}

}
