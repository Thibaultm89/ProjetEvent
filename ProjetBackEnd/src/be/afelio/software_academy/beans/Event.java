package be.afelio.software_academy.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {
	
	private static final long serialVersionUID = -1172079574826305882L;
	
	protected Integer id;
	protected String name;
	protected Date start;
	protected Date finish;
	protected List<Activity> listActivity;

}
