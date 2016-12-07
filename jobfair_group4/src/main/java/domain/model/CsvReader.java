package domain.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
	
	private String csvFile = "";
	private BufferedReader br = null;
	private String line = "";
	
	
	public List<User> read(){
		List<User> users = new ArrayList<User>();
		try{
			br = new BufferedReader(new FileReader(csvFile));
			while((line = br.readLine()) != null){
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
			
		} catch (IOException e){
			e.printStackTrace();
		}
		return users;
	}

}
