package be.afelio.software_academy.beans;

import java.io.Serializable;

public class MyLogin implements Serializable{
	
	private static final long serialVersionUID = -3575249572120379435L;
	
	private String email;
	private String password;

	public String getEmail() {
		return this.email;
	}


	public String getPassword() {
		return this.password;
	}
}
