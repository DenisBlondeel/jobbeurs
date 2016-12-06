package domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	
	private String userId, contactName, companyName, email, password, salt;
	
	public User(){
		
	}
	
	public User(String userId, String contactName, String companyName, String email, String password){
		
	}
	
	public User(String userId, String contactName, String companyName, String email, String password, String salt){
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		if(userId == null || userId.isEmpty()){
			throw new IllegalArgumentException("");
		}
		this.userId = userId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		if(contactName == null || contactName.isEmpty()){
			throw new IllegalArgumentException("Gelieve een contactpersoon op te geven");
		}
		this.contactName = contactName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		if(companyName == null || companyName.isEmpty()){
			throw new IllegalArgumentException("Gelieve de naam van je bedrijf in te vullen");
		}
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password == null || password.isEmpty()){
			throw new IllegalArgumentException("Gelieve een wachtwoord op te geven");
		}
		this.password = password;
	}
	
	public String hashPassword(String password){
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	

}
