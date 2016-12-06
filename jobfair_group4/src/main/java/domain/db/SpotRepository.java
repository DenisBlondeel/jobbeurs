package domain.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class SpotRepository {

	private PreparedStatement statement;
	private Connection connection;
	private Properties properties;

	public SpotRepository(Properties properties)
	{
		this.properties = properties;
	}
}
