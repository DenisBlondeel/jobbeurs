package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.User;

public class UpdateAccountHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<String> errors = new ArrayList<>();
		errors = checkInputvalues(user, request);
		if(!errors.isEmpty()){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("Controller?action=myaccount").forward(request, response);
		} else {
			service.updateUser(user);
			request.setAttribute("success", "Uw gegevens werden aangepast.");
			request.getRequestDispatcher("Controller?action=home").forward(request, response);
		}
	}

	private List<String> checkInputvalues(User user, HttpServletRequest request) {
		List<String> errors = new ArrayList<>();
		userSetName(user, request, errors);
		userSetEmail(user, request, errors);
		userSetPassword(user, request, errors);
		return errors;
	}

	private void userSetPassword(User user, HttpServletRequest request, List<String> errors) {
		String currPass = request.getParameter("currpass");
		if(service.getUserIfAuthenticated(user.getUserID(), currPass) != null){
			String newPass = request.getParameter("newpass");
			String repPass = request.getParameter("reppass");
			if(newPass.equals(repPass)){
				try{
					user.setPassword(newPass);
					user.setPasswordHashed(newPass);
				} catch (IllegalArgumentException e){
					errors.add(e.getMessage());
				}
			} else {
				errors.add("Nieuwe wachtwoord en herhaalde wachtwoord komen niet overeen.");
			}
		} else {
			errors.add("Huidige wachtwoord is niet correct.");
		}
	}

	private void userSetEmail(User user, HttpServletRequest request, List<String> errors) {
		String email = request.getParameter("email");
		request.setAttribute("email", email);
		try{
			user.setEmail(email);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void userSetName(User user, HttpServletRequest request, List<String> errors) {
		String contactName = request.getParameter("contactname");
		request.setAttribute("contactname", contactName);
		try{
			user.setContactName(contactName);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

}
