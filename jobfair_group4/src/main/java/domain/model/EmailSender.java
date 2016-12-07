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

	
	private String emailsender = "";
	private String emailserver = "";
	
	public void sendConfirmationMail(String spotId, String emailreceiver){
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", emailserver);
		Session session = Session.getDefaultInstance(properties);
		
		try{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailsender));
		
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailreceiver));
		message.setSubject("confirmationemail");
		message.setText("uw plaats met nummer " + spotId + " werd gereserveerd");
		Transport.send(message);
		}
		catch(MessagingException m) {
			throw new DbException(m.getMessage());
		} 
	}
}
