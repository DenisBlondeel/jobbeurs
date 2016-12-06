package domain.model;

public class Spot {

	private String spotID;
	private int amountTables = 0;
	private int amountChairs = 0;
	private boolean electricity = false;
	private String remarks = null;
	private User user = null;

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
		// TODO Auto-generated method
		this.spotID = spotID;
	}

	public int getAmountTables() {
		return amountTables;
	}

	public void setAmountTables(int amountTables) {
		// TODO Auto-generated method
		this.amountTables = amountTables;
	}

	public int getAmountChairs() {
		return amountChairs;
	}

	public void setAmountChairs(int amountChairs) {
		// TODO Auto-generated method
		this.amountChairs = amountChairs;
	}

	public boolean isElectricity() {
		return electricity;
	}

	public void setElectricity(boolean value) {
		// TODO Auto-generated method
		this.electricity = value;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		// TODO Auto-generated method
		this.remarks = remarks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		// TODO Auto-generated method
		this.user = user;
	}

}
