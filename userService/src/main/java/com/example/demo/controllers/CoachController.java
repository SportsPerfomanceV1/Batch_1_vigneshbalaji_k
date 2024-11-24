package com.example.demo.controllers;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Dto.AthleteData;
import com.example.demo.Dto.UserData;
import com.example.demo.Enities.Coach;
import com.example.demo.Enum.Gender;
import com.example.demo.Repo.CoachRepo;
import com.example.demo.services.CoachService;
import com.example.demo.services.ImageCompressor;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
public class CoachController {
  
	private final CoachRepo coachRepo;
	private final CoachService coachService;
	private final ImageCompressor image;
	
	
	@GetMapping("/coach/editprofile")
	public ModelAndView getProfilePage(Model model , HttpSession session) {
		 UserData user = (UserData) session.getAttribute("user");
		 Coach coach =  coachRepo.findById(user.getEmail()).orElse(null);
         model.addAttribute("coach", coach);
		 return new ModelAndView("coacheditprofile");
	}
	
	@PostMapping("/coach/updation/profile")
	public ModelAndView updationProfile(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("dob") String dob,
			@RequestParam("gender") Gender gender,
			@RequestParam("coachId") String  coachId,
			@RequestParam("height") double height,
			@RequestParam("weight") double weight,
			@RequestParam("userId") String userId,
			@RequestParam("profile") MultipartFile profile
			) throws IOException {
		
		    Coach coach = Coach
				                              .builder()
				                              .coachId(coachId)
				                              .userId(userId)
				                              .height(height)
				                              .weight(weight)
				                              .photo(image.compress(profile.getBytes()))
				                              .build();
		
	      coachRepo.save(coach);
	      return  new ModelAndView("redirect:/home"); 
	}
	
}
