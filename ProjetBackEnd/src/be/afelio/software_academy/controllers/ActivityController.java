package be.afelio.software_academy.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.beans.Activity;
import be.afelio.software_academy.repository.DataRepository;

public class ActivityController extends BaseController{
	
	public ActivityController(DataRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
	}
	
	public void findOneActivitysById(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getPathInfo().split("/")[2]);
		Object o = repository.findOneActivityById(id);
		response.getWriter().write(objectToJson(o));
	}
	
	public void addActivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Activity activity = null;
		try {
			activity = jsonStreamToObject(request.getInputStream(), Activity.class);
		} catch(Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		if (activity != null) {		
			repository.addActivity(activity.getName(), activity.getStart(), activity.getFinish());
			activity = repository.findOneActivityByName(activity.getName());
		} 
		response.getWriter().write(objectToJson(activity));
	}
	
}
