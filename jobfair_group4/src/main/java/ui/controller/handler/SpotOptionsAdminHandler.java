package ui.controller.handler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;
import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class SpotOptionsAdminHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int chairs = Integer.parseInt(request.getParameter("chairs"));
		int tables = Integer.parseInt(request.getParameter("tables"));
		boolean electricity = false;
		if(request.getParameter("electricity") != null){
			electricity = true;
		}
		String extra = request.getParameter("extra");
		String spotID = request.getParameter("id");
		String userID = request.getParameter("userID");

		User user = getService().getUser(userID);
		this.getService().updateSpot(new Spot(spotID, tables, chairs, electricity, extra, user));

		try {
			new EmailSender().sendUserLinkedToSpotMail(spotID, user.getCompanyName(), user.getEmail());
		} catch (MessagingException e) {
			throw new ServletException(e.getMessage(), e);
		}

		response.sendRedirect("Controller?action=");
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}
}
