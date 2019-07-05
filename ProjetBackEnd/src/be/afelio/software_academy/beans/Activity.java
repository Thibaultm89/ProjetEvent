package be.afelio.software_academy.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.afelio.software_academy.JsonDeserial;
import be.afelio.software_academy.JsonSerial;

public class Activity implements Serializable {

	private static final long serialVersionUID = 809180280680054447L;
	
	protected Integer id;
	protected String name;
	
	@JsonDeserialize(using = JsonDeserial.class)
	@JsonSerialize(using = JsonSerial.class)
	protected LocalDateTime start;
	
	@JsonSerialize(using = JsonSerial.class)
	@JsonDeserialize(using = JsonDeserial.class)
	protected LocalDateTime finish;
	
	protected People manager;
	protected Integer idEvent;
	protected String img;
	protected List<People> listPeople;


	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", start=" + start + ", finish=" + finish + ", manager="
				+ manager + ", idEvent=" + idEvent + ", img=" + img + ", listPeople=" + listPeople + "]";
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

	public People getManager() {
		return manager;
	}

	public void setManager(People manager) {
		this.manager = manager;
	}

	public Integer getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<People> getListPeople() {
		return listPeople;
	}

	public void setListPeople(List<People> listPeople) {
		this.listPeople = listPeople;
	}
	
	
}
