package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.User;

public class DeleteBedrijfHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String bedrijf = request.getParameter("companyName");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		User user = ((User) session.getAttribute("user"));

		if (user.isCorrectPassword(password))
		{
			try
			{
				service.deleteUser(bedrijf);
				request.setAttribute("success", "Het bedrijf " + bedrijf + " is verwijderd");
				request.getRequestDispatcher("admin.jsp").forward(request, response);
			} catch (IllegalArgumentException e)
			{
				request.setAttribute("errors", e.getMessage());
				request.getRequestDispatcher("Controller?action=getBedrijven").forward(request, response);
			}
		}
	}

	@Override
	public RoleEnum[] getAccesList()
	{
		return new RoleEnum[] { RoleEnum.ADMIN };
	}

}
