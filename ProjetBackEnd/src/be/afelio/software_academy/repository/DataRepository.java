package be.afelio.software_academy.repository;

import java.util.List;

import be.afelio.software_academy.beans.People;

public class DataRepository {
	
	private String url;
	private String user;
	private String password;
	
	public DataRepository(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public List<People> findAllPeople() {
		
		return null;
	}
}
