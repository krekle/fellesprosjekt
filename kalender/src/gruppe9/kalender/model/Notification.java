package gruppe9.kalender.model;

public class Notification {
	private String description;
	private Person personCausing;
	private String time;
	
	
	public Notification(String description, Person personCausing, String time) {
		super();
		this.description = description;
		this.personCausing = personCausing;
		this.time = time;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Person getPersonCausing() {
		return personCausing;
	}
	public void setPersonCausing(Person personCausing) {
		this.personCausing = personCausing;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
