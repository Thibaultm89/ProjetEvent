package be.afelio.software_academy.controllers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.beans.Event;
import be.afelio.software_academy.repository.DataRepository;

public class EventController extends BaseController{

	public EventController(DataRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
	}
	
	public void findAllEvents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object o = repository.findAllEvents();
		response.getWriter().write(objectToJson(o));
	}

	public void findOneEventById(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getPathInfo().split("/")[2]);
		Object o = repository.findOneEventById(id);
		response.getWriter().write(objectToJson(o));	
	}
	
	public void addEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Event event = null;
		try {
			event = jsonStreamToObject(request.getInputStream(), Event.class);
		} catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		if (event != null) {		
			repository.addEvent(event.getName(), event.getStart(), event.getFinish());
			event = repository.findOneEventByName(event.getName());
		} 
		response.getWriter().write(objectToJson(event));
	}
	

	public void deleteEventById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = 0;
		try {
			id = Integer.parseInt(getPathParameter(request.getPathInfo()));
		} catch(NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		Object o = null;
		if (id > 0) {
			o = repository.findOneEventById(id);
			if (o != null) {
				repository.deleteEventById(id);
			}
		}
		response.getWriter().write(objectToJson(o));
	}
		
	
	
}
