package gruppe9.kalender.user;


public class Bruker {
	
	//Her vil vi typisk ha ArrayList<Avtaler>, ArrayList<Varsler> osv, osv
	
	private static String username;
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

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Bruker.username = username;
	}

	//For å bruke metoder fra bruker kall Bruker.getInstance().*
	//* = any method, med dette pattern så kan instansen av denne klassen nåes fra hvor som helst.
}