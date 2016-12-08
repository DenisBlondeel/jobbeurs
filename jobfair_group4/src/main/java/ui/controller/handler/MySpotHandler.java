package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class MySpotHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		

		if (user!=null) {
			String userID = user.getUserID();
			request.setAttribute("userid", user.getUserID());
			if(user.getRole().equals(RoleEnum.ADMIN))
			{
				request.setAttribute("admin", "admin");
			}
			
			Spot spot = service.getSpotFromUser(userID);
			
			//request.setAttribute("spot", spot);
			request.setAttribute("spotnr", spot.getSpotID());
			request.setAttribute("company", user.getCompanyName());
			request.setAttribute("chairs", spot.getAmountChairs());
			request.setAttribute("tables", spot.getAmountTables());
			request.setAttribute("extra", spot.getRemarks());
			
			if(spot.getElectricity() == true)
			{
				request.setAttribute("electricity", "ja");
			}
			else request.setAttribute("electricity", "nee");
			
			
			}
		
	request.getRequestDispatcher("myspot.jsp").forward(request, response);
	}

}
