package ui.controller.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Spot;

public class GetAlphabeticSpotsHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		List<Spot> spots = this.getService().getAlphabeticOccupiedSpots();
		request.setAttribute("spots", spots);

		request.getRequestDispatcher("spotoverview.jsp").forward(request, response);
		
	}

}
