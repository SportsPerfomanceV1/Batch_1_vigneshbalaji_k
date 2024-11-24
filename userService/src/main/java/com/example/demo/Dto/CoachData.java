package com.example.demo.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoachData {
	
	   private String userId;
	   private double height;
	   private double weight;
	   private String coachId;
	   private byte[] photo;
}
