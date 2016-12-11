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

public class AddAdminHandler extends RequestHandler {
	
	private String tempPass;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		List<String> result = new ArrayList<>();
		result = checkInputValues(request, user);
		if(result.size()>0){
			request.setAttribute("errors", result);
			request.getRequestDispatcher("addAdmin.jsp").forward(request, response);
		} else {
			//TODO give success with a session
			String success = "De beheerder " + user.getUserID() + " is toegevoegd.";
			request.setAttribute("success", success);

			user.setRole(RoleEnum.ADMIN);
			this.getService().addUser(user);
			try {
				new EmailSender().sendNewAdminMail(user.getUserID(), tempPass, user.getEmail());
			} catch (MessagingException e) {
				throw new ServletException(e.getMessage(), e);
			}
			response.sendRedirect("Controller?action=admin");
		}
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}

	private List<String> checkInputValues(HttpServletRequest request, User user) {
		List<String> errors = new ArrayList<>();
		userSetId(user, request, errors);
		userSetEmail(user, request, errors);
		userSetPassword(user, request, errors);
		userSetName(user, request);
		return errors;
	}

	private void userSetId(User user, HttpServletRequest request, List<String> result) {
		String userID = request.getParameter("userID");
		request.setAttribute("prevID", userID);
		try{
			user.setUserID(userID);
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

	private void userSetPassword(User user, HttpServletRequest request, List<String> result) {
		try{
			tempPass = user.generatePassword();
		} catch (IllegalArgumentException e){
			result.add(e.getMessage());
		}
	}

	private void userSetName(User user, HttpServletRequest request) {
		String contactName = request.getParameter("contactName");
		request.setAttribute("prevContactName", contactName);
		user.setContactNameFromDb(contactName);
	}
}
