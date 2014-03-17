package gruppe9.kalender.model;

public class Notification {
	private String description;
	private int meetingId;
	private String time;
	
	
	public Notification(String description, int meetingId, String time) {
		super();
		this.description = description;
		this.meetingId = meetingId;
		this.time = time;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingid) {
		this.meetingId = meetingid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
