package gruppe9.kalender.client;

import gruppe9.kalender.client.Client.Type;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Person;
import gruppe9.kalender.user.Bruker;


public class Database {

	public static CalResponse login(String email, String password) {
		String result = "";
		try {
			Client client =new Client("login", Type.POST, "email", email, "password", password);
			result = client.execute();
		} catch (Exception e) {
			System.out.println("ERR-database client login");
		}
		return new CalResponse(result, null);
	}
	
	public static CalResponse getMeetings(){
		String result = "";
		try {
			result = new Client("get/mineavtaler/" + Bruker.getInstance().getUser().getId(), Type.GET).execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new CalResponse(result, "avtaler");
	}
	
	public static CalResponse addMeeting(Meeting m){
		String result = "";
		try {
			result = new Client("add/avtale",Type.GET,
					"start", m.getStart(), 
					"slutt", m.getEnd(), 
					"beskrivelse", m.getDescription().replace(" ", "[space]"),
					"varighet", "",
					"sted", "sad",
					"skaper", m.getCreator() + "",
					"romid", m.getRoom()+"")
					.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CalResponse(result, null);
	}
	
	public static CalResponse deleteMeeting(Meeting m){
		String result = "";
		try {
			result = new Client("delete/avtale", Type.GET,
					"avtale_id", m.getId()+"").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CalResponse(result, null);
	}
 	
	public static CalResponse updateMeeting(Meeting m){
		String result = "";
		try {
			result = new Client("update/avtale" + m.getId(), Type.GET,
					"avtale_id", m.getId()+"").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CalResponse(result, null);
	}
	
	public static CalResponse addParticipants(String avtale_id, String csvPeople, String csvStatus){
		String result = "";
		//TODO: check status and people length
		if(csvPeople.split(",").length != csvStatus.split(",").length)
			return null;
		try {
			result = new Client("add/deltakere/" + avtale_id, Type.GET,
					"people", csvPeople,
					"status", csvStatus).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CalResponse(result, null);
	}
	
	public static CalResponse getParticipants(Meeting m){
		String result = "";
		try {
			result = new Client("get/deltakere/" + m.getId()+"", Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CalResponse(result, "deltakere");
	}
	
	public static CalResponse deleteParticipant(String avtale_id, String person_id){
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("delete/deltaker", Type.GET,
					"person_id", avtale_id,
					"avtale_id", person_id).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CalResponse(result, null);
	}

	public static CalResponse updateParticipantStatus(String avtale_id, String person_id, String status){
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("update/avtalestatus/"+ Bruker.getInstance().getUser().getId(), Type.GET,
					"status", status,
					"avtale_id", avtale_id).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CalResponse(result, null);
	}

	public static void main(String args[]){
//		CalResponse cal = login("eposten3@min.com", "passordet");
//		Person me = cal.confirmLogin();
//		System.out.println(me.getName() + me.getTelephonenumber());
		
		Meeting meet = new Meeting(7, 3, "2014-03-13-13:00:52", "2014-03-13-15:00:43", "Lorem ipsum dolor", 414);
		CalResponse cal = addMeeting(meet);
		System.out.println(cal.getCode() + " " + cal.getMsg());
//		System.out.println("Getting meetings");
//		CalResponse c = getParticipants(meet);
//		System.out.println(c.getDeltakere().get(1).getPersonID());
		
	}
}
