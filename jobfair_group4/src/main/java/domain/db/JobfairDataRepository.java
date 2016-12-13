package domain.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

public class JobfairDataRepository {

	private PreparedStatement statement;
	private Connection connection;
	private Properties properties;

	public JobfairDataRepository(Properties properties) {
		setProperties(properties);
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = properties.getProperty("url");
			connection = DriverManager.getConnection(url, getProperties());
		} catch (Exception e)
		{
			throw new DbException(e.getMessage(), e);
		}
	}

	public Calendar getDeadline(String name) {
		if (name == null || name.isEmpty()) {
			return null;
		}

		Calendar calendar = null;
		String sql = "SELECT * FROM jobfair_group4.jobfairdata WHERE naam = ?;";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			if(results.next());{
				calendar = Calendar.getInstance();
				calendar.setTime(results.getDate("deadline"));
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return calendar;
	}
	

	public void setDeadline(String name, Calendar deadline) {
		if (name == null || name.isEmpty()) {
			return;
		}
		if (deadline == null) {
			return;
		}

		String sql = "UPDATE jobfair_group4.jobfairdata SET deadline = ? WHERE naam = ?;";
		try {
			statement = connection.prepareStatement(sql);
			statement.setDate(1, new Date(deadline.getTimeInMillis()));
			statement.setString(2, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}

	public void close()
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
	}
}
