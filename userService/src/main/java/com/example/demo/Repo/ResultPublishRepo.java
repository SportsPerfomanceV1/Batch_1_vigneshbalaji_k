package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Enities.ResultPublish;

@Repository
public interface ResultPublishRepo extends JpaRepository<ResultPublish, Integer>{

}
