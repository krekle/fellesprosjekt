package gruppe9.kalender.model;

//class that makes some test instances

public class ListOfTestmodels {

	
	
	//some person test instances
	
	public Person person0 = new Person(0,"Test person0",22225555,"Trondheim Sentrum","person0@domain.com");
	public Person person1 = new Person(1, "Test person1", 291827291, "Ntnu P15", "person1@domain.com");
	public Person person2 = new Person(2, "Test person2", 28364927, "Høyskoleringen 3", "person2@domain.com");
	public Person person3 = new Person(3, "Test person3", 394749472, "Your mamas' home", "person3@domain.com");
	
	//some meeting test instances
	
	public Group group1 = new Group("Gruppe1");
	public Group group2 = new Group("Gruppe2");
	
	//some meeting rooms
	
	public Room room1 = new Room(0, "Stripa", 1, "bra rom", 5);
	public Room room2 = new Room(1, "Elektro", 0, "stillerom", 20);
	
	//mainmethod to test
	public static void main(String[] args) {
		Meeting meeting1 = new Meeting(0, 0, "2014-03-14 20:51:45", "2014-03-12 22:54:45", "lorem ipsum dolor", 0);
		Meeting meeting2 = new Meeting(0, 0, "2014-03-14 28:51:45", "2014-03-12 22:51:45", "lorem ipsum dolor", 0);
//		System.out.println(meeting1.getDayOfWeek());
//		System.out.println(meeting1.getEnd());
//		System.out.println(meeting1.getEndTime());
//		meeting1.setEndTime("21:22");
//		System.out.println(meeting1.getEnd());
		System.out.println(meeting1.getDuration());
	}
}
