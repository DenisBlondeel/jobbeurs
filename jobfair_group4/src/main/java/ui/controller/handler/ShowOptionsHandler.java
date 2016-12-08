package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.User;

public class ShowOptionsHandler extends RequestHandler {
	
	User user;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spot = request.getParameter("id");
		request.setAttribute("spotnr", spot);
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");

		if (user!=null) {
			request.setAttribute("userid", user.getUserID());
		}
		else
		{
			redirect(request, response, "index");
			
		}
		if(service.getSpot(spot).getUser() == null)redirect(request, response, "index");
		if(service.getSpot(spot).getUserID().equals(user.getUserID()))
		{
			redirect(request, response, "index");
		}

		request.getRequestDispatcher("spotoptions.jsp").forward(request, response);
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response, String dest)
	{
		request.setAttribute("bezet", service.getOccupiedSpots());
		if(user != null) request.setAttribute("mine", service.getSpotFromUser(user.getUserID()).getSpotID());
		equest.getRequestDispatcher(dest + ".jsp").forward(request, response);
	}

}
