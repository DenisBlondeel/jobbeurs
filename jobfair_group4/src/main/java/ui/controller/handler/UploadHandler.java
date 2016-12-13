package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import domain.model.CsvReader;
import domain.model.RoleEnum;

public class UploadHandler extends RequestHandler{
	
	CsvReader reader = new CsvReader();
	
	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    List<String> errors = new ArrayList<>();
	    
		try {
			reader.read(errors, filePart.getInputStream(), getService());
		} catch (MessagingException e) {
			throw new ServletException(e.getMessage(), e);
		}
		if (errors != null) {
			request.setAttribute("errors", errors);
		}
	    request.getRequestDispatcher("Controller?action=admin").forward(request, response);
	}


	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}

}
