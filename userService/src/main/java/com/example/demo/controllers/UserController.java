package com.example.demo.controllers;

import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.List;

import org.hibernate.sql.results.jdbc.internal.JdbcValuesResultSetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Dto.AthleteData;
import com.example.demo.Dto.AuthInfo;
import com.example.demo.Dto.CoachData;
import com.example.demo.Dto.EventData;
import com.example.demo.Dto.UserData;
import com.example.demo.Enities.Coach;
import com.example.demo.Enities.Event;
import com.example.demo.Enum.Gender;
import com.example.demo.Repo.CoachRepo;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.jsonwebtoken.io.IOException;
import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import com.example.demo.services.AthleteService;
import com.example.demo.services.AthleteThread;
import com.example.demo.services.EventService;
import com.example.demo.services.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final AthleteThread athleteThread;
	private final EventService eventService;
	private final UserService userService;
	private final AthleteService athleteService;
	private final CoachRepo coachRepo;

	@GetMapping("/")
	public ModelAndView getlandingpage() {
		return new ModelAndView("loginpage");
	}

	@GetMapping("/login")
	public ModelAndView gethome() {
		return new ModelAndView("loginpage");
	}

	@GetMapping("/home")
	public ModelAndView home(Model model, HttpSession session) throws IOException, java.io.IOException {
		List<EventData> events = eventService.getAllEvent();
		model.addAttribute("events", events);

		UserData userData = (UserData) session.getAttribute("user");
		switch (userData.getRole()) {
			case "athlete":
				AthleteData data = athleteService
						.checkUserAsAthlete(((UserData) session.getAttribute("user")).getEmail());
				if (data == null) {
					userData.setAthleteData(AthleteData
							.builder()
							.userId(((UserData) session.getAttribute("user")).getEmail())
							.build());

				} else {
					userData.setAthleteData(data);
				}
				session.setAttribute("user", userData);
				model.addAttribute("users", userData);
				break;
			case "coach", "admin":

				Coach coach = coachRepo.findById(userData.getEmail()).orElse(null);
				session.setAttribute("user", userData);
				model.addAttribute("coach", coach);
				break;

		}

		return new ModelAndView("home");
	}

	@GetMapping("/user/signup")
	public ModelAndView getSignUp() {

		return new ModelAndView("sigUp");
	}

	@GetMapping("/user/signup/reg")
	public ModelAndView saveNewUser(Model model, UserData userData) {

		userData.setGen(userData.getGen().toLowerCase());
		if (userData.getGen().equals("male"))
			userData.setGender(Gender.MALE);
		else
			userData.setGender(Gender.FEMAL);
		userService.saveNewUser(userData);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/home");
		return mav;
	}

	@GetMapping("/getUser/{userid}")
	public UserData getUser(@PathVariable("userid") String userId) {
		UserData userData = userService.getUserData(userId);
		return userData;
	}
}
