package gruppe9.kalender.client;

import gruppe9.kalender.model.Alert;
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
		private static ArrayList<Alert> alerts;

		@Override
		public void run() {
			getNot();
		}

		private void getNot(){
			System.out.println("30 seconds passed");
			try {
				notifications = Bruker.getInstance().getNotifications();
				alerts = Bruker.getInstance().getVarsler();
			} catch (Exception e) {
				notifications = null;
				alerts = null;
				System.out.println("alerts and notifications empty");
			}

			try {
				Database.getAlerts(this);
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
				if(alerts != null && Bruker.getInstance().getVarsler() != null){
					int oldSize = alerts.size();
					int newSize = Bruker.getInstance().getVarsler().size();
					if(newSize > oldSize){
						//NEW ALERTS
						System.out.println("new ALERTS!");
					}
				}
			}else if(response.getNotifications()){
				System.out.println("AT CALLBACK notifications");
				if(notifications != null && Bruker.getInstance().getNotifications() != null){
					int oldSize = alerts.size();
					int newSize = Bruker.getInstance().getNotifications().size();
					if(newSize > oldSize){
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
//		service.scheduleAtFixedRate(r, 10, 60, TimeUnit.SECONDS);
		try {
			service.schedule(r, 10, TimeUnit.SECONDS);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		//        Thread.sleep(10000);
		//        service.shutdown();

	}
}