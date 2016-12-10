package domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.management.relation.Role;

import domain.model.RoleEnum;
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
			user.setCompanyNameFromDb(results.getString("companyName"));
			user.setContactNameFromDb(results.getString("contactName"));
			user.setEmail(results.getString("email"));
			user.setPassword(results.getString("password"));
			user.setRole(results.getString("role"));
			user.setSalt(results.getString("salt"));
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return user;
	}
	
	public List<User> getAllCompanies() {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM jobfair_group4.users ORDER BY companyname";
		try{
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				User user = new User();
				user.setCompanyNameFromDb(results.getString("companyname"));
				user.setContactNameFromDb(results.getString("contactname"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
				user.setRole(results.getString("role"));
				user.setSalt(results.getString("salt"));
				user.setUserID(results.getString("userid"));
				
				if(user.getRole()==RoleEnum.COMPANY) {
					list.add(user);
				}
			}
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
		return list;
	}

	public List<User> getAdmins() {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM jobfair_group4.users WHERE role='ADMIN';";
		try {
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				User user = new User();
				user.setUserID(results.getString("userid"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
				user.setRole(results.getString("role"));
				user.setSalt(results.getString("salt"));
				list.add(user);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return list;
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
				sql = "INSERT INTO jobfair_group4.users (userid, companyname, contactname, email, password, salt, role) "
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
	
	public void deleteAll()
	{
		String sql = "DELETE FROM jobfair_group4.users where role='COMPANY'";

		try {
			statement = connection.prepareStatement(sql);
			statement.execute();
	}	catch(SQLException e)
		{
		throw new DbException(e.getMessage(), e);
		}
	}

	public List<String> getAllAdminEmails() {
		List<String> list = new ArrayList<String>();
		String sql = "SELECT email FROM jobfair_group4.users WHERE role='ADMIN'";
		try{
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				list.add(results.getString("email"));
			}
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
		return list;
	}

	public List<String> getUserIDsWithoutSpot() {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> result = new ArrayList<String>();

		String sql1 = "SELECT userID FROM jobfair_group4.users WHERE role='COMPANY'";
		String sql2 = "SELECT u.userID FROM jobfair_group4.users u INNER JOIN jobfair_group4.spots s ON u.userid=s.userid WHERE u.role='COMPANY'";

		try{
			statement = connection.prepareStatement(sql1);
			ResultSet results1 = statement.executeQuery();
			while (results1.next())
			{
				list1.add(results1.getString("userID"));
			}

			statement = connection.prepareStatement(sql2);
			ResultSet results2 = statement.executeQuery();
			while (results2.next())
			{
				list2.add(results2.getString("userID"));
			}

			for (String id : list1) {
				if (!list2.contains(id)) {
					result.add(id);
				}
			}
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
		return result;
	}

	public List<String> getEmailFromUsersWithoutSpot() {
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> result = new ArrayList<String>();
		String sql1 = "SELECT email FROM jobfair_group4.users WHERE role='COMPANY'";
		String sql2 = "SELECT email FROM jobfair_group4.users u INNER JOIN jobfair_group4.spots s ON u.userid=s.userid WHERE u.role='COMPANY'";
		try{
			statement = connection.prepareStatement(sql1);
			ResultSet results1 = statement.executeQuery();
			while (results1.next())
			{
				list1.add(results1.getString("email"));
			}

			statement = connection.prepareStatement(sql2);
			ResultSet results2 = statement.executeQuery();
			while (results2.next())
			{
				list2.add(results2.getString("email"));
			}

			for (String email : list1) {
				if (!list2.contains(email)) {
					result.add(email);
				}
			}
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
		return result;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
