package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.User;

public class LogInHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String userID = request.getParameter("userid");
		String password = request.getParameter("password");
		User user = service.getUserIfAuthenticated(userID, password);
		
		if(user!=null) {
			String success;
			success = "Je bent succesvol ingelogd!";
			request.setAttribute("success", success);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("userid", user.getUserID());
			response.sendRedirect("Controller?action=");
		} else {
			ArrayList<String> errors = new ArrayList<>();
			errors.add("Uw gebruikersnaam of wachtwoord is niet correct");
			request.setAttribute("errors", errors);
			request.setAttribute("userid", userID);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		request.getRequestDispatcher("Controller?action=resetpw").forward(request, response);
	}
}
