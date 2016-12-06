package ui.controller.handler;

import java.util.Map;
import java.util.Properties;

import domain.service.SpotService;

public class HandlerFactory {

	private Map<String, RequestHandler> handlers;

	public HandlerFactory(Properties properties, SpotService service) {
		for(Object key : properties.keySet()) {
			RequestHandler handler = null;
			String handlerName = properties.getProperty((String) key);
			try {
				Class<?> handlerClass = Class.forName(handlerName);
				Object handlerObject = handlerClass.newInstance();
				handler = (RequestHandler) handlerObject;
			} catch (ClassNotFoundException e) {
			} catch (InstantiationException e) {
			} catch (IllegalAccessException e) {
			}
			handler.setService(service);
			handlers.put((String) key, handler);
		}
	}

	public RequestHandler getHandler(String handler) {
		return handlers.get(handler);
	}
}
