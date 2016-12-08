package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.Spot;
import domain.model.User;

public class MySpotHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		String userID = user.getUserID();

		if (user!=null) {
			request.setAttribute("userid", user.getUserID());
		}
		
		Spot spot = service.getSpot(userID);
		
		request.setAttribute("spot", spot);
		request.setAttribute("spotnr", spot.getSpotID());
		request.setAttribute("chairs", spot.getAmountChairs());
		request.setAttribute("tables", spot.getAmountTables());
		request.setAttribute("electricity", spot.getElectricity());
		request.setAttribute("extra", spot.getRemarks());
		
		response.sendRedirect("myspot.jsp");
	}

}
