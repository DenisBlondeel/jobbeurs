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

public class UpdateAccountHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String password = request.getParameter("password");
		if (password == null || password.isEmpty()) {
			request.setAttribute("errors", "Je bent verplicht om je wachtwoord in te geven vooraleer de wijzigingen doorgevoerd kunnen worden.");
			request.getRequestDispatcher("myaccount.jsp").forward(request, response);
			return;
		}

		if (!user.isCorrectPassword(password)) {
			request.setAttribute("errors", "Verkeerd wachtwoord.");
			request.getRequestDispatcher("myaccount.jsp").forward(request, response);
			return;
		}

		List<String> errors = new ArrayList<>();
		errors = checkInputvalues(user, request);

		if(!errors.isEmpty()){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("myaccount.jsp").forward(request, response);
		} else {
			service.updateUser(user);
			request.setAttribute("success", "Uw gegevens werden aangepast.");
			request.getRequestDispatcher("Controller?action=home").forward(request, response);
		}
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN, RoleEnum.COMPANY};
	}

	private List<String> checkInputvalues(User user, HttpServletRequest request) {
		List<String> errors = new ArrayList<>();
		userSetName(user, request, errors);
		userSetEmail(user, request, errors);
		return errors;
	}
	
	private void userSetEmail(User user, HttpServletRequest request, List<String> errors) {
		String email = request.getParameter("email");
		try{
			user.setEmail(email);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}

	private void userSetName(User user, HttpServletRequest request, List<String> errors) {
		String contactName = request.getParameter("contactname");
		try{
			user.setContactName(contactName);
		} catch (IllegalArgumentException e){
			errors.add(e.getMessage());
		}
	}
}
