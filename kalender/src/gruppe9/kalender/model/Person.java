package gruppe9.kalender.model;

import java.util.ArrayList;

public class Person {
	private int id;
	private String name;
	private int telephonenumber;
	private String adress;
	private String email;
	private String password;
	private ArrayList<Meeting> meetings;
	private ArrayList<Alert> alerts;
	private ArrayList<Notification> notifications;
}
