package com.example.demo.Dto;

import java.util.List;
import java.util.Set;

import com.example.demo.Enities.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AthleteData {
	 
	private String userId;
	   private String athleteId;
	   private double height;
	   private double weight;
	   private Set<Integer> regEventId;
	   private String coachId;
	   private byte[] photo;
	 
}
