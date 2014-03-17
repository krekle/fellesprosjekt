package gruppe9.kalender.client;

import gruppe9.kalender.client.Client.Type;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.user.Bruker;

import java.util.Calendar;


public class Database {

	public static void login(ApiCaller caller, String email, String password) {
		String result = "";
		try {
			Client client =new Client("login", Type.POST, "email", email, "password", password);
			result = client.execute();
		} catch (Exception e) {
			System.out.println("ERR-database client login");
		}
		caller.callBack(new CalResponse(result, null));
	}

	public static void getMeetings(ApiCaller caller){
		String result = "";
		try {
			result = new Client("get/mineavtaler/" + Bruker.getInstance().getUser().getId(), Type.GET).execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		caller.callBack(new CalResponse(result, "avtaler"));
	}

	public static void addMeeting(ApiCaller caller, Meeting m){
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
		caller.callBack(new CalResponse(result, null));
	}

	public static void deleteMeeting(ApiCaller caller, Meeting m){
		String result = "";
		try {
			result = new Client("delete/avtale", Type.GET,
					"avtale_id", m.getId()+"").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, null));
	}

	public static void updateMeeting(ApiCaller caller, Meeting m){
		String result = "";
		try {
			result = new Client("update/avtale/" + m.getId(), Type.GET,
					"avtale_id", m.getId()+"",
					"Tittel", m.getName(),
					"Starttidspunkt", m.getStart(),	
					"Sluttidspunkt", m.getEnd(),
					"Beskrivelse", m.getDescription().replace(" ", "[space]"),
					"varighet", m.getDuration()
					).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(caller != null){
			caller.callBack(new CalResponse(result, null));
		}
	}

	public static void addParticipants(ApiCaller caller, String avtale_id, String csvPeople, String csvStatus){
		String result = "";
		//TODO: check status and people length
		if(csvPeople.split(",").length != csvStatus.split(",").length)
			return;
		try {
			result = new Client("add/deltakere/" + avtale_id, Type.GET,
					"people", csvPeople,
					"status", csvStatus).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, null));
	}

	public static void getParticipants(ApiCaller caller, Meeting m){
		String result = "";
		try {
			result = new Client("get/deltakere/" + m.getId()+"", Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "deltakere"));
	}

	public static void deleteParticipant(ApiCaller caller, String avtale_id, String person_id){
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("delete/deltaker", Type.GET,
					"person_id", avtale_id,
					"avtale_id", person_id).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, null));
	}

	public static void updateParticipantStatus(ApiCaller caller, String avtale_id, String person_id, String status){
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("update/avtalestatus/"+ Bruker.getInstance().getUser().getId(), Type.GET,
					"status", status,
					"avtale_id", avtale_id).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, null));
	}

	//Alert er typsik: Møte med Kristian om 1 time! Obs Obs
	public static void getAlerts(ApiCaller caller){
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("get/person/varsler/"+ Bruker.getInstance().getUser().getId(), Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "alarm"));
	}

	//Alert er typsik: Avtale slettet, avtale endret osv osv //TODO: Backend support
	public static void getNotifications(ApiCaller caller){
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("get/person/meldinger/"+ Bruker.getInstance().getUser().getId(), Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "melding"));
	}

	public static void getAvaliableRooms(ApiCaller caller,String start, String end){
		//Størrelse??
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("get/ledigerom", Type.GET,
					"Starttidspunkt", start,
					"Sluttidspunkt", end).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "rom"));
	}
	
	public static void getAllPeople(ApiCaller caller){
		String result = "";
		//TODO: check status and people length
		try {
			result = new Client("get/person/all", Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "people"));
	}
}
