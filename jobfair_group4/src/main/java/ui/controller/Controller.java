package ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.service.Service;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Service service;
	private HandlerFactory factory;

	
	@Override
	public void init() throws ServletException{
		super.init();
		System.out.println("init");
		ServletContext context = getServletContext();

		Properties properties = new Properties();
		Enumeration<String> parameterNames = context.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String propertyName = parameterNames.nextElement();
			properties.setProperty(propertyName, context.getInitParameter(propertyName));
		}

		service = new Service(properties);
		
		try {
			InputStream input = context.getResourceAsStream("/WEB-INF/handlers.xml");
			Properties handlerProperties = new Properties();
			handlerProperties.loadFromXML(input);

			factory = new HandlerFactory(handlerProperties, service);
		} catch (Exception e) {
			
		}
	}

	@Override
	public void destroy() {
		service.close();

		super.destroy();
	}

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");

		if (action == null) {
			action = "";
		}

		RequestHandler handler = factory.getHandler(action);
		handler.handle(request, response);
		
	}
}

