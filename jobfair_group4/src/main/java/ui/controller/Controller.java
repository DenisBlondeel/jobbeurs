package ui.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
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
			default:
			destination = "index.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
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
