package ui.controller.handler;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetDeadlineHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String date = request.getParameter("datum");
		String[] elements = date.split("-");
		
		Calendar calendar = null;
		calendar.set(Calendar.YEAR, Integer.parseInt(elements[2]));
		calendar.set(Calendar.MONTH, Integer.parseInt(elements[1]));
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(elements[0]));
		deadline = calendar;
		
	}

}
