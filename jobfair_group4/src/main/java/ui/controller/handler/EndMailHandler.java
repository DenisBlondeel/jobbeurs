package ui.controller.handler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;

public class EndMailHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			new EmailSender().sendEndOfRegistrationMail(deadline.getTime(), this.getService().getEmailFromUsersWithoutSpot());
		} catch (MessagingException e) {
			throw new ServletException();
		}
		response.sendRedirect("Controller?action=admin");
		
	}

}
