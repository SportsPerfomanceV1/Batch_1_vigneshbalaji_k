package com.example.demo.services;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.tomcat.jni.Buffer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Dto.EventData;
import com.example.demo.Dto.eventDatap;
import com.example.demo.Enities.Event;
import com.example.demo.Enum.Permission;
import com.example.demo.Repo.EventRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepo eventRepo;
	private final ImageCompressor compressor;

	public boolean eventCreation(EventData registerEvent, MultipartFile image) throws IOException {
		byte[] compressedImg = compressor.compress(image.getBytes());
		Event newEvent = new Event();
		BeanUtils.copyProperties(registerEvent, newEvent);
		newEvent.setImage(compressedImg);
		eventRepo.save(newEvent);
		// var preEvent = eventRepo.findLastRow();
		// long currentId ;
		// if (preEvent.isEmpty()) {
		// System.out.println("sorry i am null");
		// currentId = 1;
		// }else
		// currentId = preEvent.get().getEventId() + 1;
		// System.out.println("hi this is event service class method the given data is
		// :" + registerEvent);
		// Event event = new Event();
		// registerEvent.setPhotoUrl(storgeImgFs(image, currentId ,
		// registerEvent.getEventTitle()));
		// System.out.println("i am getting a url such as :" +
		// registerEvent.getPhotoUrl());
		// BeanUtils.copyProperties(registerEvent, event);
		// eventRepo.save(event);
		return true;
	}

	public EventData getEventById(int eventId) throws io.jsonwebtoken.io.IOException, IOException {
		EventData eventData = new EventData();
		if (eventRepo.existsById(eventId)) {
			Event event = eventRepo.findById(eventId).orElseThrow();
			event.setImage(compressor.decompress(event.getImage()));
			String base64 = getBase64(event.getImage());
			// System.out.println("the retrive event from event service class :" + event);
			BeanUtils.copyProperties(event, eventData);
			eventData.setBase64(base64);
		}
		return eventData;
	}

	public EventData getEventByEventName(String eventTitle) throws io.jsonwebtoken.io.IOException, IOException {
		EventData eventData = new EventData();
		if (eventRepo.existsByEventTitle(eventTitle)) {
			Event event = eventRepo.findByEventTitle(eventTitle).orElseThrow();
			event.setImage(compressor.decompress(event.getImage()));
			// System.out.println("this retrive event from event service class :" + event);
			String base64 = getBase64(event.getImage());
			BeanUtils.copyProperties(event, eventData);
			eventData.setBase64(base64);
			return eventData;

		}
		return null;
	}

	public List<EventData> getAllEvent() throws io.jsonwebtoken.io.IOException, IOException {
		List<Event> events = eventRepo.findAll();
		List<EventData> eventDatas = new ArrayList<>();
		for (Event event : events) {
			event.setImage(compressor.decompress(event.getImage()));
			EventData eventData = new EventData();
			BeanUtils.copyProperties(event, eventData);
			eventData.setBase64(getBase64(event.getImage()));
			eventDatas.add(eventData);
		}
		return eventDatas;
	}

	private String getBase64(byte[] image) {
		return Base64.getEncoder().encodeToString(image);
	}

	public void changePermission(int eventId) {
		if (eventRepo.existsById(eventId)) {
			Event event = eventRepo.findById(eventId).orElseThrow();
			event.setPermission(Permission.APPROVE);
			eventRepo.save(event);

		}
	}

	public void disapprove(int eventId) {
		if (eventRepo.existsById(eventId)) {
			eventRepo.deleteById(eventId);
		}
	}

}
