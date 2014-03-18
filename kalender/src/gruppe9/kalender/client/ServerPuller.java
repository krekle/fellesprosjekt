package gruppe9.kalender.client;
import static java.util.concurrent.TimeUnit.*;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import gruppe9.kalender.frontend.*;
import gruppe9.kalender.model.Alert;
import gruppe9.kalender.model.Notification;
import gruppe9.kalender.user.Bruker;

public class ServerPuller implements ApiCaller{

	private static ArrayList<Notification> notif;
	private static ArrayList<Alert> alerts;
	
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private final ApiCaller call = this;

	public void pullFromServer() {
		final Runnable pull = new Runnable() {
			public void run() {
				getNot();s
				}
		};
		
		final ScheduledFuture<?> pullHandle =
				scheduler.scheduleAtFixedRate(pull, 60, 60, SECONDS);
	
//		
//		scheduler.schedule(new Runnable() {
//			public void run() { pullHandle.cancel(true); }
//		}, 60 * 60, SECONDS);
	}

	private void getNot(){
		try {
			notif = Bruker.getInstance().getNotifications();
			alerts = Bruker.getInstance().getVarsler();
		} catch (Exception e) {
			notif = null;
			alerts = null;
			System.out.println("alerts and notifications empty");
		}
		
		Database.getAlerts(call);
		Database.getNotifications(call);
	}
	
	@Override
	public void callBack(CalResponse response) {
		if(response.getAlerts()){
			if(alerts != null && Bruker.getInstance().getVarsler() != null){
				int oldSize = alerts.size();
				int newSize = Bruker.getInstance().getVarsler().size();
				if(newSize > oldSize){
					//NEW ALERTS
				}
			}
		}else if(response.getNotifications()){
			if(notif != null && Bruker.getInstance().getNotifications() != null){
				int oldSize = alerts.size();
				int newSize = Bruker.getInstance().getNotifications().size();
				if(newSize > oldSize){
					//NEW NOTIFICATIONS
				}
			}
		}
	}
}