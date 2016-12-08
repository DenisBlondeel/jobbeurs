package domain.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
	
	public List<User> read(InputStream in){
		List<User> users = new ArrayList<User>();
		Scanner inputStream;
		inputStream = new Scanner(in);
		while(inputStream.hasNextLine()){
			String line = inputStream.nextLine();
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
		inputStream.close();
		return users;
	}

}
