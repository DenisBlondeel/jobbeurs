package domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.model.Spot;
import domain.model.User;

public class UserRepository {

	private Connection connection;
	private PreparedStatement statement;
	private Properties properties;

	public UserRepository(Properties properties) {
		this.setProperties(properties);
		try {
			Class.forName("org.postgresql.Driver");
			String url = getProperties().getProperty("url");
			connection = DriverManager.getConnection(url, getProperties());
			setProperties(properties);
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
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
	
/*	public List<Spot> getAll() {
	List<Spot> list = new ArrayList<Spot>();
	String sql = "Select * FROM jobfair_group4.users";
	try{
		statement = connection.prepareStatement(sql);
		ResultSet results = statement.executeQuery();
		while (results.next())
		{
			Spot spot = new Spot(results.getString("spotID"));
			spot.setAmountTables(results.getInt("amountTables"));
			spot.setAmountChairs(results.getInt("amountChairs"));
			spot.setElectricity(results.getBoolean("electricity"));
			spot.setRemarks(results.getString("remarks"));
			spot.setUserID(results.getString("userid"));
			list.add(spot);
		}
	} catch (SQLException e)
	{
		throw new DbException(e);
	}
	return list;
	}
	*/

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
				sql = "INSERT INTO jobfair_group4.users (userid, companyname, contactname, email, password, salt, role) "
						+ "Values (?, ?, ?, ?, ?, ?, ?)";
				try {
					statement = connection.prepareStatement(sql);

					statement.setString(1, user.getUserID());
					statement.setString(2, user.getCompanyName());
					statement.setString(3, user.getContactName());
					statement.setString(4, user.getEmail());
					statement.setString(5, user.getPassword());
					statement.setString(6, "hihisalt");
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

	public void delete(String userID) {
		if (userID == null) {
			throw new DbException("Nothing to delete.");
		}
		String sql = "DELETE FROM jobfair_group4.users WHERE userid = ?";
		try {
			statement = connection.prepareStatement(sql);

			statement.setString(1, userID);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	public User getUserIfAuthenticated(String userID, String password) {
		try {
			User user = get(userID);
			if (user.isCorrectPassword(password)) {
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
