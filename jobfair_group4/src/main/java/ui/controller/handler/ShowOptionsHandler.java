package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class ShowOptionsHandler extends RequestHandler {
	
	User user;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotID = request.getParameter("id");
		request.setAttribute("spotnr", spotID);
		
		HttpSession session = request.getSession();
		user = (User) session.getAttribute("user");
		
		if(service.getSpotFromUser(user.getUserID()) != null){
			request.setAttribute("errors", "Er werd al een plaats gereserveerd voor " + user.getCompanyName() + ".");
			response.sendRedirect("Controller?action=");
		}

		if (user!=null) {
			request.setAttribute("userid", user.getUserID());
			Spot spot = service.getSpotFromUser(user.getUserID());
			if (spot != null) {
				request.setAttribute("mine", spot.getSpotID());
			}
		}
		request.setAttribute("bezet", service.getOccupiedSpots());
		request.getRequestDispatcher("spotoptions.jsp").forward(request, response);
		
	}

	@Override
	public RoleEnum[] getAccesList() {
		if (this.getTimeHasCome()) {
			return new RoleEnum[]{};
		}
		return new RoleEnum[]{RoleEnum.COMPANY};
	}
}
