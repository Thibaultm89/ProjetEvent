package be.afelio.software_academy.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
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

	public List<People> findAllPeople() {
		List<People>list = new ArrayList<People>();
		String sql = "";
		return list;
	}
	
	public List<Event> findAllEvents() {
		List<Event> list = new ArrayList<Event>();
		String sql = "SELECT id_event AS EventId, name_event AS EventName,"
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
	//	String start = rs.getString("EventStart");
	//	String finish = rs.getString("EventFinish");
		Timestamp start = rs.getTimestamp("EventStart");
		Timestamp finish = rs.getTimestamp("EventFinish");
		Event e = new Event();
		e.setId(id);
		e.setName(name);
		//e.setStart(LocalDateTime.parse(start));
		//e.setFinish(LocalDateTime.parse(finish));
		e.setStart(start.toLocalDateTime());
		e.setFinish(finish.toLocalDateTime());
		return e;
	}
	
	public List<Activity> findActivitiesByEventId(int i) {
		List<Activity> list = new ArrayList<Activity>();
		String sql = "SELECT id_activity AS ActivityId, name_activity AS ActivityName, start_activity AS ActivityStart, finish_activity AS ActivityFinish, id_event AS EventId "
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
		Activity a = new Activity();
		a.setId(idA);
		a.setName(name);
		a.setStart(start.toLocalDateTime());
		a.setFinish(finish.toLocalDateTime());
		a.setIdEvent(idE);
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

}
