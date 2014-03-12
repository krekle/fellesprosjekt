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
}
