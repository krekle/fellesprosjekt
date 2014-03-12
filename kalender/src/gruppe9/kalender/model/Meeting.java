package gruppe9.kalender.model;

import java.util.ArrayList;

public class Meeting {
	private int meetingId;
	private int creatorId;
	//private String creatorName;
	private String date;
	private String starttime;
	private String endtime;
	private String description;
	private Room room;
	private ArrayList<Person> participants;
	private ArrayList<Notification> notifications;


	public Meeting(int id, int creator, String date, String starttime,
			String endtime, String description, Room room) {
		super();
		this.meetingId = id;
		this.creatorId = creator;
		this.date = date;
		this.starttime = starttime;
		this.endtime = endtime;
		this.description = description;
		this.room = room;
	}
	
	public void addPerson(Person person){
		participants.add(person);
	}
	public void addNotificaton(Notification notification){
		notifications.add(notification);
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}
	public int getId() {
		return meetingId;
	}
	public void setId(int id) {
		this.meetingId = id;
	}
	public int getCreator() {
		return creatorId;
	}
	public void setCreator(int creator) {
		this.creatorId = creator;
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
