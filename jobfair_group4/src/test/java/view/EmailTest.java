package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

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

	@Test
	public void sendEndMailTest() throws Exception {
		Calendar deadline = Calendar.getInstance();
		deadline.set(1995, 10, 26);
		List<String> receivers = new ArrayList<>();
		receivers.add("email@hotmail.com");
		sender.sendEndOfRegistrationMail(deadline, receivers);
	}
}
