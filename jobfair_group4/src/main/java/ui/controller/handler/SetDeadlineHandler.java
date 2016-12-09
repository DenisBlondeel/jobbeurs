package ui.controller.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetDeadlineHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		List<String> errors = new ArrayList<>();
		this.setDeadline(request, errors);
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
		} else {
			Date date = deadline.getTime();
			String dateStr = new SimpleDateFormat("EEEE").format(date) + " "
					+ new SimpleDateFormat("dd").format(date) + " "
					+ new SimpleDateFormat("MMMM").format(date) + " "
					+ new SimpleDateFormat("yyyy").format(date);
			request.setAttribute("success", "Je hebt de deadline gewijzigd naar: " + dateStr);
		}

		request.getRequestDispatcher("Controller?action=admin").forward(request, response);
	}

	private void setDeadline(HttpServletRequest request, List<String> errors) {
		String date = request.getParameter("datum");

		if (date == null || date.trim().isEmpty()) {
			errors.add("Geen datum meegegeven.");
			this.setDeadline(null);
			return;
		}

		if (!date.contains("-")) {
			errors.add("De gegeven datum heeft niet het juiste formaat: \"dag-maand-jaar\"");
			this.setDeadline(null);
			return;
		}

		String[] elements = date.split("-");
		if (elements.length != 3) {
			errors.add("De gegeven datum heeft niet het juiste formaat: \"dag-maand-jaar\"");
			deadline = null;
			return;
		}
		
		Calendar calendar = Calendar.getInstance();
		try {
			int year = Integer.parseInt(elements[2]);
			int month = Integer.parseInt(elements[1]) - 1;
			int day = Integer.parseInt(elements[0]);
			calendar.set(year, month, day);
			this.setDeadline(calendar);
		} catch (NumberFormatException e) {
			errors.add("De gegeven datum bestond niet uit enkel cijfers gescheiden door "
					+ "een '-'. Gelieve een juiste datum mee te geven.");
		}

	}
}
