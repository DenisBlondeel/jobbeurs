package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.StaticArray;
import domain.model.User;

public class IndexHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user!=null) {
			request.setAttribute("userid", user.getUserID());
			if(user.getRole().equals(RoleEnum.ADMIN))
			{
				request.setAttribute("admin", "admin");
			}
			Spot spot = service.getSpotFromUser(user.getUserID());
			if (spot != null) {
				request.setAttribute("mine", spot.getSpotID());
			}
		}
		if(StaticArray.getAtrium().isEmpty()) StaticArray.fill();
		if(StaticArray.getHemis().isEmpty()) StaticArray.fill();
		request.setAttribute("bezet", service.getOccupiedSpots());
		request.setAttribute("hemis", StaticArray.getHemis());
		request.setAttribute("atrium", StaticArray.getAtrium());

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
