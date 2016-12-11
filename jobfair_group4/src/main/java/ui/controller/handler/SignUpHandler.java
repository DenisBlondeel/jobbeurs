package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;
import domain.model.RoleEnum;
import domain.model.User;

public class SignUpHandler extends RequestHandler {
	
	private String tempPass;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		List<String> errors = new ArrayList<>();
		errors = checkInputValues(request, user);
		if(errors.size()>0){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		} else {
			String succes = "Het bedrijf " + user.getCompanyName() + " is toegevoegd.";
			request.setAttribute("success", succes);
			this.getService().addUser(user);
			if (!this.getTimeHasCome()) {
				try {
					new EmailSender().sendNewCompanyMail(user.getUserID(), tempPass, user.getEmail());
				} catch (MessagingException e) {
					throw new ServletException(e.getMessage(), e);
				}
			}
		    request.getRequestDispatcher("Controller?action=admin").forward(request, response);
		}
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}


	private List<String> checkInputValues(HttpServletRequest request, User user) {
		List<String> errors = new ArrayList<>();
		userSetCompany(user, request, errors);
		userSetName(user, request, errors);
		userSetEmail(user, request, errors);
		userSetId(user, request, errors);
		userSetPassword(user, request, errors);
		return errors;
	}

	private void userSetCompany(User user, HttpServletRequest request, List<String> errors) {
		String companyName = request.getParameter("companyName");
		request.setAttribute("prevCompany", companyName);
		try{
			user.setCompanyName(companyName);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void userSetId(User user, HttpServletRequest request, List<String> errors) {
		String companyName = request.getParameter("companyName");
		try{
			user.generateUserId(companyName);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void userSetPassword(User user, HttpServletRequest request, List<String> errors) {
		try{
			tempPass = user.generatePassword();
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void userSetEmail(User user, HttpServletRequest request, List<String> errors) {
		String email = request.getParameter("email");
		request.setAttribute("prevEmail", email);
		try{
			user.setEmail(email);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void userSetName(User user, HttpServletRequest request, List<String> errors) {
		String contactName = request.getParameter("contactName");
		request.setAttribute("prevContactName", contactName);
		try{
			user.setContactName(contactName);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}
}
