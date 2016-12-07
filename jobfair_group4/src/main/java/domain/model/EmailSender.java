package domain.model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.db.DbException;


public class EmailSender {

	
	private String emailsender = "scrumbags.06@gmail.com";

	
	public void sendConfirmationMail(String spotId, String emailreceiver){
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.password", "tttttttt");
		properties.put("mail.smtp.user", "scrumbags.06");
		
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		
		try{
		message.setFrom(new InternetAddress(emailsender));
		
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailreceiver));
		message.setSubject("confirmationemail");
		message.setText("uw plaats met nummer " + spotId + " werd gereserveerd");
		Transport transport = session.getTransport("smtp");
		transport.connect("scrumbags.06",emailsender, "tttttttt");
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		}
		catch(MessagingException m) {
			throw new DbException(m.getMessage());
		} 
	}






}