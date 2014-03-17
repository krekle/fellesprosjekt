package gruppe9.kalender.model;

import java.util.ArrayList;

public class Group {
	private String name;
	private String description;
	private ArrayList<Person> people = new ArrayList<Person>();
	private int ID;
	
	public String toString()
	{
		return "Gruppe " + this.ID+": " + this.name;
	}
	public Group(String name) {
		super();
		this.name = name;
	}

	public void addPerson(Person person){
		people.add(person);
	}

	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void setID(int ID)
	{
		this.ID = ID;
	}
	public int getID()
	{
		return ID;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Person> getPeople() {
		return people;
	}
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
}
