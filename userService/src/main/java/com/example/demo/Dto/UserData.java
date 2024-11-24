package com.example.demo.Dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.demo.Enities.Athlete;
import com.example.demo.Enum.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Component
@Scope("prototype")
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
	private String firstName;
	private String lastName;
	private Gender gender;
	private String gen;
	private int age;
	private String dob;
	private String role;
	private String email;
	private String password;
	private AthleteData athleteData;
}
