package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.RoleEnum;

public class ToShowOptionsHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoleEnum[] getAccesList()
	{
		if (this.getTimeHasCome())
		{
			return new RoleEnum[] {RoleEnum.ADMIN};
		}
		return new RoleEnum[] { RoleEnum.COMPANY, RoleEnum.ADMIN};
	}
}
