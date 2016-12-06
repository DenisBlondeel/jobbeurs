package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {

	WebDriver driver;
	Statement statement;
	String useridRandom;
	String PASSWORD = "";
	String FIRSTNAME = "";

	@Before
	public void setUp() {
		registerUser();
		driver = new FirefoxDriver();
	}

	private void registerUser() {
		Properties properties = new Properties();
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/jobfair_group4";
		properties.setProperty("user", "local_hackaton_6");
		properties.setProperty("password", "eeZaetohnie7thoh");
		properties.setProperty("ssl", "true");
		properties.setProperty("currentSchema", "jobfair_group4");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");

		try {
			Connection connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
			
		} catch (SQLException e) {
		}
		
	}
}
