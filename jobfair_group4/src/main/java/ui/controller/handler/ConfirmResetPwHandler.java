package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.DbException;
import domain.model.EmailSender;
import domain.model.User;

public class ConfirmResetPwHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submit = request.getParameter("submit");
		if(submit.equals("Annuleer")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);			
			return;
		}
		if(submit.equals("Reset")){
			List<String> errors = new ArrayList<>();
			String userID = request.getParameter("userID");
			User user = getUserById(userID, errors);
			if(errors.size()>0){
				request.setAttribute("prevUserid", userID);
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("resetpw.jsp").forward(request, response);
				return;
			}

			String password = user.generatePassword();
			service.updateUser(user);

			try {
				new EmailSender().sendResetPasswordMail(user, password, user.getEmail());
			} catch (MessagingException e) {
				throw new ServletException(e.getMessage(), e);
			}

			request.setAttribute("success", "Er werd een nieuw wachtwoord verstuurd naar " + user.getEmail());
			request.getRequestDispatcher("Controller?action=home").forward(request, response);
		} else {
			request.getRequestDispatcher("Controller?action=").forward(request, response);
		}
	}

	private User getUserById(String userID, List<String> errors) {
		if (userID == null || userID.isEmpty()) {
			errors.add("Geen gebruiker met de gegeven gebruikersnaam gevonden.");
			return null;
		}
		try {
			User user = service.getUser(userID);
			if (user == null) {
				errors.add("Geen gebruiker met de gegeven gebruikersnaam gevonden.");
			}
			return user;
		} catch (DbException e) {
			errors.add("Geen gebruiker met de gegeven gebruikersnaam gevonden.");
			return null;
		}
	}

}
