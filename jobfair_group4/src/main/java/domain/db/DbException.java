package domain.db;

import java.sql.SQLException;

public class DbException extends RuntimeException {

	public DbException(String message, Exception e)
	{
		super(message, e);
	}

	public DbException(String message)
	{
		super(message);
	}

	public DbException(SQLException e)
	{
		super(e);
	}

}
