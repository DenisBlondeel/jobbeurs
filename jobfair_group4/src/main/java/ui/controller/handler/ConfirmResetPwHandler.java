package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;
import domain.model.User;

public class ConfirmResetPwHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submit = request.getParameter("submit");
		if(submit.equals("Reset")){
			String userid = request.getParameter("userid");
			String email = request.getParameter("email");
			User user = service.getUser(userid);
			String contactName = user.getContactName();
			String companyname = user.getCompanyName();
			String password = user.generatePassword();
			user.setPasswordHashed(password);
			service.updateUser(user);
			EmailSender em = new EmailSender();
			try {
				em.sendResetPasswordMail(contactName, companyname, email, password);
			} catch (MessagingException e) {
				throw new ServletException(e.getMessage(), e);
			}
			request.setAttribute("userid", userid);
			List<String> success = new ArrayList<>();
			success.add("Er werd een nieuw wachtwoord verstuurd naar " + email);
			request.setAttribute("success", success);
			request.getRequestDispatcher("Controller?action=home").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
	}

}
