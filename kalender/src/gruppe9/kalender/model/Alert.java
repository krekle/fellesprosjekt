package gruppe9.kalender.model;

public class Alert {
	
	private String time;
	private String type;
	private String desciption;
	private int meetingID;
	private String sound;
	
	public Alert(String time, String type, String desc, int meetingId, String sound){
		this.time = time;
		this.type = type;
		this.desciption = desc;
		this.meetingID = meetingId;
		this.sound = sound;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public int getMeetingID() {
		return meetingID;
	}
	public void setMeetingID(int meetingID) {
		this.meetingID = meetingID;
	}
	public String getSound() {
		return sound;
	}
	public void setSound(String sound) {
		this.sound = sound;
	}
	
}
