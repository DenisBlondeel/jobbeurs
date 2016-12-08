package domain.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
	
	private BufferedReader br = null;
	private String line = "";
	
	
	public List<User> read(InputStream fileContent){
		List<User> users = new ArrayList<User>();
		Scanner inputStream = new Scanner(fileContent);
		while(inputStream.hasNext()){
			String[] data = line.split(";");
			User user = new User();
			String companyName = data[0];
			user.setCompanyName(companyName);
			String contactName = data[1];
			user.setContactName(contactName);
			String email = data[3];
			user.setEmail(email);
			user.generateUserId(companyName);
			user.generatePassword();
			users.add(user);
		}
		return users;
	}

}
