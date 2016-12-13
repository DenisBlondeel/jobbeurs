package domain.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Scanner;

import javax.mail.MessagingException;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import domain.service.Service;

public class CsvReader {

	private EmailSender emailSender = new EmailSender();
	private CsvParserSettings settings;
	private CsvParser parser;
	
	public CsvReader() {
		settings = new CsvParserSettings();
		settings.getFormat().setDelimiter(';');
		settings.getFormat().setLineSeparator("\n");
		settings.selectIndexes(0,1,2);
		parser = new CsvParser(settings);
	}
	
//	public void read(InputStream in, Service service) throws MessagingException{
//		List<User> users = new ArrayList<>();
//		Map<User, String> mailList = new HashMap<>();
//
//		Scanner inputStream;
//		inputStream = new Scanner(in, "ISO-8859-1");
//		inputStream.nextLine();
//		while(inputStream.hasNextLine()){
//			String line = inputStream.nextLine();
//			String[] data = line.split(";");
//			User user = new User();
//			String companyName = data[0];
//			String contactName = data[1];
//			String email = data[2];
//			if(companyName.equals("Bedrijfsnaam") || contactName.equals("Naam contactpersoon") || email.equals("E-mailadres contactpersoon")){
//				continue;
//			}
//			user.setCompanyName(companyName);
//			user.setContactName(contactName);
//			user.setEmail(email);
//			user.generateUserId(companyName);
//			String tempPass = user.generatePassword();
//
//			users.add(user);
//			mailList.put(user, tempPass);
//		}
//		inputStream.close();
//		
//		service.addUsers(users);
//
//		try {
//			emailSender.sendNewCompanyMail(mailList);
//		} catch (MessagingException e) {
//			throw new MessagingException(e.getMessage(), e);
//		}
//	}

	public void read(InputStream in, Service service) throws MessagingException{
		List<User> users = new ArrayList<>();
		Map<User, String> mailList = new HashMap<>();

		List<String[]> allRows = parser.parseAll(in, "ISO-8859-1");
		System.out.println(allRows.size());
		for (int i = 0; i < allRows.size(); i++) {
			String[] data = allRows.get(i);
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

			users.add(user);
			mailList.put(user, tempPass);
		}
		service.addUsers(users);

		try {
			emailSender.sendNewCompanyMail(mailList);
		} catch (MessagingException e) {
			throw new MessagingException(e.getMessage(), e);
		}
	}

}
