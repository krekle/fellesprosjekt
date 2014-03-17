package gruppe9.kalender.model;

public class Deltaker {
	private int personID, avtaleID;
	private String status, sistSett;
	
	public Deltaker(int personId, int avtaleId, String status, String sistSett){
		this.personID = personId;
		this.avtaleID = avtaleId;
		this.status = status;
		this.sistSett = sistSett;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getAvtaleID() {
		return avtaleID;
	}

	public void setAvtaleID(int avtaleID) {
		this.avtaleID = avtaleID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSistSett() {
		return sistSett;
	}

	public void setSistSett(String sistSett) {
		this.sistSett = sistSett;
	}
	
	
}
