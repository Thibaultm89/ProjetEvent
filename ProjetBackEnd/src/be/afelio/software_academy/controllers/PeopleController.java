package be.afelio.software_academy.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.afelio.software_academy.repository.DataRepository;

public class PeopleController extends BaseController {

	public PeopleController(DataRepository repository, ObjectMapper mapper) {
		super(repository, mapper);
	}
}