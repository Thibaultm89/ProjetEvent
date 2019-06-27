package be.afelio.software_academy.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.afelio.software_academy.repository.DataRepository;

public class FrontController  extends HttpServlet {
	
	private static final long serialVersionUID = -3198285139205951197L;
	
	protected PeopleController peopleController;
	protected ActivityController activityController;
	protected EventController eventController;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = config.getInitParameter("database.url");
			String user = config.getInitParameter("database.user");
			String password = config.getInitParameter("database.password");
			DataRepository repository = new DataRepository(url, user, password);
			peopleController = new PeopleController(repository);
			activityController = new ActivityController(repository);
			eventController = new EventController(repository);
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}
}
