package gruppe9.kalender.client;

import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Notification;
import gruppe9.kalender.user.Bruker;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ServerPuller {

	private static ScheduledExecutorService service; 

	private static class Updater implements Runnable, ApiCaller {

		private static ArrayList<Notification> notifications;
		private static ArrayList<Meeting> meetings;

		@Override
		public void run() {
			getNot();
			System.out.println("getNot called");
		}

		private void getNot(){
			//Notifications, Avtaler og Grupper
			try {
				System.out.println("Checking for information");
				notifications = Bruker.getInstance().getNotifications();
				meetings = Bruker.getInstance().getAvtaler();
			} catch (Exception e) {
				notifications = null;
				meetings = null;
				System.out.println("No alerts and notifications");
			}
			System.out.println("Asking for updates");
			try {
				Database.getMeetings(this, Bruker.getInstance().getUser().getId());
				Database.getNotifications(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		@Override
		public void callBack(CalResponse response) {
			try{
			System.out.println("AT CALLBACK");
			if(response.getAlerts()){
				System.out.println("AT CALLBACK alert");
				if(meetings != null && Bruker.getInstance().getVarsler() != null){
					int oldSize = ((meetings != null)?meetings.size():0);
					int newSize = Bruker.getInstance().getAvtaler().size();
					if(newSize > oldSize){
						//CALL MAIN_WINDOW
						System.out.println("new MEETINGS!");
					}
				}
			}
				else if(response.getNotifications()){
				System.out.println("AT CALLBACK notifications");
				if(notifications != null && Bruker.getInstance().getNotifications() != null){
					int oldSize = notifications.size();
					int newSize = Bruker.getInstance().getNotifications().size();
					if(newSize > oldSize){
						//CALL MAIN_WINDOW
						System.out.println("new NOTIFICATIONS!");
					}
				}
			}
			}catch(Exception e){
				e.printStackTrace();
				System.err.println("Something went terribly wrong?");
			}
		}

	}

	public static void update() {
		Runnable r = new Updater();
		service = Executors.newScheduledThreadPool(1);
		try {
			service.scheduleAtFixedRate(r, 10, 20, TimeUnit.SECONDS);
//			service.schedule(r, 60, TimeUnit.SECONDS);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		//        Thread.sleep(10000);
		//        service.shutdown();

	}
}