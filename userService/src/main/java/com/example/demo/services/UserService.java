package com.example.demo.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.UserData;
import com.example.demo.Enities.User;
import com.example.demo.Repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepo userRepo;

	public UserData getUserData(String userId) {
		User user = userRepo.findById(userId).orElseThrow();
		UserData userData = new UserData();
		BeanUtils.copyProperties(user, userData);
		return userData;
	}

	public void saveNewUser(UserData userData) {
		User user = new User();
		BeanUtils.copyProperties(userData, user);

		userRepo.save(user);
	}

}
