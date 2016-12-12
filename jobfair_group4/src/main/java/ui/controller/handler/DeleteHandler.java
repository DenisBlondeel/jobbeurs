package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.RoleEnum;

public class DeleteHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotnr = request.getParameter("spotnr");
		request.setAttribute("spotnr", spotnr);
		request.getRequestDispatcher("confirmdelete.jsp").forward(request, response);
	}

	@Override
	public RoleEnum[] getAccesList() {
		if (this.getTimeHasCome()) {
			return new RoleEnum[]{};
		}
		return new RoleEnum[]{RoleEnum.COMPANY};
	}
}
