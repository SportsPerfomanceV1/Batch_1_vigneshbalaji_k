package com.example.demo.Dto;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.Enities.Event;
import com.example.demo.Enities.ResultPublish;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultPublishDto {
	    private String firstPlayer;
	    private String secondPlayer;
	    private int eventId;
	    private Event events;
}
