package gruppe9.kalender.client;

import gruppe9.kalender.frontend.Main_Window;
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
	private static Main_Window mw;

	private static class Updater implements Runnable, ApiCaller {

		@Override
		public void run() {
			getUpdates();
			System.out.println("PULL FROM SERVER COMPLETE");
		}

		private void getUpdates(){
			//Notifications, Avtaler og Grupper
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
					for (Alert alert : Bruker.getInstance().getVarsler()) {
						mw.parseObject(alert);						
					}
				}
				else if(response.getNotifications()){
					
					for (Notification no : Bruker.getInstance().getNotifications()) {
						mw.parseObject(no);
					}
				}
				else if(response.getAvtaler()){
					mw.parseObject(Bruker.getInstance().getAvtaler());
				}
			}
			catch(Exception e){
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