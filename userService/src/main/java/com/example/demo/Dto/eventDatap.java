package com.example.demo.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class eventDatap {
	private int eventId;
	private String eventTitle;
	private String meetName;
	private String category;
	private String eventDate;
	private String location;
	private String photoUrl;
	private MultipartFile image;
}
