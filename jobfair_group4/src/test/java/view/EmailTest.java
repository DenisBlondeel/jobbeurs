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
	public void sendConfirmationMailTest() {
		sender.sendConfirmationMail("Ik hoop dat dit werkt", "brechtdecuyper@hotmail.com");
	}
}
