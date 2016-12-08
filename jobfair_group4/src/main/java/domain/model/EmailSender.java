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
	
	public void sendConfirmationMail(String spotId, String emailreceiver) throws Exception{
		String subject = "Confirmation email";
		String message = "Uw plaats met nummer " + spotId + " werd gereserveerd";
		sendFromGMail(subject, message, emailreceiver);
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

	public void sendMail() throws Exception {
		String subject = "A test mail";
		String message = "Dear customer, please may we inform you that you can register for the job fair:";
		sendFromGMail(subject, message, "brechtdecuyper@hotmail.com");
	}

	private void sendFromGMail(String subject, String body, String... to) throws Exception {
		Session session = Session.getDefaultInstance(properties);
		
		MimeMessage message = new MimeMessage(session);
		for (int i = 0; i < to.length; i++) {
			InternetAddress toAddress = new InternetAddress(to[i]);
			message.addRecipient(Message.RecipientType.TO, toAddress);
		}
		message.setSubject(subject);
		message.setText(body);
		
		String from = properties.getProperty("mail.smtp.user");
		String password = properties.getProperty("mail.smtp.password");
		Transport transport = session.getTransport("smtp");
		transport.connect(from, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
}