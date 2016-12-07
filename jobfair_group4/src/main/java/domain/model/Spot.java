package domain.model;

public class Spot {

	private String spotID;
	private int amountTables = 0;
	private int amountChairs = 0;
	private boolean electricity = false;
	private String remarks = null;
	private User user = null;
	private String userID;

	public Spot(String spotID) {
		this.setSpotID(spotID);
	}

	public Spot(String spotID, int amountTables, int amountChairs, boolean electricity, String remarks, User user) {
		this.setSpotID(spotID);
		this.setAmountTables(amountTables);
		this.setAmountChairs(amountChairs);
		this.setElectricity(electricity);
		this.setRemarks(remarks);
		this.setUser(user);
	}

	public String getSpotID() {
		return spotID;
	}

	public void setSpotID(String spotID) {
		if (spotID == null || spotID.isEmpty()) {
			throw new IllegalArgumentException("Er is geen spotID gegeven");
		}
		this.spotID = spotID;
	}

	public int getAmountTables() {
		return amountTables;
	}

	public void setAmountTables(int amountTables) {
		if (amountTables < 0 || amountTables > 1) {
			throw new IllegalArgumentException("Er is geen valide aantal tafels gegeven");
		}
		this.amountTables = amountTables;
	}

	public int getAmountChairs() {
		return amountChairs;
	}

	public void setAmountChairs(int amountChairs) {
		if (amountChairs < 0 || amountChairs > 2) {
			throw new IllegalArgumentException("Er is geen valide aantal stoelen gegeven");
		}
		this.amountChairs = amountChairs;
	}

	public boolean getElectricity() {
		return electricity;
	}

	public void setElectricity(boolean value) {
		this.electricity = value;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUserID(String userID)
	{
		this.userID = userID;
	}
	
	public String getUserID()
	{
		return userID;
	}

}
