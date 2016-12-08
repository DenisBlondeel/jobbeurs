package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Spot;
import domain.model.User;

public class ConfirmUpdateHandler extends RequestHandler	{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotnr = request.getParameter("spotnr");
		Spot prev = service.getSpot(spotnr);
		int chairs = Integer.parseInt(request.getParameter("chairs"));
		int tables = Integer.parseInt(request.getParameter("tables"));
		boolean electricity = request.getParameter("electricity") != null;
		String extra = request.getParameter("extra");
		User user = service.getUser(prev.getUserID());
		Spot spot = new Spot(spotnr, chairs, tables, electricity, extra, user);
		service.updateSpot(spot);
		request.setAttribute("spot", spot);
		request.getRequestDispatcher("myspot.jsp").forward(request, response);
	}

}
