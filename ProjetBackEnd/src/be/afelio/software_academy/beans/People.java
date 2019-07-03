package be.afelio.software_academy.beans;

import java.io.Serializable;

public class People implements Serializable  {
	
	private static final long serialVersionUID = 4574229571991243218L;
	
	protected Integer id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String password;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	@Override
	public String toString() {
		return "People [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
