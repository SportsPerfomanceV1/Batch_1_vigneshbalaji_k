package com.example.demo.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AthleteData;
import com.example.demo.Enities.Athlete;
import com.example.demo.Repo.AthleteRepo;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AthleteService {

	private final ImageCompressor compressor;
	private final AthleteRepo athleteRepo;

	protected void name() {

	}

	byte[] imageCompressor(byte[] image) throws IOException, java.io.IOException {
		return compressor.compress(image);
	}

	private byte[] imageDecompressor(byte[] image) throws IOException, java.io.IOException {
		return compressor.decompress(image);
	}

	public Set<Integer> getRegisteredEvent(String athleteId) {
		Athlete athlete = athleteRepo.findById(athleteId).orElse(null);
		if (athlete == null)
			return null;
		else
			return athlete.getRegEventId();
	}

	public void saveAthlete(AthleteData athleteData) throws IOException, java.io.IOException {
		Athlete athlete = new Athlete();

		System.out.println(athleteData.getAthleteId() + "" + athleteData.getCoachId());
		BeanUtils.copyProperties(athleteData, athlete);
		// System.out.println("the image data " +
		// Arrays.toString(athleteData.getPhoto()));
		athlete.setPhoto(compressor.compress(athleteData.getPhoto()));
		athleteRepo.save(athlete);
	}

	// profile updation
	public void athleteProfileUpdation(AthleteData athleteData) throws IOException, java.io.IOException {

		Athlete athlete = new Athlete();
		if (athleteData.getPhoto().length != 0)
			athleteData.setPhoto(imageCompressor(athleteData.getPhoto()));
		else
			athleteData.setPhoto(athleteRepo.findById(athleteData.getUserId()).get().getPhoto());
		if (athleteRepo.existsById(athleteData.getUserId())) {
			athleteData.setRegEventId(athleteRepo.findById(athleteData.getUserId()).get().getRegEventId());
		}
		BeanUtils.copyProperties(athleteData, athlete);

		athleteRepo.save(athlete);
	}

	// retrive athlete data depend on athlete id

	public AthleteData getAthlete(String userId) {
		if (athleteRepo.existsById(userId)) {
			Athlete athlete = athleteRepo.findById(userId).get();
			AthleteData athleteData = new AthleteData();
			BeanUtils.copyProperties(athlete, athleteData);
			return athleteData;
		}
		return null;
	}

	public AthleteData getAthleteData(String athleteId) throws IOException, java.io.IOException {
		AthleteData athleteData = new AthleteData();
		if (athleteRepo.existsByAthleteId(athleteId)) {
			Athlete athlete = athleteRepo.findByAthleteId(athleteId);
			athlete.setPhoto(imageDecompressor(athlete.getPhoto()));
			BeanUtils.copyProperties(athlete, athleteData);
			return athleteData;
		}
		return null;
	}

	public AthleteData checkUserAsAthlete(String userId) throws IOException, java.io.IOException {
		if (athleteRepo.existsById(userId)) {
			AthleteData athleteData = new AthleteData();
			Athlete athlete = athleteRepo.findById(userId).get();
			athlete.setPhoto(imageCompressor(athlete.getPhoto()));
			BeanUtils.copyProperties(athlete, athleteData);
			return athleteData;
		}
		return null;
	}

	public void registerEvent(String userId, int eventId) {
		Athlete athlete = null;

		if (athleteRepo.existsById(userId)) {
			athlete = athleteRepo.findById(userId).orElse(null);
			if (athlete != null && athlete.getRegEventId() == null) {
				Set<Integer> list = new HashSet<>();
				list.add(eventId);
				athlete.setRegEventId(list);
			} else {
				athlete.getRegEventId().add(eventId);
			}
			athleteRepo.save(athlete);
		}

	}

}
