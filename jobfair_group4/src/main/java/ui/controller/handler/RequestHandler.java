package ui.controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {

	public RequestHandler() {}

	public final void handle(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method
	}

	public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response);

}
