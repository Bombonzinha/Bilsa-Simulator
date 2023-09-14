package com.bolbon.bilsa;

import com.bolbon.entities.Team;
import java.util.Random;
public class Match {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public static void simulation(Team home, Team visit) {
		
	}
	
	//creo la variable de aleatoriedad basada en el rating de los equipos para dar mas dinamismo
	//PENDIENTEEEEEEEEEE
	public static double var(int teamRating) {
		Random random = new Random();
		double var =	teamRating * (random.nextDouble(130)-50)/100;
		/*suElo*(aleatorioEntre(-50,80)/ 100)/ 100 */
		return var;
	}
}
