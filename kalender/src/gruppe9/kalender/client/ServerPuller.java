package gruppe9.kalender.client;

import gruppe9.kalender.frontend.Main_Window;
import gruppe9.kalender.model.Meeting;
import gruppe9.kalender.model.Notification;
import gruppe9.kalender.user.Bruker;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ServerPuller {

	private static ScheduledExecutorService service; 
	private static Main_Window mw;

	private static class Updater implements Runnable, ApiCaller {

		private static ArrayList<Notification> notifications;
		private static ArrayList<Meeting> meetings;

		@Override
		public void run() {
			getUpdates();
		}

		private void getUpdates(){
			//Notifications, Avtaler og Grupper
			try {
				notifications = Bruker.getInstance().getNotifications();
				meetings = Bruker.getInstance().getAvtaler();
			} catch (Exception e) {
				notifications = null;
				meetings = null;
			}
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
			if(response.getAlerts()){
				if(meetings != null && Bruker.getInstance().getVarsler() != null){
					int oldSize = ((meetings != null)?meetings.size():0);
					int newSize = Bruker.getInstance().getAvtaler().size();
					if(newSize > oldSize){
						mw.parseObject(Bruker.getInstance().getAvtaler());
					}
				}
			}
				else if(response.getNotifications()){
				if(notifications != null && Bruker.getInstance().getNotifications() != null){
					int oldSize = notifications.size();
					int newSize = Bruker.getInstance().getNotifications().size();
					if(newSize > oldSize){
						mw.parseObject(Bruker.getInstance().getNotifications());
					}
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

	public static void update(Main_Window main) {
		mw = main;
		Runnable r = new Updater();
		service = Executors.newScheduledThreadPool(1);
		try {
			service.scheduleAtFixedRate(r, 10, 60, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void stop(){
		service.shutdown();
	}
}