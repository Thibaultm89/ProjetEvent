package be.afelio.software_academy.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import be.afelio.software_academy.repository.DataRepository;

@WebServlet("/json/*")
public class FrontController  extends HttpServlet {
	
	private static final long serialVersionUID = -3198285139205951197L;
	
	protected PeopleController peopleController;
	protected ActivityController activityController;
	protected EventController eventController;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("org.postgresql.Driver");
			String path = getServletContext().getRealPath("/WEB-INF/conf/database.properties");
			Properties properties = new Properties();
			try (
				InputStream in = new FileInputStream(path)
			) {
				properties.load(in);
			}
			String url = properties.getProperty("database.url");
			String user = properties.getProperty("database.user");
			String password = properties.getProperty("database.password");
			
			DataRepository repository = new DataRepository(url, user, password);
			ObjectMapper mapper = new ObjectMapper();
			
			peopleController = new PeopleController(repository, mapper);
			activityController = new ActivityController(repository, mapper);
			eventController = new EventController(repository, mapper);
			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			response.setContentType("application/json");
			setHeaders(response);
			
			if (pathInfo.startsWith("/event/all")) {
				eventController.findAllEvents(request, response);
				
			} else if (pathInfo.startsWith("/event/")){
				eventController.findOneEventById(request, response);
				
			} else if (pathInfo.startsWith("/activity/all")){
				activityController.findAllActivity(request, response);
				
			} else if (pathInfo.startsWith("/activity/")){
				activityController.findOneActivitysById(request, response);
				
			} else if (pathInfo.startsWith("/people/")){
				peopleController.findOnePeopleByEmail(request, response);
							
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			response.setContentType("application/json");
			setHeaders(response);
			switch (pathInfo) {
			case "/create-event/":
				eventController.addEvent(request, response);
				break;
			case "/create-activity/":
				activityController.addActivity(request,response);
				break;
			case "/create-people/":
				peopleController.addPeople(request,response);
				break;	
			case "/login/":
				peopleController.findOnePeopleByEmailAndPassword(request, response);
				break;
			case "/logout/":
				request.getSession().invalidate();
				break;
			case "/register-people/":
				peopleController.findOnePeopleById(request,response);
				break;
			default:
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}			
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String pathInfo = request.getPathInfo();
		System.out.println("FrontController.doDelete()");
		if (pathInfo != null) {
			setHeaders(response);
			response.setContentType("application/json");
			if (pathInfo.startsWith("/delete-event/")) {
				eventController.deleteEventById(request, response);
			} else if (pathInfo.startsWith("/delete-activity/")) {
				activityController.deleteActivityById(request, response);
			} else if (pathInfo.startsWith("/delete-people/")) {
				peopleController.deletePeopleById(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}			
		} else {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doOptions(request, response);
		setHeaders(response);
	}
	
	private void setHeaders( HttpServletResponse response ) {
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.addHeader("Access-Control-Allow-Methods", "*");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Headers", "content-type");
	}


}

