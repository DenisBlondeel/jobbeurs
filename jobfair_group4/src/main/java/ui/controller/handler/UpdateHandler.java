package ui.controller.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Spot;

public class UpdateHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String checked = "checked";
		String spotnr = request.getParameter("spotnr");
		Spot spot = service.getSpot(spotnr);
		switch(spot.getAmountChairs()){
		case 0:
			request.setAttribute("ch0", checked);
			request.setAttribute("ch1", "");
			request.setAttribute("ch2", "");
			break;
		case 1:
			request.setAttribute("ch0", "");
			request.setAttribute("ch1", checked);
			request.setAttribute("ch2", "");
			break;
		case 2:
			request.setAttribute("ch0", "");
			request.setAttribute("ch1", "");
			request.setAttribute("ch2", checked);
			break;
		default:
			request.setAttribute("ch0", "");
			request.setAttribute("ch1", "");
			request.setAttribute("ch2", checked);
		}
		if(spot.getAmountTables() == 0){
			request.setAttribute("tb0", checked);
			request.setAttribute("tb1", "");
		} else {
			request.setAttribute("tb0", "");
			request.setAttribute("tb1", checked);
		}
		if(spot.getElectricity()){
			request.setAttribute("el", checked);
		} else {
			request.setAttribute("el", "");
		}
		request.setAttribute("extra", spot.getRemarks());
		request.setAttribute("spotnr", spotnr);
		response.sendRedirect("updatespot.jsp");
	}

}
