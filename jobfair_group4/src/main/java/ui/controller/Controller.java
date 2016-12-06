package ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Spot;
import domain.service.SpotService;
import ui.controller.handler.HandlerFactory;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SpotService service;
	private HandlerFactory factory;
	
	@Override
	public void init() throws ServletException{
		super.init();

		ServletContext context = getServletContext();

		Properties properties = new Properties();
		Enumeration<String> parameterNames = context.getInitParameterNames();
		while (parameterNames.hasMoreElements()) {
			String propertyName = parameterNames.nextElement();
			properties.setProperty(propertyName, context.getInitParameter(propertyName));
		}

		service = new SpotService(properties);
		
//		try {
//			InputStream input = context.getResourceAsStream("/WEB-INF/handlers.xml");
//			Properties handlerProperties = new Properties();
//			handlerProperties.loadFromXML(input);
//
//			handlerFactory = new HandlerFactory(handlerProperties, service);
//		} catch (Exception e) {
//			
//		}
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
		String action;
		String destination = "";
		
		action = request.getParameter("action");
		
		switch(action)
		{
		case "showopt":
			destination = showOptions(request, response);
			break;
		case "spotoptions":
			destination = spotOptions(request, response);
			break;
		case "spots":
			destination = getSpots(request, response);
			break;
		case "vrijelijst":
			destination = getFreeSpots(request, response);
			break;
		case "bezetlijst":
			destination = getOccupiedSpots(request, response);
			break;
			default:
			destination = "index.jsp";
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}

	private String getOccupiedSpots(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "spotoverview.jsp";
		List<Spot> spots = service.getOccupiedSpot();
		request.setAttribute("spots", spots);
		return destination;
	}

	private String getFreeSpots(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "spotoverview.jsp";
		List<Spot> spots = service.getFreeSpot();
		request.setAttribute("spots", spots);
		return destination;
	}

	private String getSpots(HttpServletRequest request, HttpServletResponse response) {
		List<Spot> spots = service.getAll();
		request.setAttribute("spots", spots);
		return "spotoverview.jsp";
	}

	private String showOptions(HttpServletRequest request, HttpServletResponse response) {
		String spot = request.getParameter("id");
		request.setAttribute("spotnr", spot);
		return "spotoptions.jsp";
	}

	private String spotOptions(HttpServletRequest request, HttpServletResponse response) {
		int chairs = Integer.parseInt(request.getParameter("chairs"));
		int tables = Integer.parseInt(request.getParameter("tables"));
		boolean electricity = false;
		if(request.getParameter("electricity") != null){
			electricity = true;
		}
		String extra = request.getParameter("extra");
		String spot = request.getParameter("id");
		request.setAttribute("spotnr", spot);
		request.setAttribute("reserved", "Uw plaats werd gereserveerd.");
		return "index.jsp";
	}

}
