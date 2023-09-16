package com.bolbon.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bolbon.entities.Division;

@Repository("divisionRepository")
public interface IDivisionRepository extends JpaRepository<Division, Serializable> {
	public Division findByIdDivision(int idDivision);

	@Query("SELECT d FROM Division d")
	public List<Division> getAllDivisions();
}
