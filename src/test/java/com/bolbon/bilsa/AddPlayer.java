package com.bolbon.bilsa;


import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.bolbon.entities.Abilities;
import com.bolbon.entities.Player;
import com.bolbon.entities.Team;
import com.bolbon.repositories.IAbilitiesRepository;
import com.bolbon.repositories.IPlayerRepository;
import com.bolbon.repositories.ITeamRepository;
import com.bolbon.utils.AbilitiesGenerator;
import com.bolbon.utils.PlayerLoad;
import com.bolbon.utils.RandomWordPicker;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
//@Component
public class AddPlayer {
	@Autowired
	private IPlayerRepository playerRepository;
	@Autowired
	private ITeamRepository teamRepository;
	@Autowired
	private IAbilitiesRepository abilitiesRepository;
	private static Random random = new Random();
	@Test
	public void testAddPlayer() {
		/*attack, defence, strength, stamina, speed, agility, dribble, shortPass, longPass, shot,
				jump, technique*/
//		teamsGenerator(20);
//		String resultados = "";
//		for (int i = 0; i< 10;i++) {
//			long a = random.nextLong(1, 20);
//			long b = random.nextLong(1, 20);
//			Team home = teamRepository.findByIdTeamWithPlayers(a);
//			Team away = teamRepository.findByIdTeamWithPlayers(b);
//			resultados += Match.simulationSimple(home, away) + "\n";
//		}
//		System.out.println(resultados);

	}
	
	public boolean saveAll(Abilities abi, Player pla, Team tea) {
		pla.setAbilities(abi);
		tea.getPlayers().add(pla);
		pla.setTeam(tea);
		abi.setPlayer(pla);
		pla.calcularRating();
		teamRepository.save(tea);
		playerRepository.save(pla);
		abilitiesRepository.save(abi);
		return true;
	}
	
	public void playerMaker80(String name, int age, int pos, Team team) {
//		Abilities abilities = new Abilities(80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80);
		Abilities abilities = AbilitiesGenerator.generateAbilitiesForPosition(pos);
		Player player = new Player(name, age, pos);
		playerRepository.save(player);
		abilitiesRepository.save(abilities);
		saveAll(abilities, player, team);
	}
	
	public void teamMaker(String name) {
		Team team = new Team(name);
		//teamRepository.save(team);
		
		for (int i =0;i<26;i++) {
			playerMaker80(nombre(), random.nextInt(16, 40), random.nextInt(11), team);
		}
		team.calcularRating();
		teamRepository.save(team);
	}
	
	public void teamsGenerator(int quant) {
		for (int i = 0; i < quant; i++) {
			teamMaker(nombre());
		}
	}
	public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
	
	public String nombre() {
		String filePath = "D:\\Eclipse\\sts-4.18.1.RELEASE\\XXX\\nombres\\words.txt";
    	String nombre = RandomWordPicker.pickRandomWord(filePath);
    	return nombre;
	}
}
