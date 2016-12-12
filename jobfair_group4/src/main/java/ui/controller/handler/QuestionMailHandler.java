package ui.controller.handler;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;

public class QuestionMailHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String to = service.getUser(request.getParameter("adminID")).getEmail();
		String from = request.getParameter("name");
		String subj = request.getParameter("subject");
		String msg =  request.getParameter("message");
		if(to == null || from == null || subj == null || msg == null){
			request.setAttribute("errors", "Gelieve alle velden in te vullen");
			request.setAttribute("from", from);
			request.setAttribute("subj", subj);
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("Controller?action=contact").forward(request, response);
		} else {
			EmailSender es = new EmailSender();
			try{
				es.sendQuestionMail(to, from, subj, msg);
			} catch (MessagingException e){
				
			}
			request.setAttribute("success", "Uw vraag / opmerking werd verzonden.");
			request.getRequestDispatcher("contact.jsp").forward(request, response);
		}
	}
}
