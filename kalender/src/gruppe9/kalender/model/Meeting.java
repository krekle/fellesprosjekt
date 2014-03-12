package gruppe9.kalender.model;

import java.util.ArrayList;

public class Meeting {
	private int id;
	private int creator;
	//private String creatorName;
	private String starttime;
	private String endtime;
	private String description;
	private Room room;
	private ArrayList<Person> participants;
	private ArrayList<Notification> notifications;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreator() {
		return creator;
	}
	public void setCreator(int creator) {
		this.creator = creator;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public ArrayList<Person> getParticipants() {
		return participants;
	}
	public void setParticipants(ArrayList<Person> participants) {
		this.participants = participants;
	}
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	

}
