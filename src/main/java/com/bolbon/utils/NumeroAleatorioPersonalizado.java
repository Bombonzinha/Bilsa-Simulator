package com.bolbon.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NumeroAleatorioPersonalizado {

	public static void main(String[] args) {
        int mu = 28;  // El número alrededor del cual quieres centrar la distribución
        double sigma = 7;  // Desviación estándar

        for (int i = 0; i < 100; i++) {
            int generatedNumber = generateNumber(mu, sigma, 15, 45);
            System.out.println("Número generado: " + generatedNumber);
        }
    }

    public static int generateNumber(double mu, double sigma, int min, int max) {
        Random random = new Random();

        // Genera un número aleatorio siguiendo una distribución normal
        double num = mu + random.nextGaussian() * sigma;

        // Redondea al entero más cercano y asegura que esté dentro del rango [0, 99]
        int roundedNum = (int) Math.round(num);
        roundedNum = Math.max(min, Math.min(roundedNum, max));

        return roundedNum;
    }
}
