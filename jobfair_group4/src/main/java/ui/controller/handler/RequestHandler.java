package ui.controller.handler;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ui.controller.NotAuthorizedException;

import domain.model.RoleEnum;
import domain.model.User;
import domain.service.Service;

public abstract class RequestHandler {

	protected Service service;
	protected Calendar deadline;
	private boolean timeHasCome = false;
	private boolean enoughSpots = true;

	public RequestHandler() {};

	public final void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.checkDate();
			this.checkRole(request);
			this.handleRequest(request, response);
		} catch (NotAuthorizedException e) {
			request.setAttribute("errors", e.getMessage());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	private void checkRole(HttpServletRequest request) {
		if (this.getAccesList() == null) {
			return;
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			throw new NotAuthorizedException("Insufficient rights");
		}
		for (RoleEnum role : this.getAccesList()) {
			if (user.getRole().equals(role)) {
				return;
			}
		}
		if(user.getRole().equals(RoleEnum.ADMIN))
		{
			request.setAttribute("admin", "admin");
		}
		throw new NotAuthorizedException("Insufficient rights");
	}

	public RoleEnum[] getAccesList() {
		return null;
	}

	public void setService(Service service) {
		this.service = service;
	}

	protected Service getService() {
		return service;
	}
	
	public void checkDate()
	{
		if(deadline == null) {
			timeHasCome = false;
			return;
		}
		if(deadline.getTime().after(new Date())) {
			timeHasCome = true;
		}
	}

	protected boolean getTimeHasCome() {
		return this.timeHasCome;
	}

	/**********************
	 * SpotOptionsHandler *
	 **********************/

	protected boolean getEnoughSpots() {
		return this.enoughSpots;
	}

	protected void setEnoughSpots(boolean value) {
		this.enoughSpots = value;
	}
}
