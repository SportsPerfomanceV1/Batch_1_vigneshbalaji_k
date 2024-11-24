package com.example.demo.Dto;

import com.example.demo.Enities.Event;
import com.example.demo.Enum.Permission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventData {
	private int eventId;
	private String eventTitle;
	private String meetName;
	private Permission permission;
	private String category;
	private String eventDate;
	private String location;
	private String base64;
}
