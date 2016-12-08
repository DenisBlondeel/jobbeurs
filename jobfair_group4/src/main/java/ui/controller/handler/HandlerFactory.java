package ui.controller.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import domain.service.Service;

public class HandlerFactory {

	private Map<String, RequestHandler> handlers = new HashMap<>();

	public HandlerFactory(Properties properties, Service service) {
		for(Object key : properties.keySet()) {
			RequestHandler handler = null;
			String handlerName = properties.getProperty((String) key);
			try {
				Class<?> handlerClass = Class.forName(handlerName);
				Object handlerObject = handlerClass.newInstance();
				handler = (RequestHandler) handlerObject;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Ge zijt nen loeser ! 1");
			} catch (InstantiationException e) {
				e.printStackTrace();
				System.out.println("Ge zijt nen loeser ! 2");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				System.out.println("Ge zijt nen loeser ! 3");
			}
			handler.setService(service);
			handlers.put((String) key, handler);
		}
	}

	public RequestHandler getHandler(String handler) {
		return handlers.get(handler);
	}
}
