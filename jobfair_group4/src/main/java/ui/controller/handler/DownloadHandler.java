package ui.controller.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.RoleEnum;
import domain.model.Spot;
import domain.model.User;

public class DownloadHandler extends RequestHandler{
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/csv");
        String reportName =  "Inschrijvingen_Jobbeurs_"+sdf.format(new Date())+".csv";
        response.setHeader("Content-disposition", "attachment; " +
                "filename=\"" + reportName + "\"");

        ArrayList<String> rows = new ArrayList<String>();
        List<User> users = service.getAllCompaniesAlphabeticallyOnCompany();
		rows.add("Bedrijfsnaam;Naam contactpersoon;E-mailadres contactpersoon;Plaatsnummer;Aantal stoelen;Aantal tafels;Elektriciteit;Opmerkingen\n");

        for (User user : users) {
        	Spot spot = getService().getSpotFromUser(user.getUserID());
        	if (spot!=null) {
        		rows.add(user.getCompanyName()+";"+user.getContactName()+";"+user.getEmail()+";"
        			+ spot.getSpotID() + ";" + spot.getAmountChairs() + ";" + spot.getAmountTables() + ";"
        			+ spot.getElectricity() + ";" + spot.getRemarks() + "\n");
        	} else {
        		rows.add(user.getCompanyName()+";"+user.getContactName()+";"+user.getEmail()+";;;;;\n");
        	}
        }

        try {
        	OutputStream outputStream = response.getOutputStream();
            Iterator<String> iter = rows.iterator();
            while (iter.hasNext()){
                String outputString = (String) iter.next();
                outputStream.write(outputString.getBytes());
            }
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
        	throw new ServletException(e.getMessage(), e);
        }

		request.getRequestDispatcher("Controller?action=admin").forward(request, response);
	}

	@Override
	public RoleEnum[] getAccesList() {
		return new RoleEnum[]{RoleEnum.ADMIN};
	}
}
