package ui.controller.handler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.EmailSender;
import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class ConfirmDeleteHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotnr = request.getParameter("spotnr");
		String action = request.getParameter("submit");
		Spot spot = this.getService().getSpot(spotnr);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if(action.equals("ja")){
			service.removeUserFromSpot(spotnr);
			request.setAttribute("annuleer", "annuleer");
			try {
				new EmailSender().sendCancelationMail(spot, user.getCompanyName(), user.getEmail());
			} catch (MessagingException e) {
				
			}
			response.sendRedirect("Controller?action=");
		} else {
			request.getRequestDispatcher("Controller?action=myspot").forward(request, response);
		}
	}

	@Override
	public RoleEnum[] getAccesList() {
		if (this.getTimeHasCome()) {
			return new RoleEnum[]{RoleEnum.ADMIN};
		}
		return new RoleEnum[]{RoleEnum.COMPANY, RoleEnum.ADMIN};
	}
}
