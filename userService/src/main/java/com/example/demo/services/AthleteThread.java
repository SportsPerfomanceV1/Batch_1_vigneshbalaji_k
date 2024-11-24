package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Enities.Athlete;
import com.example.demo.Enities.User;
import com.example.demo.Repo.AthleteRepo;
import com.example.demo.Repo.UserRepo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AthleteThread extends Thread {

	private final AthleteRepo athleteRepo;
	private final UserRepo userRepo;
	private Athlete athlete;
	private String userIdORathleteId;
	private String operation;
	private String value;

	@Override
	public void run() {
		if (operation.equals("get"))
			getUser();
		else
			setAthlete(userIdORathleteId);
	}

	private void setAthlete(String athleteId) {

		athlete = athleteRepo.findById(athleteId).get();

	}

	public Athlete getAthlete() {
		return this.athlete;
	}

	private User getUser() {
		userIdORathleteId = "";
		return userRepo.findById(userIdORathleteId).orElse(null);
	}

	public void setAthleteId(String userIdORathleteId) {

		this.userIdORathleteId = userIdORathleteId;
	}

}
