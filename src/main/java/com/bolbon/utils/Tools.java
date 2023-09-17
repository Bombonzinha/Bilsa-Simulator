package com.bolbon.utils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tools {
	public static void ordenarAleatoriamente(List<?> lista) {
		long semilla = System.nanoTime();
		Collections.shuffle(lista, new Random(semilla));
	}

}
