package gruppe9.kalender.client;

import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Deltaker;
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
		try 
		{
			if(arrayResponse != null){
				for (int i = 0; i < arrayResponse.length(); i++) {
					JSONObject jo;
					jo = arrayResponse.getJSONObject(i);
					meetList.add(new Meeting(Integer.parseInt(jo.getString("AvtaleID")), Integer.parseInt(jo.getString("skaper")), jo.getString("Starttidspunkt"), jo.getString("Sluttidspunkt"), jo.getString("Beskrivelse").replace("[space]", " "), Integer.parseInt(jo.getString("rom")), jo.getString("Tittel")));				
				}}
			Bruker.getInstance().setAvtaler(meetList);
			return true;
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<Meeting> getOtherMeetings(){
//		if(!var.equals("avtaler"))
//		{
//			return null;
//		}
		ArrayList<Meeting> meetList = new ArrayList<Meeting>();
		try 
		{
			if(arrayResponse != null){
				for (int i = 0; i < arrayResponse.length(); i++) {
					JSONObject jo;
					jo = arrayResponse.getJSONObject(i);
					System.out.println("jo: " + jo.toString());
					meetList.add(new Meeting(Integer.parseInt(jo.getString("AvtaleID")), Integer.parseInt(jo.getString("skaper")), jo.getString("Starttidspunkt"), jo.getString("Sluttidspunkt"), jo.getString("Beskrivelse").replace("[space]", " "), Integer.parseInt(jo.getString("rom")), jo.getString("Tittel")));				
				}}
		}catch (JSONException e) {
			e.printStackTrace();
		}
		return meetList;
	}

	public ArrayList<Deltaker> getDeltakere(){
		if(!var.equals("deltakere")){
			return null;
		}
		ArrayList<Deltaker> deltakerList = new ArrayList<Deltaker>();
		for (int i = 0; i < arrayResponse.length(); i++) {
			JSONObject jo;
			try {
				jo = arrayResponse.getJSONObject(i);
				deltakerList.add(new Deltaker(jo.getInt("Person_Ansattnummer"), jo.getInt("Avtale_AvtaleID"), jo.getString("Status"), jo.getString("SistSett")));
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
		if(!var.equals("melding")){
			return false;
		}
		ArrayList<Notification> notifytList = new ArrayList<Notification>();
		for (int i = 0; i < arrayResponse.length(); i++) {
			JSONObject jo;
			try {
				jo = arrayResponse.getJSONObject(i);
				notifytList.add(new Notification(jo.getString("Aarsak"), jo.getInt("Avtale_AvtaleID"), jo.getString("Tidspunkt")));
				System.out.println("notification added!");
			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}
		}
		Bruker.getInstance().setNotifications(notifytList);
		return true;
	}

	public ArrayList<Room> getRoms(){
		if(!var.equals("Room")){
			return null;
		}
		ArrayList<Room> roomList = new ArrayList<Room>();
		try {
			JSONObject jo;
			for (int i = 0; i < arrayResponse.length(); i++) {
				jo = arrayResponse.getJSONObject(i);
				roomList.add(new Room(jo.getInt("ID"), jo.getString("Bygg"), jo.getInt("Etasje"), jo.getString("Beskrivelse"), jo.getInt("Stoerrelse")));
				System.out.println("notification added!");
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
		try {
			return objectResponse.getString(var);
		} catch (JSONException e) {
			return null;
		}
	}
}