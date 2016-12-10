package domain.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.model.SpotData;

public class SpotDataRepository {

	private PreparedStatement statement;
	private Connection connection;
	private Properties properties;

	public SpotDataRepository(Properties properties)
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = properties.getProperty("url");
			connection = DriverManager.getConnection(url, properties);
			setProperties(properties);
		} catch (Exception e)
		{
			throw new DbException(e.getMessage(), e);
		}
	}

	public Properties getProperties()
	{
		return properties;
	}

	public void setProperties(Properties properties)
	{
		this.properties = properties;
	}

	public void close()
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
	}
	
	public List<SpotData> getHemisfeerData()
	{
		List<SpotData> datalist = new ArrayList<SpotData>();
		String sql = "SELECT * FROM jobfair_group4.spotdata WHERE place = 'hemis'";
		try
		{
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				SpotData data = new SpotData();
				data.setId(results.getString("spotid"));
				data.setCoords(results.getString("coords"));
				data.setShape(results.getString("shape"));
				datalist.add(data);
			}
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
		return datalist;				
	}
	
	public List<SpotData> getAtriumData()
	{
		List<SpotData> datalist = new ArrayList<SpotData>();
		String sql = "SELECT * FROM jobfair_group4.spotdata WHERE place = 'atrium'";
		try
		{
			statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while (results.next())
			{
				SpotData data = new SpotData();
				data.setId(results.getString("spotid"));
				data.setCoords(results.getString("coords"));
				data.setShape(results.getString("shape"));
				datalist.add(data);
			}
		} catch (SQLException e)
		{
			throw new DbException(e);
		}
		return datalist;				
	}
}
