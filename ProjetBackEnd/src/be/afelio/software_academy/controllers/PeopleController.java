package be.afelio.software_academy.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.repository.DataRepository;

public class PeopleController extends BaseController {

	protected DataRepository repository;

	public PeopleController(DataRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
	}
	public void findAllPeople(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object o = repository.findAllActivities();
		response.getWriter().write(objectToJson(o));
		
	}
}