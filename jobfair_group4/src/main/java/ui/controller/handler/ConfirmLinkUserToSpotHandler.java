package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmLinkUserToSpotHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotID = request.getParameter("spotID");
		String userID = request.getParameter("userID");
		
		this.getService().addUserToSpot(spotID, this.getService().getUser(userID));

		response.sendRedirect("admin.jsp");
	}

}
