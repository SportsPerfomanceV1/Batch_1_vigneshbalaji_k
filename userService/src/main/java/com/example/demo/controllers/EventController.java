package com.example.demo.controllers;

import java.io.IOException;
import java.net.http.HttpClient.Redirect;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;
import com.example.demo.Dto.EventData;
import com.example.demo.Dto.eventDatap;
import com.example.demo.Enities.Event;
import com.example.demo.Enum.Permission;
import com.example.demo.services.EventService;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class EventController {
	
	private final EventService eventService;
	
	@GetMapping("/event/getCreationpage")
	public ModelAndView getCreationpage() {
		return  new ModelAndView("eventcreation");
	}
	
	@GetMapping("/event/registered_event")
    public ModelAndView getregisteredEvent () {
    	return new ModelAndView("registeredEvent");
    }
	@PostMapping(" ")
	public ModelAndView eventCreation(
			@RequestParam("image") MultipartFile image,
			@RequestParam("eventTitle") String eventTitle,
			@RequestParam("meetName") String meetName,
			@RequestParam("category") String category,
			@RequestParam("eventDate") String eventDate,
			@RequestParam("location") String location	
	) throws IOException{
	 
	 	EventData eventData = EventData.builder()
		        .eventTitle(eventTitle)
	        	.category(category)
	     	    .location(location)
		        .meetName(meetName)
		        .eventDate(eventDate)
		        .permission(Permission.NOT_APPROVE)
		        .build();
//		System.out.println("this is event that come to store that event in db :" + eventData);
		eventService.eventCreation(eventData , image);
//		return  new RedirectView("http://localhost:8080/home");
		return  new ModelAndView("redirect:/home");
	}
	
	@GetMapping("/event/getall")
	public List<EventData> getAllEventWithoutModel() throws io.jsonwebtoken.io.IOException, IOException{
		return  eventService.getAllEvent();
	}
	
	@GetMapping("/event/getAllEvent")
	public ModelAndView getAllEventWithModel(Model model) throws io.jsonwebtoken.io.IOException, IOException {
		List<EventData> events = eventService.getAllEvent();
	    model.addAttribute("events", events);
		return new ModelAndView("waitingforapprove");
	}
	@GetMapping("/event/event/findbyevent/{id}")
	public EventData findByeventId (@PathVariable("id") int id) throws io.jsonwebtoken.io.IOException, IOException {
//		System.out.println("this is given event id :" + id);
		 return eventService.getEventById(id);
	}
	
	@GetMapping("/event/findbyeventname/{eventname}")
	public EventData findByeventName (@PathVariable("eventname") String eventName) throws io.jsonwebtoken.io.IOException, IOException {
//		System.out.println("this is given event name :" + eventName);
	          return eventService.getEventByEventName(eventName);	
	}
	
	@GetMapping("/event/permission/{eventId}")
	public String setPermission(@PathVariable("eventId") int eventId) {
		System.out.println("the given event id is " + eventId);
		 eventService.changePermission(eventId);
		 return "i done my work";
	}
	
	@GetMapping("/event/disapprove/{eventId}")
	public String disapprove(@PathVariable int eventId) {
//		int eventID = Integer.parseInt(eventId);
		System.out.println("incoming event id is :" + eventId);
		 eventService.disapprove(eventId);
		 return "i done my work";
	}
	
	@GetMapping("/event/getregevent")
	public ModelAndView getRegEvent() {
		return  new ModelAndView("redirect:/athlete/regevent");
	}
	


}
