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
	private boolean timeHasCome = false;
	private boolean enoughSpots = true;

	public RequestHandler() {};

	public final void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.checkDate(request);
			this.checkRole(request);
			this.handleRequest(request, response);
		} catch (NotAuthorizedException e) {
			request.setAttribute("errors", e.getMessage());
			request.getRequestDispatcher("Controller?action=home").forward(request, response);
		}
	}

	public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	private void checkRole(HttpServletRequest request) {
		if (this.getAccesList() == null) {
			return;
		}
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			throw new NotAuthorizedException("Je hebt hiervoor geen toegang");
		}
		for (RoleEnum role : this.getAccesList()) {
			if (user.getRole().equals(role)) {
				return;
			}
		}
		throw new NotAuthorizedException("Je hebt hiervoor geen toegang");
	}
	
	public void checkDate(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		Calendar deadline = (Calendar)session.getAttribute("deadline");
		if(deadline == null) {
			timeHasCome = false;
			return;
		}
		if(deadline.getTime().before(new Date())) {
			timeHasCome = true;
		}
	}

//	private void resetSomeSessions(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		session.removeAttribute("success");
//		session.removeAttribute("errors");
//	}


	/*********************
	 * Getters & Setters *
	 *********************/

	public RoleEnum[] getAccesList() {
		return null;
	}

	public void setService(Service service) {
		this.service = service;
	}

	protected Service getService() {
		return service;
	}

	protected boolean getEnoughSpots() {
		return this.enoughSpots;
	}

	protected void setEnoughSpots(boolean value) {
		this.enoughSpots = value;
	}

	protected boolean getTimeHasCome() {
		return this.timeHasCome;
	}
}
