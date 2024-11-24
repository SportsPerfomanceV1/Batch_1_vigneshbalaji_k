package com.example.demo.Enities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
       
	   @Id
	   private String userId;
	   private double height;
	   private double weight;
	   private String coachId;
	   @Lob
	   @Column(name ="image" , columnDefinition = "MEDIUMBLOB")
	   private byte[] photo;
	
}
