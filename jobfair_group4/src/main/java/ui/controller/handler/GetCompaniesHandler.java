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

public class GetCompaniesHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> companies = this.getService().getAllCompanies();
		List<Spot> spots = this.getService().getAllSpots();
		List<Spot> spotsTaken = new ArrayList<>();
		
		for (User company : companies) {
			for (Spot spot : spots) {
				if(spot.getUserID()!=null && spot.getUserID().equals(company.getUserID())) {
					spotsTaken.add(spot);
				}
			}
		}
		
		request.setAttribute("spotsTaken", spotsTaken);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user!=null) {
			request.setAttribute("userid", user.getUserID());
			if(user.getRole().equals(RoleEnum.ADMIN))
			{
				request.setAttribute("admin", "admin");
			}
		}

		request.getRequestDispatcher("companiesoverview.jsp").forward(request, response);
	}

}
