package domain.service;

import java.util.List;
import java.util.Properties;

import domain.db.SpotDataRepository;
import domain.db.SpotRepository;
import domain.db.UserRepository;
import domain.model.Spot;
import domain.model.SpotData;
import domain.model.User;

public class Service {
	
	private SpotRepository spotDB;
	private UserRepository userDB;
	private SpotDataRepository dataDB;

	public Service(Properties properties) {
		spotDB = new SpotRepository(properties);
		userDB = new UserRepository(properties);
		dataDB = new SpotDataRepository(properties);
	}

	public void close() {
		spotDB.close();
		userDB.close();
	}

	public User getUser(String userID) {
		return getUserRepository().get(userID);
	}
	
	public List<User> getAllCompanies() {
		return getUserRepository().getAllCompanies();
	}

	public void addUser(User user) {
		getUserRepository().add(user);
	}

	public void deleteUser(String userID) {
		getUserRepository().delete(userID);
	}

	public User getUserIfAuthenticated(String userID, String password) {
		return getUserRepository().getUserIfAuthenticated(userID, password);
	}

	public List<String> getUserIDsWithoutSpot() {
		return getUserRepository().getUserIDsWithoutSpot();
	}

	public List<String> getEmailFromUsersWithoutSpot() {
		return getUserRepository().getEmailFromUsersWithoutSpot();
	}

	private UserRepository getUserRepository() {
		return this.userDB;
	}

	public Spot getSpot(String spotId) {
		return getSpotRepository().get(spotId);
	}
	
	public Spot getSpotFromUser(String userID)
	{
		return getSpotRepository().getFromUser(userID);
	}
	
	public List<Spot> getAllSpots() {
		return getSpotRepository().getAll();
	}
	
	public List<Spot> getFreeSpots() {
		return getSpotRepository().getFreeSpots();
	}
	
	public List<Spot> getOccupiedSpots() {
		return getSpotRepository().getOccupiedSpots();
	}
	
	public List<Spot> getAlphabeticOccupiedSpots() {
		return getSpotRepository().getAlphabeticOccupiedSpots();
	}
	
	public void addUserToSpot(String spotId, User user) {
		getSpotRepository().addUserToSpot(spotId, user);
	}
	
	public void removeUserFromSpot(String spotId) {
		getSpotRepository().removeUserFromSpot(spotId);
	}

	public void updateSpot(Spot spot) {
		this.getSpotRepository().updateSpot(spot);
	}
	
	public SpotRepository getSpotRepository() {
		return spotDB;
	}
	
	public void dropAllUsers()
	{
		userDB.deleteAll();
		spotDB.removeAllUsersFromSpots();
	}

	public List<String> getAdminEmails() {
		return userDB.getAllAdminEmails();
	}

	public List<User> getAdmins() {
		return getUserRepository().getAdmins();
	}

	public void deleteAdmin(String userID) {
		getUserRepository().deleteAdmin(userID);
	}
	
	public List<SpotData> getHemisData()
	{
		return dataDB.getHemisfeerData();
	}
	
	public List<SpotData> getAtriumData()
	{
		return dataDB.getAtriumData();
	}
}
