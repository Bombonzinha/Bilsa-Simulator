package com.bolbon.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bolbon.entities.Federation;

@Repository("federationRepository")
public interface IFederationRepository extends JpaRepository<Federation, Serializable>{
	public Federation findByIdFederation(int idFederation);
	
	@Query("SELECT f FROM Federation f LEFT JOIN FETCH f.teams WHERE f.idFederation = :federationId")
	Federation findByIdFederationWithTeams(@Param("federationId") int federationId);
	
	@Query("SELECT f FROM Federation f LEFT JOIN FETCH f.divisions WHERE f.idFederation = :federationId")
    Federation findByIdFederationWithDivisions(@Param("federationId") int federationId);
	
	@Query("SELECT f FROM Federation f")
	public List<Federation> getAllFederations();
}
