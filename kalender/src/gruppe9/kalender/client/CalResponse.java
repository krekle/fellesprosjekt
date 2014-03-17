package gruppe9.kalender.client;

import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Person;
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

	public ArrayList<Meeting> getMyMeetings(){
		ArrayList<Meeting> meetList = new ArrayList<Meeting>();
		for (int i = 0; i < arrayResponse.length(); i++) {
			JSONObject jo;
			try {
				jo = arrayResponse.getJSONObject(i);
				meetList.add(new Meeting(Integer.parseInt(jo.getString("AvtaleID")), Integer.parseInt(jo.getString("skaper")), jo.getString("Starttidspunkt"), jo.getString("Sluttidspunkt"), jo.getString("Beskrivelse"), Integer.parseInt(jo.getString("rom"))));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		return meetList;
	}
	
	public Person confirmLogin(){
		try {
			if(objectResponse != null){
			Bruker.getInstance().setUser(new Person(Integer.parseInt(objectResponse.getString("Ansattnummer")), objectResponse.getString("Navn"), Integer.parseInt(objectResponse.getString("Telefonnummer")), objectResponse.getString("adresse"), objectResponse.getString("Epost")));
			return Bruker.getInstance().getUser();
			}
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public CalResponse(String resp, String var){
		try {
			JSONObject data = new JSONObject(resp);
			//Parsing out response, code and message
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