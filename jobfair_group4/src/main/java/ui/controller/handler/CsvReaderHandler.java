package ui.controller.handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.CsvReader;
import domain.model.User;

public class CsvReaderHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CsvReader reader = new CsvReader();
		List<User> users = reader.read();
		for (User user : users) {
			service.addUser(user);
		}
	}

}
