package be.afelio.software_academy.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.afelio.software_academy.JsonDeserial;
import be.afelio.software_academy.JsonSerial;

public class Event implements Serializable {
	
	private static final long serialVersionUID = -1172079574826305882L;
	
	protected Integer id;
	protected String name;
	
	@JsonDeserialize(using = JsonDeserial.class)
	@JsonSerialize(using = JsonSerial.class)
	protected LocalDateTime start;
	
	@JsonSerialize(using = JsonSerial.class)
	@JsonDeserialize(using = JsonDeserial.class)
	protected LocalDateTime finish;
	
	protected String img;
	protected List<Activity> listActivities;
	
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", start=" + start + ", finish=" + finish + ", img=" + img
				+ ", listActivities=" + listActivities + "]";
	}

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
	
	public LocalDateTime getStart() {
		return start;
	}
	
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	
	public LocalDateTime getFinish() {
		return finish;
	}
	
	public void setFinish(LocalDateTime finish) {
		this.finish = finish;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public List<Activity> getListActivities() {
		return listActivities;
	}
	
	public void setListActivities(List<Activity> listActivities) {
		this.listActivities = listActivities;
	}

}
