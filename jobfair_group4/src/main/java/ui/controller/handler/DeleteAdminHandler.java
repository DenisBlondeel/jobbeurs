package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.User;

public class DeleteAdminHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminID = request.getParameter("adminID");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		User user = ((User) session.getAttribute("user"));

		if (user.isCorrectPassword(password)) {
			try {
				this.getService().deleteAdmin(adminID);
				response.sendRedirect("Controller?action=admin");
			} catch (IllegalArgumentException e) {
				request.setAttribute("errors", e.getMessage());
				request.getRequestDispatcher("Controller?action=getAdmin").forward(request, response);
			}
		}
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}
}
