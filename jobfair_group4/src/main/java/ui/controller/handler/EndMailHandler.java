package ui.controller.handler;

import java.io.IOException;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.EmailSender;
import domain.model.RoleEnum;

public class EndMailHandler extends RequestHandler {
	private EmailSender emailSender = new EmailSender();

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Calendar deadline = (Calendar)session.getAttribute("deadline");
		this.getService().getEmailFromUsersWithoutSpot();
		try {
			emailSender.sendEndOfRegistrationMail(deadline, this.getService().getEmailFromUsersWithoutSpot());
			request.setAttribute("success", "Je mails werden verstuurd");
		} catch (MessagingException e) {
			throw new ServletException();
		}
		request.getRequestDispatcher("Controller?action=admin").forward(request, response);
		
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}
}
