package be.afelio.software_academy.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Activity implements Serializable {

	private static final long serialVersionUID = 809180280680054447L;
	
	protected Integer id;
	protected String name;
	protected LocalDateTime start;
	protected LocalDateTime finish;
	protected People manager;
	protected Integer idEvent;
	protected List<People> listPeople;
	
	
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
	public People getManager() {
		return manager;
	}
	public void setManager(People manager) {
		this.manager = manager;
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
	public Integer getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}
	public List<People> getListPeople() {
		return listPeople;
	}
	public void setListPeople(List<People> listPeople) {
		this.listPeople = listPeople;
	}
	
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", manager=" + manager + ", start=" + start + ", finish="
				+ finish + ", listPeople=" + listPeople + "]";
	}
}
