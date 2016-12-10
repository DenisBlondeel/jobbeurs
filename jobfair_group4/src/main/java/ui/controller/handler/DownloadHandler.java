package ui.controller.handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.CsvWriter;
import domain.model.User;

public class DownloadHandler extends RequestHandler{
	
	private static final int BYTES_DOWNLOAD = 1024;

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = service.getAllCompanies();
		CsvWriter writer = new CsvWriter();
		File csv = writer.write(users);
		String file = csv.getPath();
		
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition",
	                     "attachment;filename=Inschrijvingen_jobbeurs_"+writer.getYear());
		ServletContext ctx = request.getServletContext();
		InputStream is = ctx.getResourceAsStream(file);

		int read=0;
		byte[] bytes = new byte[BYTES_DOWNLOAD];
		OutputStream os = response.getOutputStream();

		while((read = is.read(bytes))!= -1){
			os.write(bytes, 0, read);
		}
		os.flush();
		os.close();
		
		request.getRequestDispatcher("Controller?action=admin").forward(request, response);
	}
}
