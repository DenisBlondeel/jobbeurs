package ui.controller.handler;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.EmailSender;
import domain.model.User;
import ui.controller.VerifyRecaptcha;

public class QuestionMailHandler extends RequestHandler{

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String to = "";
		String admin = request.getParameter("admin");
		List<User> admins = service.getAdmins();
		for(User ad : admins){
			if(ad.getContactName().equals(admin)){
				to = ad.getEmail();
			}
		}
		String from = request.getParameter("name");
		String subj = request.getParameter("subject");
		String msg =  request.getParameter("message");
		String captcha = request.getParameter("g-recaptcha-response");
		boolean verify = VerifyRecaptcha.verify(captcha);
		if(to.trim().isEmpty() || from.trim().isEmpty() || subj.trim().isEmpty() || msg.trim().isEmpty()){
			request.setAttribute("errors", "Gelieve alle velden in te vullen");
		} else if(!verify){
			request.setAttribute("errors", "Vergeet de captcha niet aan te vinken.");
		} else {
			EmailSender es = new EmailSender();
			try{
				es.sendQuestionMail(to, from, subj, msg);
				request.setAttribute("success", "Je vraag werd verzonden");
			} catch (MessagingException e){
				throw new ServletException(e.getMessage(), e);
			}
		}
		request.setAttribute("from", from);
		request.setAttribute("subj", subj);
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("Controller?action=contact").forward(request, response);
	}
}
