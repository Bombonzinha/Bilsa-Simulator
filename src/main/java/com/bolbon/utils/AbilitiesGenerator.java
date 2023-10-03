package com.bolbon.utils;

import java.util.Random;

import com.bolbon.entities.*;

public class AbilitiesGenerator {
	private static double[] weights = { 0.08, 0.08, 0.05, 0.12, 0.16, 0.11, 0.11, 0.09, 0.08, 0.03, 0.03, 0.06 };

	public static Abilities generateAbilitiesForPosition(int position) {
		Abilities abilities = new Abilities();
		weights = weights(position);

		for (int i = 0; i < weights.length; i++) { // por cada habilidad
			int adjustedValue = NumeroAleatorioPersonalizado.generateNumber(weights[i], 12.5, 0, 99);//20 malos, 30 normal, 40 buenos
			// Establecer el valor en la habilidad correspondiente
			setAbilityValue(abilities, i, adjustedValue);
		}
		return abilities;
	}

	public static double[] weights(int pos) {
		double[] weights;
		switch (pos) {             /* atq  def   str    sta   spd   agi   dri   shp   lnp   sht   jum   tec */
		case 0: // Arquero
			weights = new double[] { 5.0, 40.0, 25.0, 20.0, 20.0, 25.0, 10.0, 20.0, 20.0, 10.0, 40.0, 10.0 };
			break;
		case 1: // Defensor
			weights = new double[] { 15.0, 40.0, 35.0, 25.0, 20.0, 10.0, 10.0, 25.0, 30.0, 15.0, 35.0, 15.0 };
			break;
		case 2: // Medio
			weights = new double[] { 30.0, 25.0, 25.0, 40.0, 30.0, 30.0, 40.0, 40.0, 40.0, 20.0, 10.0, 40.0 };
			break;
		case 3: // Delantero
			weights = new double[] { 40.0, 5.0, 30.0, 25.0, 30.0, 30.0, 30.0, 25.0, 15.0, 40.0, 30.0, 30.0 };
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + pos);
		}
		return weights;
	}

	private static void setAbilityValue(Abilities abilities, int abilityIndex, int adjustedValue) {
		switch (abilityIndex) {
		case 0:
			abilities.setAttack(adjustedValue);
			break;
		case 1:
			abilities.setDefence(adjustedValue);
			break;
		case 2:
			abilities.setStrength(adjustedValue);
			break;
		case 3:
			abilities.setStamina(adjustedValue);
			break;
		case 4:
			abilities.setSpeed(adjustedValue);
			break;
		case 5:
			abilities.setAgility(adjustedValue);
			break;
		case 6:
			abilities.setDribble(adjustedValue);
			break;
		case 7:
			abilities.setShortPass(adjustedValue);
			break;
		case 8:
			abilities.setLongPass(adjustedValue);
			break;
		case 9:
			abilities.setShot(adjustedValue);
			break;
		case 10:
			abilities.setJump(adjustedValue);
			break;
		case 11:
			abilities.setTechnique(adjustedValue);
			break;
		}
	}

	public static void main(String[] args) {
		int position = 3; // Cambia la posición según sea necesario
		Abilities generatedAbilities = generateAbilitiesForPosition(position);

		System.out.println("Abilities generated for position " + position + ":");
		System.out.println(generatedAbilities);
		System.out.println(generatedAbilities.ratingFinal(position));
	}
}
