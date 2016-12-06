package domain.service;

import java.util.Properties;

import domain.db.SpotRepository;

public class SpotService {
	
	private SpotRepository spotRepository;

	public SpotService(Properties properties) {
		
		spotRepository = new SpotRepository(properties);
	}

}
