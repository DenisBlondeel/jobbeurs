package ui.controller.handler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.EmailSender;
import domain.model.Spot;
import domain.model.User;

public class SpotOptionsHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, MessagingException {
		int chairs = Integer.parseInt(request.getParameter("chairs"));
		int tables = Integer.parseInt(request.getParameter("tables"));
		boolean electricity = false;
		if(request.getParameter("electricity") != null){
			electricity = true;
		}
		String extra = request.getParameter("extra");
		String spotID = request.getParameter("id");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		this.getService().updateSpot(new Spot(spotID, tables, chairs, electricity, extra, user));

		request.setAttribute("spotnr", spotID);
		request.setAttribute("reserved", "Uw plaats werd gereserveerd. U ontvangt een mail ter bevestiging.");
		new EmailSender().sendConfirmationMail(spotID, user.getEmail());

		if (user!=null) {
			request.setAttribute("userid", user.getUserID());
		}

		response.sendRedirect("Controller");
	}

}
