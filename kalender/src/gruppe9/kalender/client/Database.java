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
					"beskrivelse", m.getDescription(),
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
	
	public static void main(String args[]){
		CalResponse cal = login("eposten3@min.com", "passordet");
		Person me = cal.confirmLogin();
//		System.out.println(me.getName() + me.getTelephonenumber());
		
//		Meeting meet = new Meeting(23, 3, "2014-03-13-13:00:52", "2014-03-13-15:00:43", "Loremipsumdolor", 414);
//		CalResponse cal = addMeeting(meet);
//		System.out.println(cal.getCode() + " " + cal.getMsg());
		
		CalResponse c = getMeetings();
		System.out.println(c.getMyMeetings());
		
		
	}
}
