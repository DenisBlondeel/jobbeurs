package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class GetCompaniesAlphabeticallyOnCompanyHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> companies = this.getService().getAllCompanies();
		List<Spot> spots = this.getService().getAllSpots();
		List<String> spotsTaken = new ArrayList<>();
		
		for (User company : companies) {
			String companySpot = "";
			for (Spot spot : spots) {
				if(spot.getUserID()!=null && spot.getUserID().equals(company.getUserID())) {
					companySpot = spot.getSpotID();
				}
			}
			spotsTaken.add(companySpot);
		}
		
		request.setAttribute("companies", companies);
		request.setAttribute("spotsTaken", spotsTaken);
		
		request.setAttribute("admin", "admin");

		request.getRequestDispatcher("companiesoverview.jsp").forward(request, response);
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}
}
