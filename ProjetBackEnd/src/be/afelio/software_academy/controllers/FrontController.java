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
			response.addHeader("Access-Control-Allow-Origin", "*");
			if (pathInfo.startsWith("/activity/all")) {
				activityController.findAllActivities(request, response);
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
		
	}
}
