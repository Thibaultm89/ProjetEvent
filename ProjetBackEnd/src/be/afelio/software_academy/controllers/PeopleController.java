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
			System.out.println("le nom est"+people.getFirstName()+people.getLastName());
			System.out.println("idActivity vaut: " + idActivity);
			people = repository.findOnePeopleByFirstNameAndLastName(people.getFirstName(), people.getLastName()); // après cette ligne perte de idActivity, faut il le conversr dans people?
			//récup id auto incrémenté de db
			// après avoir récup id people , ajout dans activity_people aveec id_acvitivy récup de angular
			repository.addActivityPeople(idActivity, people.getId());
			//je pense que activiy_id sera perdu car on écrase people, il faudrait peut etre la récup dans un variable avant *
		}
		response.getWriter().write(objectToJson(people));
	}


	public void findOnePeopleByEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String email = getPathParameter(request.getPathInfo());
		Object o = repository.findOnePeopleByEmail(email);
		response.getWriter().write(objectToJson(o));	
	}
	
	public void findOnePeopleByEmailAndPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MyLogin myLogin = jsonStreamToObject(request.getInputStream(), MyLogin.class);
		People p = repository.findOnePeopleByEmailAndPassword(myLogin.getEmail(), myLogin.getPassword());
		if (p != null) { 
			int id = p.getId();
			request.getSession().setAttribute("idUser", id);
		}
		
		response.getWriter().write(objectToJson(p));
	}
}

	