package domain.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
	
	public List<User> read(File file){
		List<User> users = new ArrayList<User>();
		Scanner inputStream;
		try {
			inputStream = new Scanner(file);
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return users;
	}

}
