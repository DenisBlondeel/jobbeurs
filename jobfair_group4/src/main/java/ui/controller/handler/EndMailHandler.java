package ui.controller.handler;

import java.io.IOException;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.EmailSender;

public class EndMailHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Calendar deadline = (Calendar)session.getAttribute("deadline");
		try {
			new EmailSender().sendEndOfRegistrationMail(deadline, this.getService().getEmailFromUsersWithoutSpot());
			request.setAttribute("success", "Je mails werden verstuurd");
		} catch (MessagingException e) {
			throw new ServletException();
		}
		request.getRequestDispatcher("Controller?action=admin").forward(request, response);
		
	}

}
