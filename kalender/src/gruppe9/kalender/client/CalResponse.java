package gruppe9.kalender.client;

import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Deltaker;
import gruppe9.kalender.model.Group;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Notification;
import gruppe9.kalender.model.Person;
import gruppe9.kalender.model.Room;
import gruppe9.kalender.user.Bruker;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**Class for parsing and 
 * storing responses from the server
 * 
 * @author krekle
 *
 */
public class CalResponse {
	private String code;
	private String msg;
	private JSONObject objectResponse;
	private JSONArray arrayResponse;

	private String var;

	public boolean getAvtaler(){
		if(!var.equals("avtaler")){
			return false;
		}
		ArrayList<Meeting> meetList = new ArrayList<Meeting>();
		if(arrayResponse != null){
			for (int i = 0; i < arrayResponse.length(); i++) {
				JSONObject jo;
				try {
					jo = arrayResponse.getJSONObject(i);
					int rom = -1;
					if(!(jo.getString("rom").equals("NA") || jo.getString("rom").equals(null))){
						try {
							rom = Integer.parseInt(jo.getString("rom"));
							System.out.println("ROM GOTTEN!");
						} catch (Exception e) {
						}
						
					}else{
						System.out.println("NO ROM");
					}
					meetList.add(new Meeting(Integer.parseInt(jo.getString("AvtaleID")),
							Integer.parseInt(jo.getString("skaper")), 
							jo.getString("Starttidspunkt"), 
							jo.getString("Sluttidspunkt"), 
							jo.getString("Beskrivelse").replace("[space]", " "), 
							rom, 
							jo.getString("Tittel"), 
							jo.getString("Status")));
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}}
		Bruker.getInstance().setAvtaler(meetList);
		return true;
	}

	public ArrayList<Meeting> getOtherMeetings(){
		ArrayList<Meeting> meetList = new ArrayList<Meeting>();
		try 
		{
			if(arrayResponse != null){
				for (int i = 0; i < arrayResponse.length(); i++) {
					JSONObject jo;
					jo = arrayResponse.getJSONObject(i);
					int rom;
					try {
						rom = Integer.parseInt(jo.getString("rom"));
					} catch (Exception e) {
						rom = 0;
					}
					meetList.add(new Meeting(Integer.parseInt(jo.getString("AvtaleID")), Integer.parseInt(jo.getString("skaper")), jo.getString("Starttidspunkt"), jo.getString("Sluttidspunkt"), jo.getString("Beskrivelse").replace("[space]", " "), rom, jo.getString("Tittel"), null));				
				}}
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return meetList;
	}

	public ArrayList<Deltaker> getDeltakere(){
		if (var == null) {
			return null;
		}
		if(!var.equals("deltakere")){
			return null;
		}
		ArrayList<Deltaker> deltakerList = new ArrayList<Deltaker>();
		for (int i = 0; i < arrayResponse.length(); i++) {
			JSONObject jo;
			try {
				jo = arrayResponse.getJSONObject(i);
				deltakerList.add(new Deltaker(jo.getString("navn"), jo.getInt("Person_Ansattnummer"), jo.getInt("Avtale_AvtaleID"), jo.getString("Status"), jo.getString("SistSett")));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return deltakerList;
	}

	public boolean getAlerts(){
		if(!var.equals("alarm")){
			return false;
		}
		ArrayList<Alert> alertList = new ArrayList<Alert>();
		for (int i = 0; i < arrayResponse.length(); i++) {
			JSONObject jo;
			try {
				jo = arrayResponse.getJSONObject(i);
				alertList.add(new Alert(jo.getString("Tidspunkt"), null, jo.getString("Varselstekst"), jo.getInt("Avtale_AvtaleID"), jo.getString("lydspor")));
			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}
		}
		Bruker.getInstance().setVarsler(alertList);
		return true;
	}

	public boolean getNotifications(){
		if(!var.equals("melding") || arrayResponse == null){
			return false;
		}
		ArrayList<Notification> notifytList = new ArrayList<Notification>();
		for (int i = 0; i < arrayResponse.length(); i++) {
			JSONObject jo;
			try {
				jo = arrayResponse.getJSONObject(i);
				int aid = ((jo.getString("Avtale_AvtaleID").equals("null")? 0: Integer.parseInt(jo.getString("Avtale_AvtaleID"))));
				notifytList.add(new Notification(jo.getString("Aarsak"), aid, jo.getString("Tidspunkt")));
			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}
		}
		Bruker.getInstance().setNotifications(notifytList);
		return true;
	}

	public ArrayList<Room> getRoms(){
		//VAR BLIR HER null NÅR DEN ETTER ALLE SIGENDE SKULLE VÆRT "Room"
		if (var == null) {
			return null;
		}
		if(!var.equals("Room"))
		{
			return null;
		}
		ArrayList<Room> roomList = new ArrayList<Room>();
		try {
			JSONObject jo;
			for (int i = 0; i < arrayResponse.length(); i++) {
				jo = arrayResponse.getJSONObject(i);
				roomList.add(new Room(jo.getInt("ID"), jo.getString("Bygg"), jo.getInt("Etasje"), jo.getString("Beskrivelse"), jo.getInt("Stoerrelse")));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return roomList;
	}

	public boolean getAllPeople(){
		if(!var.equals("people")){
			return false;
		}
		ArrayList<Person> personList = new ArrayList<Person>();
		try {
			for (int i = 0; i < arrayResponse.length(); i++) {
				JSONObject jo;

				jo = arrayResponse.getJSONObject(i);
				personList.add(new Person(jo.getInt("Ansattnummer"), jo.getString("Navn"), jo.getInt("Telefonnummer"), jo.getString("adresse"), jo.getString("Epost")));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		Bruker.getInstance().setAllPeople(personList);
		return true;
	}

	public boolean getGroups(){
		if(!var.equals("groups")){
			return false;
		}
		ArrayList<Group> groups = new ArrayList<Group>();
		try {
			JSONObject jo;			
			ArrayList<Person> personList = new ArrayList<Person>();
			for (int i = 0; i < arrayResponse.length(); i++) {
				jo = arrayResponse.getJSONObject(i);
				
				JSONArray peopleArray = jo.getJSONArray("people");
				for (int j = 0; j < peopleArray.length(); j++) {
					JSONObject jobj = (JSONObject) peopleArray.get(j);
					personList.add(new Person(jobj.getInt("Ansattnummer"), 
												jobj.getString("Navn"), 
												jobj.getInt("Telefonnummer"), 
												jobj.getString("adresse"), 
												jobj.getString("Epost")));	
				}
				groups.add(new Group(jo.getString("Gruppenavn"), 
						jo.getString("Beskrivelse"), 
						jo.getInt("GruppeID"),
						personList));
				personList = new ArrayList<Person>();
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		Bruker.getInstance().setGroups(groups);
		return true;
	}

	public boolean confirmLogin(){
		try {
			if(objectResponse != null){
				Bruker.getInstance().setUser(new Person(Integer.parseInt(objectResponse.getString("Ansattnummer")), objectResponse.getString("Navn"), Integer.parseInt(objectResponse.getString("Telefonnummer")), objectResponse.getString("adresse"), objectResponse.getString("Epost")));
				return true;
			}
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
		return false;
	}

	public CalResponse(String resp, String var){
		try {
			JSONObject data = new JSONObject(resp);
			//Parsing out response, code and message
			if(var != null){this.var = var;}
			code = data.getString("code");
			msg = data.getString("msg");
			try {
				JSONObject temp = data.getJSONObject("response");
				arrayResponse = temp.getJSONArray(var);
			} catch (Exception e) {
				objectResponse = data.getJSONObject("response");
			}
		} catch (JSONException e) {
			//If response = null: this is no error
			//				System.out.println("ERR-Json parsing error");
		}
	}

	public String getCode(){
		return this.code;
	}

	public String getMsg(){
		return this.msg;
	}
	/**Method for getting a simple response
	 * 
	 * @param var
	 * var is the expected return string key
	 * @return
	 * getSimpleResponse("token") gives the token from the json response
	 * 
	 */
	public String getSimpleResponse(String var){
		String result = "";
		System.out.println("Var var " + var);
		try {
			result = objectResponse.getString(var);
		} catch (JSONException e) {
			try {
				result =  objectResponse.getInt(var)+"";
			} catch (Exception e2) {
				return null;
			}
		}
		return result;
	}
}
