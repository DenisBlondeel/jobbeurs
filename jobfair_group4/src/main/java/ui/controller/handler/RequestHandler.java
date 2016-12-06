package ui.controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.NotAuthorizedException;

import domain.model.RoleEnum;
import domain.model.User;
import domain.service.SpotService;

public abstract class RequestHandler {

	private SpotService service;

	public RequestHandler() {}

	public final void handle(HttpServletRequest request, HttpServletResponse response) {
		this.checkRole(request);
		
	}

	public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response);

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
		throw new NotAuthorizedException("Insufficient rights");
	}

	public RoleEnum[] getAccesList() {
		return null;
	}

	public void setService(SpotService service) {
		this.service = service;
	}

	protected SpotService getService() {
		return service;
	}
}
