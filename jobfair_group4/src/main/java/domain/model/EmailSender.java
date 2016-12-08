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
		properties.put("mail.smtp.password", "tttttttt");
		properties.put("mail.smtp.user", "scrumbags.06");
	}
	
	public void sendConfirmationMail(String spotId, String emailreceiver){
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		
		try{
			String from = properties.getProperty("mail.smtp.user");
			String password = properties.getProperty("mail.smtp.password");
			String host = properties.getProperty("mail.smtp.host");

			message.setFrom(new InternetAddress(emailsender));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailreceiver));
			message.setSubject("confirmationemail");
			message.setText("uw plaats met nummer " + spotId + " werd gereserveerd");

			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch(MessagingException m) {
			throw new DbException(m.getMessage());
		} 
	}
}