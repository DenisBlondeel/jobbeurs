package ui.controller.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.User;

public class ContactHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> admins = service.getAdmins();
		request.setAttribute("admins", admins);
		request.getRequestDispatcher("contact.jsp").forward(request, response);		
	}

}
