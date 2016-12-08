package domain.model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.db.DbException;


public class EmailSender {

	private String emailsender = "scrumbags.06@gmail.com";
	private String username = "scrumbags.06", password = "tttttttt";
	private Properties properties;


	public EmailSender() {
		loadProperties();
	}

	private void loadProperties() {
		properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.user", username);
	}
	
	public void sendConfirmationMail(String spotId, String emailreceiver){
		Session session = Session.getInstance(properties,
			      new javax.mail.Authenticator() {
			         protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(username, password);
			         }
			      });
		
		
		MimeMessage message = new MimeMessage(session);
		
		
		try{
			
			message.setFrom(new InternetAddress(emailsender));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailsender));
			message.setSubject("confirmationemail");
			message.setText("uw plaats met nummer " + spotId + " werd gereserveerd");

			Transport.send(message);
		} catch(MessagingException m) {
			throw new DbException(m.getMessage());
		} 
	}
}