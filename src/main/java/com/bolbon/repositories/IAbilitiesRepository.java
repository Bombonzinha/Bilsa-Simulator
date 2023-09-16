package com.bolbon.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bolbon.entities.Abilities;

@Repository("abilitiesRepository")
public interface IAbilitiesRepository extends JpaRepository<Abilities, Serializable>{
	public Abilities findByIdAbilities(int idAbility);

	@Query("SELECT a FROM Abilities a")
	public List<Abilities> getAllAbilities();
	
	
}
