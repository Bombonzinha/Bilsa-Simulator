package com.bolbon.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bolbon.entities.Team;

@Repository("teamRepository")
public interface ITeamRepository extends JpaRepository<Team, Serializable>{
	public Team findByIdTeam(int idTeam);
	
	@Query("SELECT t FROM Team t LEFT JOIN FETCH t.players WHERE t.idTeam = :teamId")
	Team findByIdTeamWithPlayers(@Param("teamId") Long teamId);

	@Query("SELECT t FROM Team t")
	public List<Team> getAllTeams();
}
