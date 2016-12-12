package ui.controller.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.model.RoleEnum;
import domain.model.User;

public class UpdatePasswordHandler extends RequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<String> errors = new ArrayList<>();

		this.userSetPassword(user, request, errors);

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("myaccount.jsp").forward(request, response);
		} else {
			service.updateUser(user);
			request.setAttribute("success", "Uw wachtwoord werd aangepast.");
			request.getRequestDispatcher("Controller?action=home").forward(request, response);
		}
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN, RoleEnum.COMPANY};
	}

	private void userSetPassword(User user, HttpServletRequest request, List<String> errors){
		String currPass = request.getParameter("currpass");
		String newPass = request.getParameter("newpass");
		String repPass = request.getParameter("reppass");
		if((currPass==null || currPass.isEmpty()) || (newPass==null || newPass.isEmpty()) || (repPass==null || repPass.isEmpty())){
			errors.add("Niet elk wachtwoord is ingevuld.");
		} else {
			if(!user.isCorrectPassword(currPass)){
				errors.add("Huidige wachtwoord is niet correct.");
			} else if(newPass.equals(repPass)){
				try{
					user.setPasswordHashed(newPass);
				} catch (IllegalArgumentException e){
					errors.add(e.getMessage());
				}
			} else {
				errors.add("Nieuw wachtwoord en herhaalde wachtwoord komen niet overeen.");
			}
		}
	}

}
