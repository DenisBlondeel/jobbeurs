package domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import domain.model.Spot;
import domain.model.User;

public class UserRepository {

	private PreparedStatement statement;
	private Connection connection;
	private Properties properties;

	public UserRepository(Properties properties) {
		try {
			Class.forName("org.postgresql.Driver");
			String url = properties.getProperty("url");
			connection = DriverManager.getConnection(url, properties);
			setProperties(properties);
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}

	public User get(String userID) {
		if (userID.isEmpty()) {
			throw new DbException("Niets te vinden !");
		}
		String sql = "SELECT * " + "FROM jobfair_group4.spots" + " WHERE spotID = ?;";
		User user = new User();
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, userID);
			ResultSet results = statement.executeQuery();
			results.next();
			user.setUserID(userID);
			user.setCompanyName(results.getString("companyName"));
			user.setContactName(results.getString("contactName"));
			user.setEmail(results.getString("email"));
			user.setPassword(results.getString("password"));
			user.setRole(results.getString("role"));
			user.setSalt(results.getString("salt"));
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return user;

	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
