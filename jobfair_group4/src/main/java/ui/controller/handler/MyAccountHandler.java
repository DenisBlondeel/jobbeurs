package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.RoleEnum;

public class MyAccountHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("myaccount.jsp");
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN, RoleEnum.COMPANY};
	}
}
