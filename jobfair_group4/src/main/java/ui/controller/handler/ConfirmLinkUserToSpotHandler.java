package ui.controller.handler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;
import domain.model.RoleEnum;
import domain.model.User;

public class ConfirmLinkUserToSpotHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotID = request.getParameter("spotID");
		String userID = request.getParameter("userID");
		
		this.getService().addUserToSpot(spotID, this.getService().getUser(userID));

		User user = getService().getUser(userID);
		try {
			new EmailSender().sendUserLinkedToSpotMail(spotID, user.getCompanyName(), user.getEmail());
		} catch (MessagingException e) {
			throw new ServletException(e.getMessage(), e);
		}

		response.sendRedirect("admin.jsp");
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}
}
