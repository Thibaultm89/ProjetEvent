package be.afelio.software_academy.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Event implements Serializable {
	
	private static final long serialVersionUID = -1172079574826305882L;
	
	protected Integer id;
	protected String name;
	protected LocalDate start;
	protected LocalDate finish;
	protected List<Activity> listActivity;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getFinish() {
		return finish;
	}
	public void setFinish(LocalDate finish) {
		this.finish = finish;
	}
	public List<Activity> getListActivity() {
		return listActivity;
	}
	public void setListActivity(List<Activity> listActivity) {
		this.listActivity = listActivity;
	}
	
	LocalDate date = LocalDate.parse("2018-05-05");
	

}
