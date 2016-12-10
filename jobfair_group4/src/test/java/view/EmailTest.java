package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import domain.model.EmailSender;
import domain.model.Spot;

public class EmailTest {

	EmailSender sender;
	String recipient = "test@hotmail.com";
	String company = "Enola Gay";

	@Before
	public void setUp() {
		sender = new EmailSender();
	}

	@Test
	public void sendSimpleMailTest() throws Exception {
		sender.sendNewCompanyMail("username", "password", recipient);
	}

	@Test
	public void sendConfirmationMailTest() throws Exception {
		Spot spot = new Spot("1425", 1, 2, true, null, null);
		sender.sendConfirmationMail(spot, company, recipient);
	}

	@Test
	public void sendSingleEndMailTest() throws Exception {
		Calendar deadline = Calendar.getInstance();
		deadline.set(1995, 10, 26);
		List<String> receivers = new ArrayList<>();
		receivers.add(recipient);
		sender.sendEndOfRegistrationMail(deadline, receivers);
	}

	@Test
	public void sendMultipleEndMailTest() throws Exception {
		List<String> receivers = new ArrayList<>();
		receivers.add(recipient);
		receivers.add(recipient);
		receivers.add(recipient);
		receivers.add(recipient);
		Calendar deadline = Calendar.getInstance();
		deadline.set(2016, 11, 25);

		if (receivers.size() == 4)
			sender.sendEndOfRegistrationMail(deadline, receivers);
	}

	@Test
	public void sendAlmostSoldOutTest() throws Exception {
		List<String> receivers = new ArrayList<>();
		receivers.add(recipient);
		receivers.add(recipient);
		receivers.add(recipient);
		receivers.add(recipient);

		sender.sendAlmostSoldOutMail(receivers);
	}
}
