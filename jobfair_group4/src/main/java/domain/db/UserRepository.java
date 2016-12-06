package domain.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class UserRepository {

	private PreparedStatement statement;
	private Connection connection;
	private Properties properties;

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
	}

}
