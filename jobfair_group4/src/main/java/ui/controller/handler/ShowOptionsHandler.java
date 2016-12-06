package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowOptionsHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spot = request.getParameter("id");
		request.setAttribute("spotnr", spot);

		request.getRequestDispatcher("spotoptions.jsp").forward(request, response);
	}

}
