package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Dto.AthleteData;
import com.example.demo.Dto.ResultPublishDto;
import com.example.demo.Enities.Athlete;
import com.example.demo.Enities.ResultPublish;
import com.example.demo.services.ResultPublishService;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class ResultPublishController {

	private final ResultPublishService resultService;

	@PostMapping("/event/saveResult")
	public void saveResult(
			@RequestParam("firstPlayer") String firstPlayer,
			@RequestParam("secondPlayer") String secondPlayer,
			@RequestParam("eventId") String eventId) {

		final String event = eventId.replaceAll("\\D", "");
		int eventid = Integer.parseInt(event);
		ResultPublishDto dto = ResultPublishDto.builder().firstPlayer(firstPlayer).secondPlayer(secondPlayer)
				.eventId(eventid).build();
		resultService.saveResult(dto);
	}

	@GetMapping("/getResultevent/{eventId}")
	public ResultPublishDto getEventResult(@PathVariable("eventId") String event) {
		final String eventId = event.replaceAll("\\D", "");
		return resultService.getEventResult(eventId);
	}

	@GetMapping("/event/getAllResult")
	public ModelAndView getAllResult(Model model) {
		System.out.println("this is getallResult method in java");
		List<ResultPublishDto> allResult = resultService.getAllResult();
		model.addAttribute("results", allResult);
		return new ModelAndView("resultPage");

	}

	@GetMapping("/result/getWinners/{eventId}")
	public List<AthleteData> getWinners(@PathVariable("eventId") String eventId, Model model)
			throws IOException, java.io.IOException {
		int eventid = Integer.parseInt(eventId.replaceAll("//D", ""));

		List<AthleteData> winners = resultService.getWinner(eventid);

		return winners;
	}

}
