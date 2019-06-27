package be.afelio.software_academy.controllers;

import be.afelio.software_academy.repository.DataRepository;

public class PeopleController {

	protected DataRepository repository;

	public PeopleController(DataRepository repository) {
		super();
		this.repository = repository;
	}
}
