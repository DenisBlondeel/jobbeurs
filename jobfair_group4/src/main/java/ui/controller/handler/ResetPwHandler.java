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
		String actie;
		User user = service.getUser(userid);
		String email = user.getEmail();
		if(user.getUserID() == null){
			result = "Ben je je wachtwoord vergeten? Klik dan hieronder op 'Reset' om een nieuw wachtwoord te ontvangen via email."
					+ "/n Deze mail wordt verzonden naar " + user.getEmail() + ".";
			actie = "success";
		} else {
			result = "Gebruiker met gebruikersnaam " + userid + " bestaat niet.";
			actie = "fail";
		}
		request.setAttribute("result", result);
		request.setAttribute("actie", actie);
		request.setAttribute("email", email);
		request.setAttribute("userid", user.getUserID());
		request.getRequestDispatcher("resetpw.jsp").forward(request, response);
	}

}
