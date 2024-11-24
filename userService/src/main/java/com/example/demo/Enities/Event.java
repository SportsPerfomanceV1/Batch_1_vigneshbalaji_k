package com.example.demo.Enities;

import com.example.demo.Enum.Permission;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Event {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;
	private String eventTitle;
	private String meetName;
	private String category;
	@Enumerated(EnumType.STRING)
	private Permission permission;
	private String eventDate;
	private String location;
	@Lob
	@Column(name ="image" , columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	
}
