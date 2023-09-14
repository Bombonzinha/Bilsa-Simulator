package com.bolbon.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bolbon.entities.Player;

@Repository("playerRepository")
public interface IPlayerRepository extends JpaRepository<Player, Integer> {
	public Player findByIdPlayer(int idPlayer);

	@Query("SELECT p FROM Player p")
	public List<Player> getAllPlayers();

}
