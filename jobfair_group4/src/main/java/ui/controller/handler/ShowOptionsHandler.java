package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class ShowOptionsHandler extends RequestHandler {

	List<String> errors = new ArrayList<String>();

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String spotID = request.getParameter("id");
		request.setAttribute("spotnr", spotID);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		//Afhandelen van het reserveren van een tweede spot.
		if (service.getSpotFromUser(user.getUserID()) != null)
		{
			errors.add("Er werd al een plaats gereserveerd voor " + user.getCompanyName() + ".");
			request.setAttribute("errors", errors);
			response.sendRedirect("Controller?action=");
			return;
		}

		//Afhandelen van het reserveren van een reeds bezette spot.
		Spot spotUser = getService().getSpot(spotID);
		if (spotUser.getUserID() != null && !spotUser.getUserID().equals(user.getUserID())) {
			request.setAttribute("userid", user.getUserID());
			Spot spot = service.getSpotFromUser(user.getUserID());
			if (spot != null)
			{
				request.setAttribute("mine", spot.getSpotID());
			}
			request.setAttribute("bezet", service.getOccupiedSpots());
			request.setAttribute("errors", "Spot "+spotUser.getSpotID()+" is al gereserveerd door het bedrijf "+getService().getUser(spotUser.getUserID()).getCompanyName()+".");
			request.getRequestDispatcher("Controller?action=").forward(request, response);
			return;
		}
		
		if (user.getRole().equals(RoleEnum.ADMIN)) {
			request.setAttribute("freeUsers", this.getService().getUserIDsWithoutSpot());
			request.getRequestDispatcher("spotoptionsadmin.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("spotoptions.jsp").forward(request, response);
		}

	}

	@Override
	public RoleEnum[] getAccesList()
	{
		if (this.getTimeHasCome())
		{
			return new RoleEnum[] {RoleEnum.ADMIN};
		}
		return new RoleEnum[] { RoleEnum.COMPANY, RoleEnum.ADMIN};
	}
}
