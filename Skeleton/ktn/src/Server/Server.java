package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONObject;


public class Server
{
	public static Map<String, Handler> user_Handler = new HashMap<String, Handler>();
	public static ArrayList<String> messages = new ArrayList<String>();
	public static void main(String[] args)
	{
		Server server = new Server();
		server.run();
	}
	public void run()
	{	final int port;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter server port, please: ");
		port = scanner.nextInt();
		int counter = 0;
		try {
			ServerSocket server = new ServerSocket(port);
			while(true)
			{
				System.out.println("Waiting for client....");
				Socket connection = server.accept();
				System.out.println("Client connected from "+connection.getInetAddress());
				Handler h = new Handler(connection, counter++);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Send melding til alle klienter..
	public static void broadcast(String username, JSONObject o) 
	{
		for(String user : user_Handler.keySet())
		{
			Handler current = user_Handler.get(user);
			current.sendToClient(username, (String) o.get("message"));
		}
	}
}
