package view;

import org.junit.Before;
import org.junit.Test;

import domain.model.EmailSender;

public class EmailTest {

	EmailSender sender;
	

	@Before
	public void setUp() {
		sender = new EmailSender();
	}

	@Test
	public void sendSimpleMailTest() throws Exception {
//		sender.sendNewCompanyMail("username", "password", "email@hotmail.com");
	}

	@Test
	public void sendConfirmationMailTest() throws Exception {
//		sender.sendConfirmationMail("spotID", "email@hotmail.com");
	}
}
