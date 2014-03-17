package gruppe9.kalender.model;

import java.util.ArrayList;

public class Person {
	private int id;
	private String name;
	private int telephonenumber;
	private String adress;
	private String email;
	private ArrayList<Meeting> meetings;
	private ArrayList<Alert> alerts;
	private ArrayList<Notification> notifications;
	
	
	@Override
	public String toString()
	{
		return this.name + " - " + this.id;
	}
	public Person(int id, String name, int telephonenumber, String adress,
			String email) {
		super();
		this.id = id;
		this.name = name;
		this.telephonenumber = telephonenumber;
		this.adress = adress;
		this.email = email;
	}
	
	
	public void addMeeting(Meeting meeting) {
		meetings.add(meeting);
	}
	public void addAlert(Alert alert){
		alerts.add(alert);
	}
	public void addNotification(Notification notification){
		notifications.add(notification);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTelephonenumber() {
		return telephonenumber;
	}
	public void setTelephonenumber(int telephonenumber) {
		this.telephonenumber = telephonenumber;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Meeting> getMeetings() {
		return meetings;
	}
	public void setMeetings(ArrayList<Meeting> meetings) {
		this.meetings = meetings;
	}
	public ArrayList<Alert> getAlerts() {
		return alerts;
	}
	public void setAlerts(ArrayList<Alert> alerts) {
		this.alerts = alerts;
	}
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	
}
