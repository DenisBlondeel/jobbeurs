package ui.controller.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.User;

public class LogInHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userID = request.getParameter("username");
		String password = request.getParameter("password");
		User user = this.getService().getUserIfAuthenticated(userID, password);

		if (user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("user", user);
		}

		response.sendRedirect("index.jsp");
	}

}
