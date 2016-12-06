package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	
	private String userID;
	private String contactName;
	private String companyName;
	private String email;
	private String password;
	private String salt;
	private RoleEnum role;

	public User(){
		this.setRole(RoleEnum.COMPANY);
	}
	
	public User(String userID, String contactName, String companyName, String email, String password){
		this.setUserID(userID);
		this.setContactName(contactName);
		this.setCompanyName(companyName);
		this.setEmail(email);
		this.setPassword(password);
		this.setRole(RoleEnum.COMPANY);
	}
	
	public User(String userID, String contactName, String companyName, String email, String password, String salt, RoleEnum role){
		this.setUserID(userID);
		this.setContactName(contactName);
		this.setCompanyName(companyName);
		this.setEmail(email);
		this.setPassword(password);
		this.setSalt(salt);
		this.setRole(role);
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		if(userID == null || userID.isEmpty()){
			throw new IllegalArgumentException("Er is geen userID gegeven");
		}
		this.userID = userID;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email == null || email.isEmpty()){
			throw new IllegalArgumentException("Er is geen email gegeven");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email heeft niet het juiste formaat");
		}
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("Geen wachtwoord gegeven");
		}
		try {
			return getPassword().equals(hashPassword(password));
		} catch (Exception e) {
			throw new IllegalArgumentException("Systeem kon geen hashed wachtwoord creeren");
		}
	}

	public void setPassword(String password) {
		if(password == null || password.isEmpty()){
			throw new IllegalArgumentException("Gelieve een wachtwoord op te geven");
		}
		this.password = password;
	}

	public void setPasswordHashed(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("Geen wachtwoord gegeven");
		}
		try {
			if (this.getSalt() == null) {
				this.createSalt();
			}
			this.password = this.hashPassword(password);
		} catch (Exception e) {
			throw new IllegalArgumentException("Systeem kon geen hashed wachtwoord creeren");
		}
	}
	
	public String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();

		crypt.update(this.getSalt().getBytes("UTF-8"));
		crypt.update(password.getBytes("UTF-8"));
		
		byte[] digest = crypt.digest();
		return new BigInteger(1, digest).toString(16);
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		if(salt == null || salt.isEmpty()){
			throw new IllegalArgumentException("De salt voor het inloggen is niet ingevuld");
		}
		this.salt = salt;
	}

	private void createSalt() {
		SecureRandom random = new SecureRandom();
		byte seed[] = random.generateSeed(20);
		this.setSalt(new BigInteger(1, seed).toString(16));
	}

	public RoleEnum getRole() {
		return this.role;
	}

	public void setRole(RoleEnum role) {
		if(role == null){
			throw new IllegalArgumentException("De role van de user is niet gedefinieerd");
		}
		this.role = role;
	}

	public void setRole(String role) {
		if(role == null || role.isEmpty()){
			throw new IllegalArgumentException("De role van de user is niet gedefinieerd");
		}
		try {
			this.role = RoleEnum.valueOf(role.toUpperCase());
		} catch (Exception e) {
			throw new IllegalArgumentException("De gegeven role bestaat niet");
		}
	}
}
