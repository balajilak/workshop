package com.gfk.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfk.model.Event;

@RestController
public class EventsReportController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(EventsReportController.class);
	
	@RequestMapping("/events")
	public List<Event> getEvents(){
		List<Event> events = new ArrayList<Event>();
		System.out.println("test");
		Event event1 = new Event();
		event1.setName("Java User Group");
		
		events.add(event1);
		
		Event event2 = new Event();
		event2.setName("Angular User Group");
		
		events.add(event2);
		
		logger.debug("test dubug info on the console");
		
		return events;
		
	}

}
