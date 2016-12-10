package domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.model.EmailSender;
import domain.model.Spot;
import domain.model.User;

public class SpotRepository {

	private PreparedStatement statement;
	private Connection connection;
	private Properties properties;
	private EmailSender emailsender;

	public SpotRepository(Properties properties) {
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
			throw new DbException(e);
		}
	}
	
	public Spot get(String spotID) {
		if (spotID.isEmpty()) {
			throw new DbException("Niets te vinden !");
		}
		String sql = "SELECT * FROM jobfair_group4.spots WHERE spotID = ?;";
		Spot spot = new Spot(spotID);
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, spotID);


			ResultSet results = statement.executeQuery();


			if(results.next()){
				spot.setAmountTables(results.getInt("amountTables"));
				spot.setAmountChairs(results.getInt("amountChairs"));
				spot.setElectricity(results.getBoolean("electricity"));
				spot.setRemarks(results.getString("remarks"));
				spot.setUserID(results.getString("userid"));
			}


		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

		return spot;
	}
	
	
	public List<Spot> getAll() {
	List<Spot> list = new ArrayList<Spot>();
	String sql = "SELECT * FROM jobfair_group4.users u RIGHT OUTER JOIN jobfair_group4.spots s ON s.userid = u.userid ORDER BY spotid";
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
			
			if(spot.getUserID()!=null) {
				User user = new User();
				user.setCompanyNameFromDb(results.getString("companyname"));
				user.setContactNameFromDb(results.getString("contactname"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
				user.setRole(results.getString("role"));
				user.setSalt(results.getString("salt"));
				user.setUserID(results.getString("userid"));
			
				spot.setUser(user);
			}
			
			list.add(spot);
		}
	} catch (SQLException e)
	{
		throw new DbException(e);
	}
	return list;
	}

	public List<Spot> getFreeSpots() {
		List<Spot> list = new ArrayList<Spot>();
		String sql = "SELECT *" + " FROM jobfair_group4.spots WHERE userID IS NULL ORDER BY spotID";
		try
		{
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
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
	
	public List<Spot> getOccupiedSpots() {
		List<Spot> list = new ArrayList<Spot>();
		String sql = "SELECT * FROM jobfair_group4.users u INNER JOIN jobfair_group4.spots s ON s.userid = u.userid ORDER BY spotid";
		try {
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				Spot spot = new Spot(results.getString("spotID"));
				spot.setAmountTables(results.getInt("amountTables"));
				spot.setAmountChairs(results.getInt("amountChairs"));
				spot.setElectricity(results.getBoolean("electricity"));
				spot.setRemarks(results.getString("remarks"));
				spot.setUserID(results.getString("userid"));
				
				User user = new User();
				user.setCompanyNameFromDb(results.getString("companyname"));
				user.setContactNameFromDb(results.getString("contactname"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
				user.setRole(results.getString("role"));
				user.setSalt(results.getString("salt"));
				user.setUserID(results.getString("userid"));
				
				spot.setUser(user);				
				
				list.add(spot);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
		return list;
	}

	public List<Spot> getAlphabeticOccupiedSpots() {
		List<Spot> list = new ArrayList<Spot>();
		String sql = "SELECT * FROM jobfair_group4.users u INNER JOIN jobfair_group4.spots s ON s.userid = u.userid ORDER BY companyname";
		try {
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				Spot spot = new Spot(results.getString("spotID"));
				spot.setAmountTables(results.getInt("amountTables"));
				spot.setAmountChairs(results.getInt("amountChairs"));
				spot.setElectricity(results.getBoolean("electricity"));
				spot.setRemarks(results.getString("remarks"));
				spot.setUserID(results.getString("userid"));
				
				User user = new User();
				user.setCompanyNameFromDb(results.getString("companyname"));
				user.setContactNameFromDb(results.getString("contactname"));
				user.setEmail(results.getString("email"));
				user.setPassword(results.getString("password"));
				user.setRole(results.getString("role"));
				user.setSalt(results.getString("salt"));
				user.setUserID(results.getString("userid"));
				 
				spot.setUser(user);				
				
				list.add(spot);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
		return list;
	}

	public Spot getFromUser(String userID)
	{
		if (userID.isEmpty()) {
			throw new DbException("Niets te vinden !");
		}
		String sql = "SELECT * FROM jobfair_group4.spots WHERE userid = ?";
		
		Spot spot = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, userID);


			ResultSet results = statement.executeQuery();


			if(results.next()){
				spot = new Spot();
				spot.setSpotID(results.getString("spotid"));
				spot.setAmountTables(results.getInt("amountTables"));
				spot.setAmountChairs(results.getInt("amountChairs"));
				spot.setElectricity(results.getBoolean("electricity"));
				spot.setRemarks(results.getString("remarks"));
				spot.setUserID(results.getString("userid"));
			}


		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
		return spot;
	}
		

	public void addUserToSpot(String spotID, User user) {
		if (spotID.isEmpty() || user == null) {
			throw new DbException("Niets te vinden !");
		}
		String sql = "UPDATE jobfair_group4.spots SET userID = ? WHERE spotID = ?";
		
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getContactName());
			statement.setString(2, spotID);
			statement.execute();
			emailsender.sendConfirmationMail(this.get(spotID), user.getCompanyName(), user.getEmail());
		} catch (Exception e) {
			throw new DbException(e.getMessage(), e);
		}
		
	}

	public void removeUserFromSpot(String spotID) {
		if (spotID.isEmpty()) {
			throw new DbException("Niets te vinden !");
		}
		String sql = "UPDATE jobfair_group4.spots SET userID = NULL WHERE spotID = ?";

		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, spotID);
			statement.execute();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	public void updateSpot(Spot spot) {
		if (spot == null) {
			throw new DbException("Niets te vinden!");
		}
		String sql = "SELECT * FROM jobfair_group4.spots WHERE spotID = ?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, spot.getSpotID());
			
			ResultSet result = statement.executeQuery();

			if (result.next() && result.getString("spotId").equals(spot.getSpotID())) {
				sql = "UPDATE jobfair_group4.spots SET amountTables = ?, amountChairs = ?, remarks = ?, userID = ?, electricity = ? WHERE spotID = ?";
			} else {
				sql = "INSERT INTO jobfair_group4.spots (amountTables, amountChairs, remarks, userID, electricity, userID) VALUES (?,?,?,?,?,?)";
			}

			try {
				statement = connection.prepareStatement(sql);
	
				statement.setInt(1, spot.getAmountTables());
				statement.setInt(2, spot.getAmountChairs());
				statement.setString(3, spot.getRemarks());
				statement.setString(4, spot.getUser().getUserID());
				statement.setBoolean(5, spot.getElectricity());
				statement.setString(6, spot.getSpotID());
	
				statement.execute();
			} catch (SQLException e) {
				throw new DbException(e.getMessage(), e);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}
	
	public void deleteAll()
	{
		String sql = "DELETE FROM jobfair_group4.spots";

		try {
			statement = connection.prepareStatement(sql);
			statement.execute();
	}	catch(SQLException e)
		{
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
