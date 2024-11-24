package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Enities.Coach;

@Repository
public interface CoachRepo  extends JpaRepository<Coach, String>{

}
