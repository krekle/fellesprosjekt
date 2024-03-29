package gruppe9.kalender.model;

import gruppe9.kalender.client.ApiCaller;
import gruppe9.kalender.client.CalResponse;
import gruppe9.kalender.client.Database;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.DefaultListModel;

public class Meeting implements Comparable<Meeting>, ApiCaller{
	
	private String name;
	private int meetingId;
	private int creatorId;
	//private String creatorName;
	private String start;
	private String end;
	private String description;
	private int roomId;
	private String place = "NA";
	private Group group = null;
	private String myStatus;
	
	private ArrayList<Person> participants;
	private ArrayList<Notification> notifications;
	private Alert emailAlert,soundAlert;

	
	public String toString(){
		String str = "";
		str += "meetingId: " + String.valueOf(meetingId);
		str += " creatorId: " + String.valueOf(creatorId);
		str += " start" + start + " end" + end;
		str += " NrOfparticipants: " + participants.size();
		return str;
	}

	public Meeting(int meetingId, int creatorId, String start, String end,
			String description, int roomId, String title, String status) {
		super();
		this.meetingId = meetingId;
		this.creatorId = creatorId;
		this.start = start;
		this.end = end;
		this.description = description;
		this.roomId = roomId;
		this.name = title;
		
		this.myStatus = ((status != null)? status:"IkkeSvart");
		
		participants = new ArrayList<Person>();
		Database.getParticipants(this, this);
		notifications = new ArrayList<Notification>();
		emailAlert = null;
		soundAlert = null;
	}

	public String getMyStatus() {
		return myStatus;
	}

	public void setMyStatus(String myStatus) {
		this.myStatus = myStatus;
	}

	public String getDuration(){
		int minutes = 0;
		minutes += ((Integer.parseInt(this.getEndTime().substring(0,2))*60) + Integer.parseInt(this.getEndTime().substring(3)));
		minutes -= (Integer.parseInt(this.getStartTime().substring(0,2))*60) + Integer.parseInt(this.getStartTime().substring(3));
		int hours = (int) Math.floor(minutes/60);
		minutes = minutes%60;
		if (minutes >= 10){
			return String.valueOf(hours) + ":" + String.valueOf(minutes);
		}
		else{
			return String.valueOf(hours) + ":0" + String.valueOf(minutes);
		}
		
	}
	
	public int getYear(){
		return Integer.parseInt(start.substring(0,4));
	}
	public int getMonth(){
//		System.out.println(start.substring(5,7));
		return Integer.parseInt(start.substring(5,7));
	}
	public int getDayOfMonth(){
		return Integer.parseInt(start.substring(8,10));
	}
	
	public String getStartTime(){
		return start.substring(11,16);
	}
	
	public void setStartTime(String time){
		this.start = this.start.substring(0, 11) + time + ":00";
	}
	
	public String getEndTime(){
		return end.substring(11, 16);
	}
	public void setEndTime(String time){
		this.end = this.end.substring(0, 11) + time + ":00";
	}
	
	public int getDayOfWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,getYear());
		calendar.set(Calendar.MONTH,getMonth() - 1);
		calendar.set(Calendar.DATE,getDayOfMonth());

		return (calendar.get(Calendar.DAY_OF_WEEK) - 1);
	}
	
	public int getWeekOfYear(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,getYear());
		calendar.set(Calendar.MONTH,getMonth() - 1);
		calendar.set(Calendar.DATE,getDayOfMonth());
		

		return (calendar.get(Calendar.WEEK_OF_YEAR));
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
	public String getStart() {
		return start.replace(" ", "-");
	}
	public void setStart(String start) {
		System.out.println("Start set to.... "+ start);
		this.start = start;
	}
	public String getEnd() {
		return end.replace(" ", "-");
	}
	public void setEnd(String end) {
		this.end = end;
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
	
	public String getStringParticipants(){ // Returns string of participants IDs
		String idStr = "";
		for (int i = 0; i < this.participants.size(); i++) {
			if ((i+1) == this.participants.size()){
				idStr += participants.get(i).getId();
			} else {
				idStr += participants.get(i).getId() + ",";				
			}
		}
		return idStr;
	}
	
	public DefaultListModel<Person> getParticipantListModel(){
		DefaultListModel participantListModel = new DefaultListModel();
		for (int i = 0; i < this.participants.size(); i++){
//			System.out.println(participants.get(i).getName());
			participantListModel.addElement(participants.get(i));
		}
		return participantListModel;
			
	}
	
	public ArrayList<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}

	@Override
	public int compareTo(Meeting o) {
		if (o.start.equals("None") || o.end.equals("None") || start.equals("None") || end.equals("None")) {
			return 0;
		}
		int dateDiff = Integer.parseInt(this.getStart().substring(0,10).replace("-", "")) - Integer.parseInt(o.getStart().substring(0,10).replace("-", ""));
		if (dateDiff !=0){
			return dateDiff;
		}
		int startDiff = Integer.parseInt(this.getStartTime().replace(":", "")) - Integer.parseInt(o.getStartTime().replace(":", ""));
		if (startDiff != 0 ){
			return startDiff;
		}
		int endDiff = Integer.parseInt(this.getEndTime().replace(":", "")) - Integer.parseInt(o.getEndTime().replace(":", ""));
		if (endDiff != 0) {
			return endDiff;
		}
		return 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void callBack(CalResponse response) 
	{
		ArrayList<Deltaker> pe = response.getDeltakere();
	}		
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	public void setGroup(Group group)
	{
		this.group = group;	
	}
	public Group getGroup(){
		return group;
	}
}
