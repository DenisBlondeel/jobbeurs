package ui.controller.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;

import domain.service.Service;

public class HandlerFactory {

	private Map<String, RequestHandler> handlers = new HashMap<>();

	public HandlerFactory(Properties properties, Service service) throws ServletException {
		for(Object key : properties.keySet()) {
			RequestHandler handler = null;
			String handlerName = properties.getProperty((String) key);
			try {
				Class<?> handlerClass = Class.forName(handlerName);
				Object handlerObject = handlerClass.newInstance();
				handler = (RequestHandler) handlerObject;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e.getMessage(), e);
			}
			handler.setService(service);
			handlers.put((String) key, handler);
		}
	}

	public RequestHandler getHandler(String handler) {
		return handlers.get(handler);
	}
}
