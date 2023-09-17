package com.bolbon.entities;

import java.util.Random;

public class Match {
	public static int[] simulationMatch(MatchResult partido) {
		Team home = partido.getHome();
		Team away = partido.getAway();
		int[] goles = simpleMatch(home, away);
		// cuando creo un mr con goles, este calcula todo automaticamente
		partido.simulateMatch(goles[0], goles[1]);
//		System.out.println(home.getName() + " " + goles[0] + " - " + goles[1] + " " + away.getName());
		return goles;
	}

	public static int[] simpleMatch(Team home, Team away) {
		/*
		 * int calcularResultadodelPartido(int loc[90][2], float varloc){ int goles=0;
		 * //variable para retornar la cantidad de goles marcados for (int
		 * i=0;i<90;i++){ //inicializar goles por minuto if
		 * ((float)loc[i][0]<varloc){//si el random es menor al determinante
		 * loc[i][1]=1; //es gol goles=goles+1;//acumulador de goles return goles;
		 */
		// genero los numeros aleatorios (0, 9999) para determinar si es gol o no
		int[] matrixHome = matrixProbabilidad();
		int[] matrixAway = matrixProbabilidad();
		// genero las variable de gol
		int goalVariableHome = variableGol(home.getRating(), away.getRating());
		int goalVariableAway = variableGol(away.getRating(), home.getRating());
		// aca se hace la comparación de las variables con los numeros aleatorios
		int[] goalsHome = matrixGoles(matrixHome, goalVariableHome); // aca van a haber 1 si es gol
		int[] goalsAway = matrixGoles(matrixAway, goalVariableAway);

		int[] goles = { sumaGoles(goalsHome), sumaGoles(goalsAway) };
		return goles;
	}

	// creo la variable de aleatoriedad basada en el rating de los equipos para dar
	// mas dinamismo
	// PENDIENTEEEEEEEEEE
	public static double var(int teamRating) {
		Random random = new Random();
		double var = teamRating * (random.nextDouble(130) - 50) / 100;
		/* suElo*(aleatorioEntre(-50,80)/ 100)/ 100 */
		return var;
	}

	/*
	 * ESTO ES EL VIEJO DE C float variableGol(float miElo, float suElo){ float
	 * varGol=0; // float diferencia=abs((int)miElo-(int)suElo); float
	 * condicionlocal = condicionRandom(miElo, suElo); //variable aleatoria entre
	 * para hacerlo divertido float condicionvisitante = condicionRandom(suElo,
	 * miElo);
	 * 
	 * varGol=miElo/suElo; //cuanto mayor el elo enemigo, menos chance de gol
	 * varGol=(pow(varGol*10, 2)+condicionlocal/10); //se multiplica el valor
	 * anterior
	 * 
	 * // printf("[%1.f] ", varGol); return varGol; }
	 */
	public static int variableGol(int elo1, int elo2) {// elo1 es el del equipo principal
		double gol = 0;
		// int diferencia = Math.abs(elo1-elo2);
		double condicional = var(elo1) / 10;
		gol = elo1 / elo2; // sabiendo que el mínimo el 1000, la mayor diferencia sería 1000vs10000
		gol = (Math.pow(gol * 10, 2) + condicional);
		return (int) gol;
	}

	/*
	 * void numeroRandomaMatriz(int tam, int matriz[90][2]){ for (int i =
	 * 0;i<tam;i++){ matriz[i][0]=rand()%10000; //le asigno valor a la variable
	 * random matriz[i][1]=0; //inicializo los goles en 0 } }
	 */
	public static int[] matrixProbabilidad() {
		int[] matrix = new int[90]; // 90 minutos con los numeros para el gol
		Random random = new Random(); // Crear una instancia de la clase Random
		for (int i = 0; i < matrix.length; i++) {
			int numeroAleatorio = random.nextInt(10000);
			matrix[i] = numeroAleatorio; // Asignar el número aleatorio al arreglo
		}
		return matrix;
	}

	public static int[] matrixGoles(int[] matrixTeam, int goalVariableTeam) {
		int[] matrixFinal = new int[90];
		for (int i = 0; i < matrixTeam.length; i++) {
			if (matrixTeam[i] < goalVariableTeam) {
				matrixFinal[i] = 1;
			}
		}
		return matrixFinal;
	}

	public static int sumaGoles(int[] matrixGoles) {
		int suma = 0;
		for (int i = 0; i < matrixGoles.length; i++)
			suma += matrixGoles[i];
		return suma;
	}
	
	public static int puntosPartido(int favor, int contra) {
		int puntos = 0;
		if (favor < contra) {
			puntos = 1;
		} else if(favor>contra) {
			puntos= 3;
		}
		return puntos;
	}
}
