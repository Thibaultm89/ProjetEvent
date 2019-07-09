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

	protected People createPeople(ResultSet rs) throws SQLException {
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
	
	protected People createPeopleWithoutPassword(ResultSet rs) throws SQLException {
		int id = rs.getInt("PeopleId");
		String firstname = rs.getString("FirstNamePeople");
		String lastname = rs.getString("LastNamePeople");
		String email = rs.getString("EmailPeople");
		People p = new People();
		p.setId(id);
		p.setFirstName(firstname);
		p.setLastName(lastname);
		p.setEmail(email);
		return p;
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
	
	public List<Activity> findAllActivity() {
		List<Activity> list = new ArrayList<Activity>();
		String sql ="SELECT id_activity AS ActivityId, name_activity AS ActivityName, img_activity AS ImgActivity,"
				+ " start_activity AS ActivityStart, finish_activity AS ActivityFinish, id_event AS EventId FROM \"Activity\"";
		try (
			Connection connection = createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql)
		) {
			while (resultSet.next()) {
				Activity activity = createActivity(resultSet);
				list.add(activity);
			}
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
		return list;
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


	
	
	public Event findOneEventByName(String name) {
		Event event = null;
		if (name != null && !name.isBlank()) {
			String sql = "SELECT id_event AS EventID, name_event as EventName, start_event AS EventStart, finish_event AS EventFinish, img_event as ImgEvent FROM \"Event\" where name_event = ?";
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
					+ " finish_activity AS ActivityFinish, id_event AS EventId, img_activity AS ImgActivity FROM \"Activity\" where name_activity = ?";
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
	
	public People findOnePeopleById(Integer id) {
		People people = null;
		String sql = "SELECT id_people AS PeopleId, firstname_people as FirstNamePeople, lastname_people AS LastNamePeople,"
				+ " email AS EmailPeople "
				+ "FROM \"People\" where id_people = ?";
		try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				statement.setInt(1, id);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						people = createPeopleWithoutPassword(resultSet);
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		return people;
	}
	
	public People findOnePeopleByFirstNameAndLastName(String firstName, String lastName) {
		People people = null;
		//if (name != null && !name.isBlank()) {
			String sql = "SELECT id_people AS PeopleId, firstname_people as FirstNamePeople, lastname_people AS LastNamePeople,"
					+ " email AS EmailPeople, password AS PasswordPeople "
					+ "FROM \"People\" where firstname_people = ? AND lastname_people = ?";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				statement.setString(1, firstName);
				statement.setString(2, lastName);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						people = createPeople(resultSet);
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		//}	
		return people;
	}

	public People findOnePeopleByEmail(String email) {
		People people = new People();
		if (email != null && !email.isBlank()) {
			String sql = "SELECT id_people AS PeopleId, firstname_people AS FirstNamePeople, lastname_people AS LastNamePeople,"
					+ " email AS EmailPeople, password AS PasswordPeople FROM \"People\" WHERE email = ?";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql)
			) {
				statement.setString(1, email);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						people = createPeople(resultSet);
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
		return people;
	}
	
	public People findOnePeopleByEmailAndPassword(String email, String password) {
		People people = null;
		if (email != null && !email.isBlank()) {
			String sql = "SELECT id_people AS PeopleId, firstname_people AS FirstNamePeople, lastname_people AS LastNamePeople,"
					+ " email AS EmailPeople FROM \"People\" WHERE email = ? AND password = ?";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql)
			) {
				System.out.println("DataRepository.findOnePeopleByEmailAndPassword()");
				statement.setString(1, email);
				statement.setString(2, password);
				try (
					ResultSet resultSet = statement.executeQuery()
				) {
					if (resultSet.next()) {
						people = createPeopleWithoutPassword(resultSet);
					}
				}
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
		return people;
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

	public void addActivity(String name, LocalDateTime start, LocalDateTime finish, int idEvent) {
		if (name != null && !name.isBlank() && findOneActivityByName(name) == null) {
			String sql = "INSERT INTO \"Activity\" (name_activity, start_activity, finish_activity, manager, id_event) values(?,?,?,1,?)";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				connection.setAutoCommit(true);
				statement.setString(1, name);
				statement.setObject(2,start);
				statement.setObject(3,finish);
				statement.setInt(4, idEvent);
				statement.executeUpdate();
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
	}	

	public void addPeople(String firstName, String lastName, String email, String pwd) {
		if (firstName != null && !firstName.isBlank()) { //&& findOnePeopleByFirstName(name) == null) {
			String sql = "INSERT INTO \"People\" (firstname_people, lastname_people, email, password) values(?,?,?,?)";
			try (
				Connection connection = createConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
			) {
				connection.setAutoCommit(true);
				statement.setString(1, firstName);
				statement.setString(2,lastName);
				statement.setString(3,email);
				statement.setString(4, pwd);
				statement.executeUpdate();
			} catch(SQLException sqle) {
				throw new RuntimeException(sqle);
			}
		}
	}	
	
	public void addActivityPeople(int idActivity, int idPeople) {
		String sql = "INSERT INTO \"Activity_people\" (id_activity, id_people) values(?,?	)";
		try (
			Connection connection = createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		) {
			connection.setAutoCommit(true);
			statement.setInt(1, idActivity);
			statement.setInt(2,idPeople);
			statement.executeUpdate();
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}
	
	public void deleteEventById(int idEvent) {
		String sql = "DELETE FROM \"Event\" where id_event = ?";
		try (
			Connection connection = createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		) {
			connection.setAutoCommit(true);
			statement.setInt(1, idEvent);
			statement.executeUpdate();
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}
	
	public void deleteActivityById(int idActivity) {
		String sql = "DELETE FROM \"Activity\" where id_activity = ?";
		try (
			Connection connection = createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		) {
			connection.setAutoCommit(true);
			statement.setInt(1, idActivity);
			statement.executeUpdate();
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}
	
	public void deletePeopleById(int idPeople) {
		String sql = "DELETE FROM \"People\" where id_people = ?";
		try (
			Connection connection = createConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		) {
			connection.setAutoCommit(true);
			statement.setInt(1, idPeople);
			statement.executeUpdate();
		} catch(SQLException sqle) {
			throw new RuntimeException(sqle);
		}
	}
}