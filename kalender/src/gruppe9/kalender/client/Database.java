package gruppe9.kalender.client;

import gruppe9.kalender.client.Client.Type;
import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Notification;
import gruppe9.kalender.user.Bruker;

import java.util.ArrayList;


public class Database implements Runnable, ApiCaller
{
	ArrayList<Object> objects;
	int mode;
	public Database(int mode, ArrayList<Object> objects)
	{
		this.mode = mode;
		this.objects = objects;
		Thread t = new Thread(this);
		t.start();		
	}
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

	public static void getMeetings(ApiCaller caller, int ID){
		String result = "";
		try {
			result = new Client("get/mineavtaler/" + ID, Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "avtaler"));
	}

	public static void addMeeting(ApiCaller caller, Meeting m){
		String result = "";
		try {
			int rom = ((m.getPlace().equals("NA") || m.getPlace().equals("")? m.getRoom(): 0)); 
			
			result = new Client("add/avtale",Type.GET,
					"tittel", m.getName(),
					"start", m.getStart(), 
					"slutt", m.getEnd(), 
					"beskrivelse", m.getDescription().replace(" ", "[space]"),
					"varighet", "",
					"sted", m.getPlace(),
					"skaper", m.getCreator() + "",
					"romid", rom+"")
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
		if(caller != null){
			caller.callBack(new CalResponse(result, null));			
		}
	}
	
	
	
	public static void sendMail(ApiCaller caller, String to, String subject, String msg){
		String result = "";
		try {
			result = new Client("send/mail", Type.GET,
					"to", to,
					"subject", subject,
					"msg", msg
					).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(caller != null){
			caller.callBack(new CalResponse(result, null));
		}

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
					"varighet", "0",
					"sted", m.getPlace(),
					"rom", m.getRoom() + ""
					).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(caller != null){
			caller.callBack(new CalResponse(result, null));
		}
	}
	
	public static void addAlert(ApiCaller caller, Alert alert){
		String result = "";
		try {
			result = new Client("add/alarm/", Type.GET,
					"Tidspunkt", alert.getTime(),
					"Varselstekst", alert.getDesciption(),
					"Avtale_AvtaleID", alert.getMeetingID()+"",
					"Person_Ansattnummer", Bruker.getInstance().getUser().getId()+"").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, null));

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
		if(caller != null){			
			caller.callBack(new CalResponse(result, null));
		}
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
		try {
			result = new Client("delete/deltaker", Type.GET,
					"person_id", person_id,
					"avtale_id", avtale_id).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(caller != null){			
			caller.callBack(new CalResponse(result, "foo"));
		}
	}

	public static void updateParticipantStatus(ApiCaller caller, String avtale_id, String person_id, String status)
	{
		String result = "";
		try {
			result = new Client("update/avtalestatus/"+ Bruker.getInstance().getUser().getId(), Type.GET,
					"status", status,
					"avtale_id", avtale_id).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, null));
	}

	public static void getAlerts(ApiCaller caller){
		String result = "";
		try {
			result = new Client("get/person/varsler/"+ Bruker.getInstance().getUser().getId(), Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "alarm"));
	}

	public static void deleteAlert(ApiCaller caller, int personId, int avtaleId){
		String result = "";
		try {
			result = new Client("delete/alarm", Type.GET,
					"avtale_id", avtaleId +"",
					"person_id", personId + "").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, null));
	}
	public static void getNotifications(ApiCaller caller){
		String result = "";
		try {
			result = new Client("get/person/meldinger/"+ Bruker.getInstance().getUser().getId(), Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "melding"));
	}

	public static void deleteNotification(ApiCaller caller, int personId, int avtaleId){
		String result = "";
		try {
			result = new Client("delete/varsel", Type.GET,
					"avtale_id", avtaleId +"",
					"person_id", personId + "").execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getAvaliableRooms(ApiCaller caller,String start, String end){
		String result = "";
		try {
			result = new Client("get/ledigerom", Type.GET,
					"Starttidspunkt", start.replace(" ", "[space]"),
					"Sluttidspunkt", end.replace(" ", "[space]")).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//DENNER ER HELT PÅ TRYNET!
		caller.callBack(new CalResponse(result, "Room"));
	}
	
	public static void getAllPeople(ApiCaller caller){
		String result = "";
		try {
			result = new Client("get/person/all", Type.GET).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "people"));
	}
	
	public static void getGroups(ApiCaller caller){
		String result = "";
		try {
			result = new Client("get/groups/" + Bruker.getInstance().getUser().getId(), Type.GET).execute();
		} catch (Exception e){
			e.printStackTrace();
		}
		caller.callBack(new CalResponse(result, "groups"));
	}

	@Override
	public void run() 
	{
		if(mode==0)
		{
			for(Object o : objects)
			{
				Database.deleteNotification(this, Bruker.getInstance().getUser().getId(), ((Notification) o).getMeetingId());
			}
		}
		
	}
	@Override
	public void callBack(CalResponse response)
	{
		System.out.println("Deleted or did something else to cause the server to respond.");
	}
}
