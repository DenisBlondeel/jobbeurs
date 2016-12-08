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
		sender.sendNewCompanyMail("r0589873", "Brecht", "brechtdecuyper@hotmail.com");
	}

//	@Test
//	public void sendConfirmationMailTest() throws Exception {
//		sender.sendConfirmationMail("1762", "brechtdecuyper@hotmail.com");
//	}
}
