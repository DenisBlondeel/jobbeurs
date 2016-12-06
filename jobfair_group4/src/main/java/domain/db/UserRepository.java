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
		String sql = "SELECT * FROM jobfair_group4.users WHERE userID = ?;";
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

	public void add(User user) {
		if (user == null) {
			throw new DbException("Niets om toe te voegen.");
		}
		String sql = "SELECT * FROM jobfair_group4.users WHERE userID = ?";
		try {
			statement = connection.prepareStatement(sql);

			statement.setString(1, user.getUserID());
			
			ResultSet result = statement.executeQuery();

			if (result.next() && result.getString("userId").equals(user.getUserID())) {
				throw new IllegalArgumentException("User bestaat al");
			} else {
				sql = "INSERT INTO jobfair_group4.users (userID, companyName, contactName, email, password, salt, role) "
						+ "Values (?, ?, ?, ?, ?, ?, ?)";
				try {
					statement = connection.prepareStatement(sql);

					statement.setString(1, user.getUserID());
					statement.setString(2, user.getCompanyName());
					statement.setString(3, user.getContactName());
					statement.setString(4, user.getEmail());
					statement.setString(5, user.getPassword());
					statement.setString(6, user.getSalt());
					statement.setString(7, user.getRole().toString());

					statement.executeUpdate();
				} catch (SQLException e) {
					throw new DbException(e.getMessage(), e);
				}
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
