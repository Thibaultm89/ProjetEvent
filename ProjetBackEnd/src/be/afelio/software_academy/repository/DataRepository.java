package be.afelio.software_academy.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public List<Activity> findAllActivities() {
		List<Activity>list = new ArrayList<Activity>();
		String sql = "SELECT id_activity AS ActivityId, name_activity AS ActivityName FROM Activity";
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
	
	protected Activity createActivity(ResultSet rs) throws SQLException {
		int id = rs.getInt("ActivityId");
		String name = rs.getString("ActivityName");
		Activity a = new Activity();
		a.setId(id);
		a.setName(name);
		return a;
	}
}
