package be.afelio.software_academy.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.beans.MyLogin;
import be.afelio.software_academy.beans.People;
import be.afelio.software_academy.repository.DataRepository;

public class PeopleController extends BaseController {

	public PeopleController(DataRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
	}
	
	public void addPeople(HttpServletRequest request, HttpServletResponse response) throws IOException {
		People people = null;
		int idActivity;
		try {
			people = jsonStreamToObject(request.getInputStream(),People.class);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		if (people != null) {
			repository.addPeople(people.getFirstName(), people.getLastName(), people.getEmail(), people.getPassword());
			idActivity = people.getIdActivity();
			System.out.println("idActivity vaut: " + idActivity);
			people = repository.findOnePeopleByFirstNameAndLastName(people.getFirstName(), people.getLastName()); 
			repository.addActivityPeople(idActivity, people.getId());
		}
		response.getWriter().write(objectToJson(people));
	}


	public void findOnePeopleByEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = getPathParameter(request.getPathInfo());
		People p= repository.findOnePeopleByEmail(email);
		response.getWriter().write(objectToJson(p));	
	}
	
	public void findOnePeopleByEmailAndPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MyLogin myLogin = jsonStreamToObject(request.getInputStream(), MyLogin.class);
		People p = repository.findOnePeopleByEmailAndPassword(myLogin.getEmail(), myLogin.getPassword());
		if (p != null) { 
			int id = p.getId();
			request.getSession().setAttribute("idUser", id);
		}
		System.out.println(objectToJson(p));
		response.getWriter().write(objectToJson(p));
	}
	
	public void findOnePeopleById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer idActivity;
		People p = jsonStreamToObject(request.getInputStream(), People.class);
		idActivity = p.getIdActivity();
		p = repository.findOnePeopleById(p.getId());
		p.setIdActivity(idActivity);
		repository.addActivityPeople(p.getIdActivity(), p.getId());
		response.getWriter().write(objectToJson(p));	
	}
	
	public void deletePeopleById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = 0;
		try {
			id = Integer.parseInt(getPathParameter(request.getPathInfo()));
		} catch(NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		Object o = null;
		if (id > 0) {
			o = repository.findOnePeopleById(id);
			if (o != null) {
				repository.deletePeopleById(id);
			}
		}
		response.getWriter().write(objectToJson(o));
	}
}

	