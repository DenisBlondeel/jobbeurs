package domain.model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.mail.MessagingException;

import domain.service.Service;

public class CsvReader {

	private EmailSender emailSender = new EmailSender();
	
	public void read(InputStream in, Service service) throws MessagingException{
		Map<User, String> mailList = new HashMap<>();

		Scanner inputStream;
		inputStream = new Scanner(in, "ISO-8859-1");
		while(inputStream.hasNextLine()){
			String line = inputStream.nextLine();
			String[] data = line.split(";");
			User user = new User();
			String companyName = data[0];
			String contactName = data[1];
			String email = data[2];
			if(companyName.equals("Bedrijfsnaam") || contactName.equals("Naam contactpersoon") || email.equals("E-mailadres contactpersoon")){
				continue;
			}
			user.setCompanyName(companyName);
			user.setContactName(contactName);
			user.setEmail(email);
			user.generateUserId(companyName);
			String tempPass = user.generatePassword();
			service.addUser(user);
			mailList.put(user, tempPass);
		}

		inputStream.close();

		try {
			emailSender.sendNewCompanyMail(mailList);
		} catch (MessagingException e) {
			throw new MessagingException(e.getMessage(), e);
		}
	}

}
