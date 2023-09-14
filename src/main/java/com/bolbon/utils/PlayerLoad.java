package com.bolbon.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.boot.test.context.SpringBootTest;

import com.bolbon.entities.Abilities;
import com.bolbon.entities.Player;
import com.bolbon.entities.Team;
import com.bolbon.repositories.IAbilitiesRepository;
import com.bolbon.repositories.IPlayerRepository;
import com.bolbon.repositories.ITeamRepository;

@Component
public class PlayerLoad {
	@Autowired
	private IPlayerRepository playerRepository;
	@Autowired
	private ITeamRepository teamRepository;
	@Autowired
	private IAbilitiesRepository abilitiesRepository;
	
	private static Random random = new Random();
	
	public boolean saveAll(Abilities abi, Player pla, Team tea) {
		abilitiesRepository.save(abi);
		pla.setAbilities(abi);
		playerRepository.save(pla);
		tea.getPlayers().add(pla);
		teamRepository.save(tea);
		pla.setTeam(tea);
		abi.setPlayer(pla);
		teamRepository.save(tea);
		playerRepository.save(pla);
		abilitiesRepository.save(abi);
		return true;
	}
	
	public void playerMaker80(String name, int age, int pos, Team team) {
		Abilities abilities = new Abilities(80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80);
		Player player = new Player(name, age, pos);
		saveAll(abilities, player, team);
	}
	
	public void teamMaker(String name) {
		Team team = new Team(name);
		for (int i =0;i<26;i++) {
			playerMaker80(generateRandomString(random.nextInt(11)), random.nextInt(16, 40), random.nextInt(11), team);
		}
	}
	
	public void teamsGenerator(int quant) {
		for (int i = 0; i < quant; i++) {
			teamMaker(generateRandomString(random.nextInt(11)));
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
	
}
