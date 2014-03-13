package gruppe9.kalender.model;

import java.util.ArrayList;

public class Meeting {
	private int meetingId;
	private int creatorId;
	//private String creatorName;
	private String starttime;
	private String endtime;
	private String description;
	private int roomId;
	private ArrayList<Person> participants;
	private ArrayList<Notification> notifications;


	public Meeting(int meetingId, int creatorId, String starttime, String endtime,
			String description, int roomId) {
		super();
		this.starttime = starttime;
		this.creatorId = creatorId;
		this.starttime = starttime;
		this.endtime = endtime;
		this.description = description;
		this.roomId = roomId;
	}

	private int getYear(){
		return Integer.parseInt(starttime.substring(0,4));
	}
	private int getMonth(){
		return Integer.parseInt(starttime.substring(5,7));
	}
	private int getDay(){
		return Integer.parseInt(starttime.substring(8,10));
	}
	
	public void addPerson(Person person){
		participants.add(person);
	}
	public void addNotificaton(Notification notification){
		notifications.add(notification);
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
	public int getRoom() {
		return roomId;
	}
	public void setRoom(int room) {
		this.roomId = room;
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
