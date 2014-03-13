package gruppe9.kalender.user;

import gruppe9.kalender.model.Person;


public class Bruker {
	
	//Her vil vi typisk ha ArrayList<Avtaler>, ArrayList<Varsler> osv, osv
	
	private Person bruker;
	//field for å holde styr på instansen av Bruker:
	private static Bruker instance = null;
	
	private Bruker() {
		// private constructor for singleton-pattern
	}

	public static Bruker getInstance() {
		if (instance == null) {
			instance = new Bruker();
		}
		return instance;
	}

	public void setUser(Person p){
		this.bruker = p;
	}
	
	public Person getUser(){
		return this.bruker;
	}
	//For å bruke metoder fra bruker kall Bruker.getInstance().*
	//* = any method, med dette pattern så kan instansen av denne klassen nåes fra hvor som helst.
}