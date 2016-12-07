package domain.db;

import java.sql.SQLException;

public class DbException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DbException(String message, Exception e) {
		super(message, e);
	}

	public DbException(String message) {
		super(message);
	}

	public DbException(SQLException e) {
		super(e);
	}
}
