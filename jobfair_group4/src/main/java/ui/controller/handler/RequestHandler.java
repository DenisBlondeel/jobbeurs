package ui.controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.service.SpotService;

public abstract class RequestHandler {

	private SpotService service;

	public RequestHandler() {}

	public final void handle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method
	}

	public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response);

	public void setService(SpotService service) {
		this.service = service;
	}

	protected SpotService getService() {
		return service;
	}
}
