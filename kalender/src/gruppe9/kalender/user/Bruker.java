package gruppe9.kalender.user;

import java.util.ArrayList;

import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Person;


public class Bruker {
	
	private ArrayList<Meeting> avtaler;
	private Person bruker;
	private ArrayList<Alert> varsler;

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
		this.avtaler = avtaler;
	}

	//For å bruke metoder fra bruker kall Bruker.getInstance().*
	//* = any method, med dette pattern så kan instansen av denne klassen nåes fra hvor som helst.
}