package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.User;

public class MyAccountHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userid = request.getParameter("id");
		User user = service.getUser(userid);
		request.setAttribute("userid", userid);
		request.setAttribute("companyName", user.getCompanyName());
		request.setAttribute("contactName", user.getContactName());
		request.setAttribute("email", user.getEmail());
		request.getRequestDispatcher("myaccount.jsp").forward(request, response);
	}

}
