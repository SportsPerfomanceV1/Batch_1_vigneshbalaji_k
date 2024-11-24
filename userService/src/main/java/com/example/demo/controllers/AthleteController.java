package com.example.demo.controllers;

import java.beans.Beans;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.Dto.AthleteData;
import com.example.demo.Dto.UserData;
import com.example.demo.Enities.Athlete;
import com.example.demo.Enities.Event;
import com.example.demo.Enities.User;
import com.example.demo.Enum.Gender;
import com.example.demo.Repo.AthleteRepo;
import com.example.demo.Repo.EventRepo;
import com.example.demo.Repo.UserRepo;
import com.example.demo.services.AthleteService;
import com.example.demo.services.AthleteThread;
import com.example.demo.services.EventService;
import com.example.demo.services.UserService;

import io.jsonwebtoken.lang.Arrays;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AthleteController {

	private final UserService userService;
	private final AthleteRepo athleteRepo;
	private final AthleteThread thread;
	private final AthleteService athleteService;
	private final EventRepo eventRepo;

	@GetMapping("/athlete/editprofile")
	public ModelAndView getEditPage(HttpSession session) {
		UserData user = (UserData) session.getAttribute("user");
		AthleteData athleteData = athleteService.getAthlete(user.getEmail());
		if (athleteData != null)
			user.setAthleteData(athleteData);
		else
			user.setAthleteData(new AthleteData());

		session.setAttribute("user", user);

		return new ModelAndView("editAthleteProfile");
	}

	@GetMapping("/athlete/getUser/{userid}")
	public RedirectView getuserData(@PathVariable("userid") String userid) {

		return new RedirectView("/getUser/" + userid);
	}

	/*
	 * form.append('firstName',firstName.value);
	 * form.append('lastName',lastname.value);
	 * form.append('dob',birthdate.value);
	 * form.append('gender',gender.value);
	 * form.append('coachId' ,coachId.value);
	 * form.append('category',category.value);
	 * form.append('height',height.value);
	 * form.append('weight',weight.value);
	 * form.append('profile',profile.files[0]);
	 */
	@PostMapping("/athlete/register")
	public ModelAndView athleteRegister(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("dob") String dob,
			@RequestParam("gender") Gender gender,
			@RequestParam("coachId") String coachId,
			@RequestParam("athleteId") String athleteId,
			@RequestParam("height") double height,
			@RequestParam("weight") double weight,
			@RequestParam("userId") String userId,
			@RequestParam("profile") MultipartFile profile,
			HttpSession session) throws IOException {

		AthleteData athleteData = AthleteData
				.builder()
				.userId(userId)
				.coachId(coachId)
				.photo(profile.getBytes())
				.weight(weight)
				.height(height)
				.athleteId(athleteId)
				.build();

		UserData user = (UserData) session.getAttribute("user");
		user.setAthleteData(athleteData);
		session.setAttribute("user", user);
		athleteService.saveAthlete(athleteData);
		return new ModelAndView("redirect:/home");
	}

	@PostMapping("/athlete/updation/profile")
	public ModelAndView updationProfile(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("dob") String dob,
			@RequestParam("gender") Gender gender,
			@RequestParam("coachId") String coachId,
			@RequestParam("height") double height,
			@RequestParam("weight") double weight,
			@RequestParam("userId") String userId,
			@RequestParam("athleteId") String athleteId,
			@RequestParam("profile") MultipartFile profile) throws IOException {

		AthleteData athleteData = AthleteData
				.builder()
				.coachId(coachId)
				.userId(userId)
				.photo(profile.getBytes())
				.height(height)
				.weight(weight)
				.athleteId(athleteId)
				.build();

		athleteService.athleteProfileUpdation(athleteData);
		return new ModelAndView("redirect:/home");
	}

	@GetMapping("/athlete/regevent")
	public ModelAndView getRegEvent(Model model, HttpSession session) {
		// List<Integer> l = List.of(1,2);
		Set<Integer> regEvent = athleteService
				.getRegisteredEvent(((UserData) (session.getAttribute("user"))).getEmail());
		if (regEvent == null)
			regEvent = new HashSet<Integer>();
		List<Event> events = eventRepo.getRegevent(regEvent);
		// System.out.println("the regevent :" + events.size());
		model.addAttribute("events", events);

		return new ModelAndView("registeredEvent");
	}

	@GetMapping("/athlete/event/register/{eventId}")
	public String eventRegister(@PathVariable("eventId") String eventid, HttpSession session) {
		UserData user = (UserData) session.getAttribute("user");

		if (user.getAthleteData() != null && user.getAthleteData().getAthleteId() != null) {

			System.out.println(eventid);
			int eventId = Integer.parseInt(eventid.replaceAll("\\D", ""));

			athleteService.registerEvent(user.getEmail(), eventId);
			return "save";
		} else {
			System.out.println("you not a athlete");
		}
		return "goot";
	}

}
