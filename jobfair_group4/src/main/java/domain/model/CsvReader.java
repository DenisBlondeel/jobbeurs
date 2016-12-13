package domain.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.mail.MessagingException;

public class CsvReader {

	private EmailSender emailSender = new EmailSender();
	
	@SuppressWarnings("resource")
	public List<User> read(InputStream in) throws MessagingException{
		List<User> users = new ArrayList<User>();
//		Map<User, String> mailList = new HashMap<>();

		Scanner inputStream;
		inputStream = new Scanner(in, "ISO-8859-1");
		while(inputStream.hasNextLine()){
			String line = inputStream.nextLine();
			String[] data = line.split(";");
			User user = new User();
			String companyName = data[0];
			String contactName = data[1];
			String email = data[2];
			if(!companyName.equals("Bedrijfsnaam") && !contactName.equals("Naam contactpersoon") && !email.equals("E-mailadres contactpersoon")){
				user.setCompanyName(companyName);
				user.setContactName(contactName);
				user.setEmail(email);
				user.generateUserId(companyName);
				String tempPass = user.generatePassword();
				users.add(user);
				try {
					emailSender.sendNewCompanyMail(user.getUserID(), tempPass, email);
				} catch (MessagingException e) {
					throw new MessagingException(e.getMessage(), e);
				}
//				mailList.put(user, tempPass);
			}
		}

//		try {
//			emailSender.sendNewCompanyMail(mailList);
//		} catch (MessagingException e) {
//			throw new MessagingException(e.getMessage(), e);
//		}

		inputStream.close();
		return users;
	}

}
