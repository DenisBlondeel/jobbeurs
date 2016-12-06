package domain.service;

import java.util.List;
import java.util.Properties;

import domain.db.SpotRepository;
import domain.db.UserRepository;
import domain.model.Spot;
import domain.model.User;

public class SpotService {
	
	private SpotRepository spotDB;
	private UserRepository userDB;

	public SpotService(Properties properties) {
		
		spotDB = new SpotRepository(properties);
		userDB = new UserRepository(properties);
	}
	
	public Spot getSpot(String spotId)
	{
		return spotDB.get(spotId);
	}
	
	public List<Spot> getAll(){
		return spotDB.getAll();
	}
	
	public List<Spot> getFreeSpot()
	{
		return spotDB.getFreeSpots();
	}
	
	public List<Spot> getOccupiedSpot()
	{
		return spotDB.getOccupiedSpots();
	}
	
	public List<Spot> getAlphabeticOccupiedSpot()
	{
		return spotDB.getOccupiedSpots();
	}
	
	public void addUserToSpot(String spotId, User user)
	{
		spotDB.addUserToSpot(spotId, user);
	}
	
	public void removeUserFromSpot(String spotId)
	{
		spotDB.removeUserFromSpot(spotId);
	}
	
	public SpotRepository getSpotRepository()
	{
		return spotDB;
	}

	public void close() {
		spotDB.close();
		userDB.close();
	}
	
	
	
	

}
