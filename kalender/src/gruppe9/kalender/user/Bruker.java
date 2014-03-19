package gruppe9.kalender.user;

import java.util.ArrayList;

import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Group;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Notification;
import gruppe9.kalender.model.Person;


public class Bruker {
	
	private Person bruker;
	
	private ArrayList<Meeting> avtaler;
	
	private ArrayList<Alert> varsler;
	private ArrayList<Notification> notifications;

	private ArrayList<Person> allPeople;
	private ArrayList<Group> groups;

	private static Bruker instance = null;
	
	private Bruker() {
		// private constructor for singleton-pattern
	}

	public static Bruker getInstance() {
		if (instance == null) 
		{
			instance = new Bruker();
		}
		return instance;
	}

	public ArrayList<Person> getAllPeople() {
		return allPeople;
	}

	public void setAllPeople(ArrayList<Person> allPeople) {
		this.allPeople = allPeople;
	}
	
	public ArrayList<Alert> getVarsler() {
		return varsler;
	}

	public void setVarsler(ArrayList<Alert> varsler) {
		this.varsler = varsler;
	}
	
	public void setUser(Person p){
		this.bruker = p;
	}
	
	public Person getUser(){
		return this.bruker;
	}
	
	public ArrayList<Meeting> getAvtaler() {
		return avtaler;
	}

	public void setAvtaler(ArrayList<Meeting> avtaler) {
		System.out.println("AVTALER ADDED IN BRUKER");
		this.avtaler = avtaler;
	}

	public void setNotifications(ArrayList<Notification> notificationList) {
		this.notifications = notificationList;
	}

	public ArrayList<Notification> getNotifications() {
		return notifications;
	}

	public void setGroups(ArrayList<Group> groups){
		this.groups = groups;
	}
	
	public ArrayList<Group> getGroups() {
		return groups;

	}
	
	//For å bruke metoder fra bruker kall Bruker.getInstance().*
	//* = any method, med dette pattern så kan instansen av denne klassen nåes fra hvor som helst.
}