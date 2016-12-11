package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.User;

public class ResetPwHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String result;
		User user = service.getUser(userid);
		if(user != null && user.getUserID() != null && !user.getUserID().trim().isEmpty()){
			result = "Ben je je wachtwoord vergeten? Klik dan hieronder op 'Reset' om een nieuw wachtwoord te ontvangen via email."
					+ "/n Deze mail wordt verzonden naar " + user.getEmail() + ".";
			String email = user.getEmail();
			request.setAttribute("email", email);
			request.setAttribute("userid", user.getUserID());
			request.setAttribute("showReset", "ok");
		} else {
			result = "Gebruiker met gebruikersnaam " + userid + " bestaat niet.";
			request.setAttribute("showReset", null);
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("resetpw.jsp").forward(request, response);
	}

}
