package be.afelio.software_academy.controllers;

import be.afelio.software_academy.repository.DataRepository;

public class EventController {
	
	protected DataRepository repository;

	public EventController(DataRepository repository) {
		super();
		this.repository = repository;
	}
}
