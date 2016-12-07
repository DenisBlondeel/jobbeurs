package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.User;

public class SignUpHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		List<String> result = new ArrayList<>();
		result = checkInputValues(request, user);
		if(result.size()>0){
			request.setAttribute("errors", result);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} else {
			this.getService().addUser(user);
			response.sendRedirect("admin.jsp");
		}
	}

	private List<String> checkInputValues(HttpServletRequest request, User user) {
		List<String> result = new ArrayList<>();
		userSetCompany(user, request, result);
		userSetName(user, request, result);
		userSetEmail(user, request, result);
		userSetId(user, request, result);
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
		try{
			user.setUserID(companyName);
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
}
