package ui.controller.handler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.User;

public class DownloadHandler extends RequestHandler{
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/csv");
        String reportName =  "Inschrijvingen_Jobbeurs_"+getYear()+".csv";
        response.setHeader("Content-disposition", "attachment; " +
                "filename=" + reportName);   

        ArrayList<String> rows = new ArrayList<String>();
        List<User> users = service.getAllCompanies();

        for (User user : users) {
            rows.add(user.getCompanyName()+";"+user.getContactName()+";;"+user.getEmail()+"\n");
        }

        Iterator<String> iter = rows.iterator();
        while (iter.hasNext()){
            String outputString = (String) iter.next();
            response.getOutputStream().print(outputString);
        }

        response.getOutputStream().flush();
		
		request.getRequestDispatcher("Controller?action=admin").forward(request, response);
	}

	private String getYear() {
		Date date = new Date();
		String currentDate = sdf.format(date);
		String year = "";
		for(int i=0; i<4; i++){
			year += currentDate.charAt(i);
		}
		return year;
	}
}
