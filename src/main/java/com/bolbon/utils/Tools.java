package com.bolbon.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tools {
	public static void ordenarAleatoriamente(List<?> lista) {
		long semilla = System.nanoTime();
		Collections.shuffle(lista, new Random(semilla));
	}
	// mu= referencia sigma= amplitud
	public static int gaussianoAleatorio(int mu, double sigma) {
        Random random = new Random();

        // Genera un número aleatorio siguiendo una distribución normal
        double num = mu + random.nextGaussian() * sigma;

        // Redondea al entero más cercano y asegura que esté dentro del rango [0, 99]
        int roundedNum = (int) Math.round(num);
        roundedNum = Math.max(0, Math.min(roundedNum, 99));

        return roundedNum;
    }

}
