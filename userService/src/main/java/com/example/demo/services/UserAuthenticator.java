package com.example.demo.services;

import java.net.http.HttpRequest;

import javax.management.relation.Role;

import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AthleteData;
import com.example.demo.Dto.UserData;
import com.example.demo.Enities.Athlete;
import com.example.demo.Repo.AthleteRepo;
import com.example.demo.Repo.UserRepo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthenticator implements UserDetailsService {

	private final UserRepo userRepo;
	private final UserData userData;
	private final HttpSession session;
	private final AthleteRepo athleteRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var user = userRepo.findById(username).get();

		UserDetails userDetails = null;
		if (user != null) {
			userDetails = User.withUsername(user.getEmail())
					.password("{noop}" + user.getPassword())
					.roles(user.getRole())
					.build();
			BeanUtils.copyProperties(user, userData);

			if (athleteRepo.existsById(user.getEmail())) {
				userData.setAthleteData(AthleteData.builder().userId(username).build());
			}
			session.setAttribute("user", userData);
		}

		return userDetails;
	}

}
