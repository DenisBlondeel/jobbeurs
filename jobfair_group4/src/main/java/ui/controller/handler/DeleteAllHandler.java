package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.RoleEnum;

public class DeleteAllHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String action = request.getParameter("submit");

		if(action.equals("ja")){
			service.dropAllUsers();
			response.sendRedirect("Controller?action=");
		} else {
			request.getRequestDispatcher("Controller?action=admin").forward(request, response);
		}
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}

}
