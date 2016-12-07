package ui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import domain.model.User;
import domain.service.SpotService;
import ui.controller.handler.HandlerFactory;
import ui.controller.handler.RequestHandler;

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
//			InputStream input = context.getResourceAsStream("src/main/webapp/WEB-INF/handlers.xml");
//			Properties handlerProperties = new Properties();
//			handlerProperties.loadFromXML(input);
//
//			factory = new HandlerFactory(handlerProperties, service);
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
		String action = request.getParameter("action");

		if (action == null) {
			action = "";
		}

//		RequestHandler handler = factory.getHandler(action);
//		handler.handle(request, response);
		
		String destination = "";

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
		case "signup":
			destination = signUp(request, response);
			break;
		default:
			destination = "index.jsp";
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}
	
	private String signUp(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		List<String> result = new ArrayList<>();
		result = checkInputValues(request, user);
		if(result.size()>0){
			request.setAttribute("errors", result);
			return "signup.jsp";
		} else {
			service.addUser(user);
			return "admin.jsp";
		}
	}

	private List<String> checkInputValues(HttpServletRequest request, User user) {
		List<String> result = new ArrayList<>();
		userSetId(user, request, result);
		userSetCompany(user, request, result);
		userSetName(user, request, result);
		userSetEmail(user, request, result);
		userSetPassword(user, request, result);
		return result;
	}

	private void userSetCompany(User user, HttpServletRequest request, List<String> result) {
		String companyName = request.getParameter("companyName");
		request.setAttribute("prevCompany", companyName);
		try{
			user.setCompanyName(companyName);
		} catch (IllegalArgumentException e){
			result.add(e.getMessage());
		}
	}

	private void userSetId(User user, HttpServletRequest request, List<String> result) {
		String companyName = request.getParameter("companyName");
		String userId = user.generateUserId(companyName);
		try{
			user.setUserID(userId);
		} catch (IllegalArgumentException e){
			result.add(e.getMessage());
		}
	}

	private void userSetPassword(User user, HttpServletRequest request, List<String> result) {
		String password = user.generatePassword();
		try{
			user.setPassword(password);
		} catch (IllegalArgumentException e){
			result.add(e.getMessage());
		}
	}

	private void userSetEmail(User user, HttpServletRequest request, List<String> result) {
		String email = request.getParameter("email");
		request.setAttribute("prevEmail", email);
		try{
			user.setEmail(email);
		} catch (IllegalArgumentException e){
			result.add(e.getMessage());
		}
	}

	private void userSetName(User user, HttpServletRequest request, List<String> result) {
		String contactName = request.getParameter("contactName");
		request.setAttribute("prevContactName", contactName);
		try{
			user.setCompanyName(contactName);
		} catch (IllegalArgumentException e){
			result.add(e.getMessage());
		}
	}
	

	/*private String signUp(HttpServletRequest request, HttpServletResponse response)
	{
		User user = new User();
		String companyName = request.getParameter("compagnyName");
		String contactName = request.getParameter("contactName");
		String email = request.getParameter("email");
		String userId = user.generateUserId(companyName);
		String password = user.generatePassword();
		user.setCompanyName(companyName);
		user.setContactName(contactName);
		user.setEmail(email);
		return null;
	}*/

	private String getOccupiedSpots(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "spotoverview.jsp";
		List<Spot> spots = service.getOccupiedSpots();
		request.setAttribute("spots", spots);
		return destination;
	}

	private String getFreeSpots(HttpServletRequest request, HttpServletResponse response)
	{
		String destination = "spotoverview.jsp";
		List<Spot> spots = service.getFreeSpots();
		request.setAttribute("spots", spots);
		return destination;
	}

	private String getSpots(HttpServletRequest request, HttpServletResponse response) {
		List<Spot> spots = service.getAllSpots();
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

