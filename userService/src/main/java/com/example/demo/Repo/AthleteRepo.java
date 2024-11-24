package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Enities.Athlete;

@Repository
public interface AthleteRepo extends JpaRepository<Athlete, String> {

	public  Athlete findByAthleteId(String athleteId);
	
	public boolean existsByAthleteId(String athleteId);
	
}
