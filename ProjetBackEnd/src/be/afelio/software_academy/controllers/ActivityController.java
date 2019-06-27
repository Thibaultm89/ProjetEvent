package be.afelio.software_academy.controllers;

import be.afelio.software_academy.repository.DataRepository;

public class ActivityController {
	
	protected DataRepository repository;

	public ActivityController(DataRepository repository) {
		super();
		this.repository = repository;
	}
}
