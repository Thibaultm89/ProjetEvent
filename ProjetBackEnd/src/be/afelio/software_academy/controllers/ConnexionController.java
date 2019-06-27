package be.afelio.software_academy.controllers;

import be.afelio.software_academy.repository.DataRepository;

public class ConnexionController {

	protected DataRepository repository;

	public ConnexionController(DataRepository repository) {
		super();
		this.repository = repository;
	}
}
