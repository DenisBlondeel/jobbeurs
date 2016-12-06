package domain.service;

import java.util.List;
import java.util.Properties;

import domain.db.SpotRepository;
import domain.db.UserRepository;
import domain.model.Spot;
import domain.model.User;

public class SpotService {
	
	private SpotRepository spotRepository;
	private UserRepository userRepository;

	public SpotService(Properties properties) {
		
		spotRepository = new SpotRepository(properties);
		userRepository = new UserRepository();
	}
	
	public Spot getSpot(String spotId)
	{
		return spotRepository.get(spotId);
	}
	
	public List<Spot> getAll(){
		return spotRepository.getAll();
	}
	
	public List<Spot> getFreeSpot()
	{
		return spotRepository.getFreeSpots();
	}
	
	public List<Spot> getOccupiedSpot()
	{
		return spotRepository.getOccupiedSpots();
	}
	
	public List<Spot> getAlphabeticOccupiedSpot()
	{
		return spotRepository.getOccupiedSpots();
	}
	
	public void addUserToSpot(String spotId, User user)
	{
		spotRepository.addUserToSpot(spotId, user);
	}
	
	public void removeUserFromSpot(String spotId)
	{
		spotRepository.removeUserFromSpot(spotId);
	}
	
	public SpotRepository getSpotRepository()
	{
		return spotRepository;
	}
	
	
	
	

}
