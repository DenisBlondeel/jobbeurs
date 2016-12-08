package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.model.CsvReader;
import domain.model.User;

public class UploadHandler extends RequestHandler{
	
	CsvReader reader = new CsvReader();
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    
	    List<String> errors = new ArrayList<>();
	    List<User> users = reader.read(filePart.getInputStream(), errors);
	    for (User user : users) {
			service.addUser(user);
		}
	    request.setAttribute("errors", errors);
	    request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
		
}
