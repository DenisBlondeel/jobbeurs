package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.RoleEnum;

public class LinkUserToSpotHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("freeSpots", this.getService().getFreeSpots());
		request.setAttribute("freeUsers", this.getService().getUsersWithoutSpot());

		request.getRequestDispatcher("linkSpot.jsp").forward(request, response);
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}
}
