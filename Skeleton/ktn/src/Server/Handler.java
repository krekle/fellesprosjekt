package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Handler extends Thread 
{

	Socket client; String username = "NO USERNAME"; PrintWriter output;	Scanner input;
	boolean keepAlive = true;
	int ID;
	public Handler(Socket socket, int ID)
	{
		this.ID = ID;
		this.client = socket;
		try 
		{
			this.output = new PrintWriter(socket.getOutputStream());
			this.input = new Scanner(socket.getInputStream());
			if(checkUser())
			{
				this.start();
			}
			else
			{
				closeAll();
				return;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	public void run()
	{
		try{
		while(!client.isClosed() | keepAlive)
		{
			String nextInput = input.nextLine();
			handle(nextInput);
		}}
		catch(NoSuchElementException e)
		{
			if(Server.user_Handler.containsKey(username))
			{
				Server.user_Handler.remove(username);
			}
			System.out.println("No input.. client prolly disconnected - " + username);
		}
		output.close();
		input.close();
	}
	public void sendToClient(String user, String message)
	{
		JSONObject ReplyObject = new JSONObject();
		ReplyObject.put("response","message");
		ReplyObject.put("message", user + ": " +message);
		output.println(ReplyObject.toString());
		output.flush();
	}
	
	public void handle(String in)
	{
		try 
		{
			JSONParser p = new JSONParser();
			JSONObject O =(JSONObject)p.parse(in);
			if(O.containsKey("request") && ((String) O.get("request")).equals("message") && O.containsKey("message"))
			{
				JSONObject ReplyObject = new JSONObject();
				System.out.println(O.toString());
				if(!Server.user_Handler.containsKey(username))
				{
					if(!Server.user_Handler.containsValue(this.client))
					{
						ReplyObject.put("response", "message");
						ReplyObject.put("error","Not logged in!");
						output.println(ReplyObject.toJSONString());
						output.flush();
					}
				}
				else
				{
					Server.messages.add(username +": " + O.get("message"));
					Server.broadcast(username, O);
				}
			}
			else if(O.containsKey("request") && ((String) O.get("request")).equals("logout"))
			{
				JSONObject ReplyObject = new JSONObject();
				if(!Server.user_Handler.keySet().contains(this.username))
				{
					ReplyObject.put("response", "logout");
					ReplyObject.put("error","Not logged in!");
					ReplyObject.put("username", username);
				}
				else
				{
					ReplyObject.put("response","logout");
					ReplyObject.put("username", username);
					Server.user_Handler.remove(this.username);
				}
				output.println(ReplyObject.toString());
				closeAll();
				keepAlive = false;
			}
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}
	public boolean checkUser()
	{
		try 
		{			
			System.out.println("Checking user - " + ID);
			String FirstContact = input.nextLine();
			System.out.println(FirstContact);
			JSONParser p = new JSONParser();
			JSONObject O =(JSONObject)p.parse(FirstContact);
			System.out.println(O.toJSONString());
			if(O.containsKey("request") && ((String)O.get("request")).equals("login") && O.containsKey("username"))
			{
				System.out.println("Request has username & login");
				String username = (String) O.get("username");
				if(!username.matches("^[a-zA-Z0-9_]*$"))
				{
					JSONObject ReplyObject = new JSONObject();
					ReplyObject.put("response", "login");
					ReplyObject.put("username", username);
					ReplyObject.put("error", "invalid username!");
					output.println(ReplyObject.toJSONString());
					closeAll();
					return false;
				}
				else if(Server.user_Handler.containsKey(username))
				{
					System.out.println("username taken.");
					JSONObject ReplyObject = new JSONObject();
					ReplyObject.put("response", "login");
					ReplyObject.put("username", username);
					ReplyObject.put("error","name already taken!");
					output.println(ReplyObject.toJSONString());
					return false;
				}
				else
				{
					Server.user_Handler.put((String) O.get("username"), this);
					System.out.println("Username not taken - valid.");
					JSONObject ReplyObject = new JSONObject();
					ReplyObject.put("response","login");
					ReplyObject.put("username", username);
					JSONArray Jarray = new JSONArray();
					Jarray.addAll(Server.messages);
					ReplyObject.put("messages", Jarray);
					this.username = username;
					System.out.println("Returning " + ReplyObject.toJSONString());
					System.out.println();
					output.println(ReplyObject.toJSONString());
					output.flush();
					return true;
				}
			}
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void closeAll()
	{
		try 
		{
			output.flush();
			output.close();
			input.close();
			client.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}