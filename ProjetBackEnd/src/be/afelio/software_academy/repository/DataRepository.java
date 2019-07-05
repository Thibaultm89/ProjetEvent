package be.afelio.software_academy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import be.afelio.software_academy.beans.Activity;
import be.afelio.software_academy.beans.People;
import be.afelio.software_academy.beans.Event;

public class DataRepository {
	
	private String url;
	private String user;
	private String password;
	
	public DataRepository(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	protected Connection createConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public List<Event> findAllEvents() {
		List<Event> list = new ArrayList<Event>();
		String sql = "SELECT id_event AS EventId, name_event AS EventName, img_event AS ImgEvent,"
				+ " start_event AS EventStart, finish_event AS EventFinish FROM \"Event\"";
		try (
			Connection connection = createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql)
		) {
			while (resultSet.next()) {
				Event event = createEvent(resultSet);
				list.add(event);
			}
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return list;
	}
	
	protected Event createEvent(ResultSet rs) throws SQLException {
		int id = rs.getInt("EventId");
		String name = rs.getString("EventName");
		Timestamp start = rs.getTimestamp("EventStart");
		Timestamp finish = rs.getTimestamp("EventFinish");
		String img = rs.getString("Imgevent");
		Event e = new Event();
		e.setId(id);
		e.setName(name);
		e.setStart(start.toLocalDateTime());
		e.setFinish(finish.toLocalDateTime());
		e.setImg(img);
		return e;
	}
	
	public List<Activity> findActivitiesByEventId(int i) {
		List<Activity> list = new ArrayList<Activity>();
		String sql = "SELECT id_activity AS ActivityId, name_activity AS ActivityName, start_activity AS ActivityStart, "
				+ "finish_activity AS ActivityFinish, id_event AS EventId, img_activity AS ImgActivity "
				+ "FROM \"Activity\""
				+ "WHERE id_event= "+ i;
		try (
			Connection connection = createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql)
		) {
			while (resultSet.next()) {
				Activity act = createActivity(resultSet);
				list.add(act);
			}
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return list;
	}
	
	protected Activity createActivity(ResultSet rs) throws SQLException {
		int idA = rs.getInt("ActivityId");
		String name = rs.getString("ActivityName");
		Timestamp start = rs.getTimestamp("ActivityStart");
		Timestamp finish = rs.getTimestamp("ActivityFinish");
		int idE = rs.getInt("EventId");
		String img = rs.getString("ImgActivity");
		Activity a = new Activity();
		a.setId(idA);
		a.setName(name);
		a.setStart(start.toLocalDateTime());
		a.setFinish(finish.toLocalDateTime());
		a.setIdEvent(idE);
		a.setImg(img);
		return a;
	}

	public void addEvent(String name, LocalDateTime start, LocalDateTime finish) { 
		if (name != null && !name.isBlank() && findOneEventByName(name) == null) {
			String sql = "INSERT INTO \"Event\" (name_event, start_event, finish_event) values(?,?,?)";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				connection.setAutoCommit(true);
				statement.setString(1, name);
				statement.setObject(2,start);
				statement.setObject(3,finish);
				statement.executeUpdate();
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
	}
	
	public Event findOneEventByName(String name) {
		Event event = null;
		if (name != null && !name.isBlank()) {
			String sql = "SELECT id_event AS EventID, name_event as EventName, start_event AS EventStart, finish_event AS EventFinish FROM \"Event\" where name_event = ?";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				statement.setString(1, name);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						event = createEvent(resultSet);
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
		return event;
	}
	
	public Event findOneEventById(Integer id) {
		Event event = null;
		
			String sql = "SELECT id_event AS EventID, name_event as EventName, "
					+ "start_event AS EventStart, finish_event AS EventFinish, img_event AS ImgEvent FROM \"Event\" "
					+ "WHERE id_event = ?";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				statement.setInt(1, id);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						event = createEvent(resultSet);
						event.setListActivities(findActivitiesByEventId(id));
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		
		return event;
	}

	public Activity findOneActivityByName(String name) {
		Activity activity = null;
		if (name != null && !name.isBlank()) {
			String sql = "SELECT id_activity AS ActivityId, name_activity as ActivityName, start_activity AS ActivityStart,"
					+ " finish_activity AS ActivityFinish, id_event AS EventId FROM \"Activity\" where name_activity = ?";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				statement.setString(1, name);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						activity = createActivity(resultSet);
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
		return activity;
	}
	
	public Activity findOneActivityById(Integer id) {
		Activity activity = null;
		String sql = "SELECT id_activity AS ActivityId, name_activity as ActivityName, start_activity AS ActivityStart,"
					+ " finish_activity AS ActivityFinish, id_event AS EventId, img_activity AS ImgActivity "
					+ "FROM \"Activity\" where id_activity = ?";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				statement.setInt(1, id);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						activity = createActivity(resultSet);
						activity.setListPeople(findPeopleByActivityId(id));
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		
		return activity;
	}

	
	public List<People> findPeopleByActivityId(Integer id) {
		List<People> list = new ArrayList<People>();
		String sql = "SELECT p.id_people AS PeopleId, firstname_people AS FirstNamePeople, lastname_people AS LastNamePeople, "
				+ "email AS EmailPeople, password AS PasswordPeople "
				+ "FROM \"People\" p "
				+ "JOIN \"Activity_people\" ap ON p.id_people = ap.id_people "
				+ "JOIN \"Activity\" a ON a.id_activity = ap.id_activity "
				+ "WHERE a.id_activity =" + id;
		try (
			Connection connection = createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql)
		) {
			while (resultSet.next()) {
				People p = createPeople(resultSet);
				list.add(p);
			}
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return list;
	}
	
	
	private People createPeople(ResultSet rs) throws SQLException {
		int id = rs.getInt("PeopleId");
		String firstname = rs.getString("FirstNamePeople");
		String lastname = rs.getString("LastNamePeople");
		String email = rs.getString("EmailPeople");
		String pwd = rs.getString("PasswordPeople");
		People p = new People();
		p.setId(id);
		p.setFirstName(firstname);
		p.setLastName(lastname);
		p.setEmail(email);
		p.setPassword(pwd);
		return p;
	}

	public void addActivity(String name, LocalDateTime start, LocalDateTime finish) {
		if (name != null && !name.isBlank() && findOneActivityByName(name) == null) {
			String sql = "INSERT INTO \"Activity\" (name_activity, start_activity, finish_activity, manager, id_event) values(?,?,?,1,2)";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				connection.setAutoCommit(true);
				statement.setString(1, name);
				statement.setObject(2,start);
				statement.setObject(3,finish);
				statement.executeUpdate();
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
	}

	public String findEventImgById(Integer id) {
		String eventImg = "";
		String sql = "SELECT img_event AS EventImg FROM \"Event\" where id_event = ?";
		try (
			Connection connection = createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		) {
			statement.setInt(1, id);
			try (
				ResultSet resultSet = statement.executeQuery()
			) {
				if (resultSet.next()) {
					eventImg = resultSet.getString("EventImg");
				}
			}
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}

	return eventImg;
}

}