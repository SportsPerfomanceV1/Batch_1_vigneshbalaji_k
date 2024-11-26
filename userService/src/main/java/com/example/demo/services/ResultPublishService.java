package com.example.demo.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AthleteData;
import com.example.demo.Dto.AthleteData;
import com.example.demo.Dto.ResultPublishDto;
import com.example.demo.Enities.Athlete;
import com.example.demo.Enities.Event;
import com.example.demo.Enities.ResultPublish;
import com.example.demo.Repo.AthleteRepo;
import com.example.demo.Repo.EventRepo;
import com.example.demo.Repo.ResultPublishRepo;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResultPublishService {

	private final ResultPublishRepo resultPublishRepo;
	private final EventRepo eventRepo;
	private final AthleteRepo athleteRepo;
	private final ImageCompressor compressor;;
	private final AthleteService athleteService;

	public void saveResult(ResultPublishDto dto) {
		ResultPublish eventResult = ResultPublish.builder()
				.firstPlayer(dto.getFirstPlayer())
				.secondPlayer(dto.getSecondPlayer())
				.eventId(dto.getEventId())
				.build();
		resultPublishRepo.save(eventResult);
	}

	public ResultPublishDto getEventResult(String eventid) {
		ResultPublishDto resultPublishDto = new ResultPublishDto();
		int eventId = Integer.parseInt(eventid);
		if (resultPublishRepo.existsById(eventId)) {
			ResultPublish resultPublish = resultPublishRepo.findById(eventId).get();
			BeanUtils.copyProperties(resultPublish, resultPublishDto);
		}
		return resultPublishDto;
	}

	public List<ResultPublishDto> getAllResult() {
		List<ResultPublish> results = resultPublishRepo.findAll();
		List<ResultPublishDto> resultEvent = new ArrayList<>();

		for (ResultPublish result : results) {

			Event event2 = eventRepo.findById(result.getEventId()).orElse(null);
			if (event2 != null) {
				event2.setImage(null);
				ResultPublishDto dto = new ResultPublishDto();
				BeanUtils.copyProperties(result, dto);
				dto.setEvents(event2);
				resultEvent.add(dto);
			}
		}

		return resultEvent;
	}

	public List<AthleteData> getWinner(int eventId) throws IOException, java.io.IOException {
		List<AthleteData> winners = new ArrayList<>();
		if (resultPublishRepo.existsById(eventId)) {
			final ResultPublish result = resultPublishRepo.findById(eventId).orElse(null);
			if (result != null && athleteRepo.existsByAthleteId(result.getFirstPlayer())) {
				AthleteData fp = athleteService.getAthleteData(result.getFirstPlayer());
				// fp.setBase64Image(getBase64(fp.getPhoto()));
				winners.add(fp);
			}
			if (result != null && athleteRepo.existsByAthleteId(result.getSecondPlayer())) {
				AthleteData sp = athleteService.getAthleteData(result.getSecondPlayer());
				// sp.setBase64Image(getBase64(sp.getPhoto()));
				winners.add(sp);

			}
		}
		return winners;
	}

	private String getBase64(byte[] image) {
		return Base64.getEncoder().encodeToString(image);
	}

}
