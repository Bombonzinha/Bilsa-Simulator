package com.bolbon.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bolbon.entities.MatchResult;

@Repository("matchResultRepository")
public interface IMatchResultRepository extends JpaRepository<MatchResult, Serializable>{
	public MatchResult findByIdMatchResult(int idMatchResult);
	
	@Query("SELECT m FROM MatchResult m")
	public List<MatchResult> getAllMatchResults();
}
