package ui.controller.handler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;
import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class ConfirmUpdateHandler extends RequestHandler	{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String spotnr = request.getParameter("spotnr");
		Spot spot = service.getSpot(spotnr);

		int chairs = Integer.parseInt(request.getParameter("chairs"));
		int tables = Integer.parseInt(request.getParameter("tables"));
		boolean electricity = request.getParameter("electricity") != null;
		String extra = request.getParameter("extra");

		spot.setAmountChairs(chairs);
		spot.setAmountTables(tables);
		spot.setElectricity(electricity);
		spot.setRemarks(extra);
		spot.setUser(this.getService().getUser(spot.getUserID()));
		service.updateSpot(spot);

		request.setAttribute("spotnr", spot.getSpotID());
		request.setAttribute("chairs", spot.getAmountChairs());
		request.setAttribute("tables", spot.getAmountTables());
		request.setAttribute("electricity", spot.getElectricity());
		request.setAttribute("extra", spot.getRemarks());
		request.setAttribute("update", "update");

		User user = getService().getUser(spot.getUserID());

		try {
			new EmailSender().sendUpdateMail(spot, user.getCompanyName(), user.getEmail());
		} catch (MessagingException e) {
			throw new ServletException(e.getMessage(), e);
		}

		response.sendRedirect("Controller?action=");
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN, RoleEnum.COMPANY};
	}
}
