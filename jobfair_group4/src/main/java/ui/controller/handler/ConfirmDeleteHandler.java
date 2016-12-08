package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmDeleteHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotnr = request.getParameter("spotnr");
		String action = request.getParameter("submit");
		if(action.equals("ja")){
			service.removeUserFromSpot(spotnr);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("myspot.jsp").forward(request, response);
		}
	}

}
