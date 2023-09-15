package com.bolbon.utils;

import java.util.Random;

import com.bolbon.entities.*;

public class AbilitiesGenerator {
    private static double[] weights = {0.08, 0.08, 0.05, 0.12, 0.16, 0.11, 0.11, 0.09, 0.08, 0.03, 0.03, 0.06};
    private static final Random random = new Random();

    public static Abilities generateAbilitiesForPosition(int position) {
        Abilities abilities = new Abilities();
        weights=weights(position);
        
        for (int i = 0; i < weights.length; i++) {
            double randomValue = random.nextDouble() * 100; // Valor aleatorio entre 0 y 100
            int adjustedValue;
            if (randomValue < 25) {
                // 25% de posibilidades de que el valor esté entre 0 y 25
                adjustedValue = (int) (Math.random() * 26);
            } else if (randomValue < 50) {
                // 25% de posibilidades de que el valor esté entre 26 y 40
                adjustedValue = (int) (Math.random() * 15) + 26;
            } else if (randomValue < 70) {
                // 20% de posibilidades de que el valor esté entre 41 y 50
                adjustedValue = (int) (Math.random() * 10) + 41;
            } else if (randomValue < 90) {
                // 20% de posibilidades de que el valor esté entre 51 y 60
                adjustedValue = (int) (Math.random() * 10) + 51;
            } else if (randomValue < 95) {
                // 5% de posibilidades de que el valor esté entre 61 y 70
                adjustedValue = (int) (Math.random() * 10) + 61;
            } else if (randomValue < 98) {
                // 3% de posibilidades de que el valor esté entre 71 y 75
                adjustedValue = (int) (Math.random() * 5) + 71;
            } else if (randomValue < 99) {
                // 1% de posibilidades de que el valor esté entre 76 y 80
                adjustedValue = (int) (Math.random() * 5) + 76;
            } else if (randomValue < 99.5) {
                // 0.5% de posibilidades de que el valor esté entre 85 y 90
                adjustedValue = (int) (Math.random() * 6) + 85;
            } else if (randomValue < 99.8) {
                // 0.3% de posibilidades de que el valor sea 96
                adjustedValue = 96;
            } else if (randomValue < 99.9) {
                // 0.1% de posibilidades de que el valor sea 97
                adjustedValue = 97;
            } else if (randomValue <99.98){
                // 0.08% de posibilidades de que el valor sea 98
                adjustedValue = 98;
            } else {
            	adjustedValue = 99;
            }

            
            // Establecer el valor en la habilidad correspondiente
            switch (i) {
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
        
        return abilities;
    }

    private static double getPositionWeight(int position, int attributeIndex) {
        // Define los pesos por posición aquí (puedes tener un array similar a 'weights' para los pesos por posición)
    	double[][] positionWeights = {
    		    // Define los pesos para cada posición (0-10)
    		    // Por ejemplo, para la posición 0:
    		    {0.0, 0.35, 0.07, 0.0, 0.04, 0.1, 0.01, 0.02, 0.02, 0.03, 0.35, 0.01},
    		    // Para la posición 1:
    		    {0.0, 0.30, 0.25, 0.05, 0.01, 0.05, 0.01, 0.02, 0.04, 0.01, 0.15, 0.01},
    		    // Para la posición 2:
    		    {0.08, 0.08, 0.05, 0.12, 0.16, 0.11, 0.11, 0.09, 0.08, 0.03, 0.03, 0.06},
    		    // Para la posición 3:
    		    {0.08, 0.08, 0.05, 0.12, 0.16, 0.11, 0.11, 0.09, 0.08, 0.03, 0.03, 0.06},
    		    // Para la posición 4:
    		    {0.04, 0.12, 0.08, 0.07, 0.03, 0.01, 0.13, 0.13, 0.13, 0.04, 0.09, 0.13},
    		    // Para la posición 5:
    		    {0.00, 0.20, 0.14, 0.12, 0.10, 0.02, 0.02, 0.04, 0.12, 0.02, 0.16, 0.06},
    		    // Para la posición 6:
    		    {0.11, 0.03, 0.05, 0.12, 0.14, 0.09, 0.09, 0.08, 0.09, 0.09, 0.03, 0.07},
    		    // Para la posición 7:
    		    {0.09, 0.06, 0.06, 0.11, 0.06, 0.08, 0.14, 0.15, 0.15, 0.09, 0.02, 0.15},
    		    // Para la posición 8:
    		    {0.13, 0.00, 0.12, 0.05, 0.08, 0.08, 0.09, 0.07, 0.03, 0.13, 0.12, 0.09},
    		    // Para la posición 9:
    		    {0.09, 0.02, 0.09, 0.06, 0.05, 0.09, 0.12, 0.12, 0.12, 0.11, 0.01, 0.12},
    		    // Para la posición 10:
    		    {0.11, 0.03, 0.05, 0.12, 0.14, 0.09, 0.09, 0.08, 0.09, 0.09, 0.03, 0.07}
    		};
        
        return positionWeights[position][attributeIndex];
    }
    
    public static double[] weights(int pos) {
    	double[] weights;
		switch (pos) { 				 /*atq  def   str   sta  spd   agi   dri  shp   lnp    sht  jum   tec                                        */
		case 0: weights = new double[]{0.0, 0.35, 0.07, 0.0, 0.04, 0.1, 0.01, 0.02, 0.02, 0.03, 0.35, 0.01};
		break;
		case 1: weights = new double[]{0.0, 0.30, 0.25, 0.05, 0.01, 0.05, 0.01, 0.02, 0.04, 0.01, 0.15, 0.01};
		break;
		case 2: weights = new double[]{0.08, 0.08, 0.05, 0.12, 0.16, 0.11, 0.11, 0.09, 0.08, 0.03, 0.03, 0.06};
		break;
		case 3: weights = new double[]{0.08, 0.08, 0.05, 0.12, 0.16, 0.11, 0.11, 0.09, 0.08, 0.03, 0.03, 0.06};
		break;
		case 4: weights = new double[]{0.04, 0.12, 0.08, 0.07, 0.03, 0.01, 0.13, 0.13, 0.13, 0.04, 0.09, 0.13};
		break;
		case 5: weights = new double[]{0.00, 0.20, 0.14, 0.12, 0.10, 0.02, 0.02, 0.04, 0.12, 0.02, 0.16, 0.06};
		break;
		case 6: weights = new double[]{0.11, 0.03, 0.05, 0.12, 0.14, 0.09, 0.09, 0.08, 0.09, 0.09, 0.03, 0.07};
		break;
		case 7: weights = new double[]{0.09, 0.06, 0.06, 0.11, 0.06, 0.08, 0.14, 0.15, 0.15, 0.09, 0.02, 0.15};
		break;
		case 8: weights = new double[]{0.13, 0.00, 0.12, 0.05, 0.08, 0.08, 0.09, 0.07, 0.03, 0.13, 0.12, 0.09};
		break;
		case 9: weights = new double[]{0.09, 0.02, 0.09, 0.06, 0.05, 0.09, 0.12, 0.12, 0.12, 0.11, 0.01, 0.12};
		break;
		case 10: weights = new double[]{0.11, 0.03, 0.05, 0.12, 0.14, 0.09, 0.09, 0.08, 0.09, 0.09, 0.03, 0.07};
		break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + pos);
		}
		return weights;
    }
    public static void main(String[] args) {
        int position = 1; // Cambia la posición según sea necesario
        Abilities generatedAbilities = generateAbilitiesForPosition(position);
        
        System.out.println("Abilities generated for position " + position + ":");
        System.out.println(generatedAbilities);
        System.out.println(generatedAbilities.ratingFinal(position));
    }
}
