package domain.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CsvWriter {
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public void write(ArrayList<User> users){
		
		String fileName = "Inschrijvingen_Jobbeurs_"+getYear();
		File file = new File(fileName);
		try {
			PrintWriter pw = new PrintWriter(file);
			StringBuilder sb = new StringBuilder();
			for(User user : users){
				sb.append(user.getCompanyName());
				sb.append(";");
				sb.append(user.getContactName());
				sb.append(";;");
				sb.append(user.getEmail());
				sb.append("\n");
				pw.write(sb.toString());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
