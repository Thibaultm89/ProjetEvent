package be.afelio.software_academy.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.beans.People;
import be.afelio.software_academy.repository.DataRepository;

public class PeopleController extends BaseController {

	public PeopleController(DataRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
	}
	
	public void addPeople(HttpServletRequest request, HttpServletResponse response) throws IOException {
		People people = null;
		try {
			people = jsonStreamToObject(request.getInputStream(),People.class);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		if (people != null) {
			repository.addPeople(people.getFirstName(), people.getLastName(), people.getEmail(), people.getPassword());
			//TODO iddem ? people = repository.findOnePeopleByFirstNameAndLastName(people.getFirstName(), people.getLastName());
			//possib de récup id auto incrémenté de db
		}
		//TODO  ne sert à rien ? response.getWriter().write(objectToJson(people));
	}
}