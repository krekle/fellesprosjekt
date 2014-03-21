package Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Connection implements Runnable
{
	private Socket socket;
	private UI ui;
	private PrintWriter output;
	private Scanner input;
	public Connection(UI ui)
	{
		this.ui = ui;
		socket = new Socket();
		
	}
	
	public boolean logIn(String username, String address, int port)
	{
		try 
		{
			ui.print("Attempting to connect...");
			socket = new Socket(address, port);
			output = new PrintWriter(socket.getOutputStream());
			input = new Scanner(socket.getInputStream());
			
			//Skap & send innloggingsobjekt til server.
			JSONObject obj = new JSONObject();
			obj.put("request", "login");
			obj.put("username", username);
			output.println(obj.toString());
			output.flush();
			
			//Hent og pakk ut respons
			String response = input.nextLine();
			JSONParser p = new JSONParser();
			JSONObject O =(JSONObject)p.parse(response);

			//Om responsen stemmer overens med forventningene - i.e. inneholder {'response':'login'....
			if(O.containsKey("response") && ((String)O.get("response")).equals("login"))
			{
				//om den også inneholder error...
				if(O.containsKey("error"))
				{
					ui.print((String)O.get("error"));
					socket.close();
					output.close();
					input.close();
					return false;
				}
				//om alt er vel, print meldingshistorie...
				else if(O.containsKey("response") && O.containsKey("messages"))
				{
					JSONArray jaray = (JSONArray) O.get("messages");
					
					ui.print("");
					ui.print("");
					ui.print("");
					ui.print("");
					ui.print("**********CONNECTED**********");
					for(Object x : jaray)
					{
						ui.print((String) x);
					}
					return true;
				}
				else
				{
					ui.print("Unexpected response...");
					for(Object x : O.keySet())
					{
						ui.print((String) x + ": " + O.get(x));
					}
					return false;
				}
				
			}
			else
			{
				ui.print("Unexpected response...");
				for(Object x : O.keySet())
				{
					ui.print((String) x + ": " + O.get(x));
				}
				return false;
			}
		} 
		
		catch (IOException e) 
		{
			ui.print("Could not connect to server at "+address+":"+port);
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			ui.print("ParseError");
			e.printStackTrace();
		}
		ui.print("fell out!");
		return false;
	}
	
	public void logOut()
	{
			JSONObject O = new JSONObject();
			O.put("request", "logout");
			output.println(O.toString());
			output.flush();
	}
	
	
	//tolk input fra server...
	public void parse(String message)
	{
		JSONParser p = new JSONParser();
		try 
		{	JSONObject O =(JSONObject)p.parse(message);
			if(O.containsKey("response"))
			{
				if(((String) O.get("response")).equals("logout"))
				{
					if(O.containsKey("error"))
					{
						ui.print("Wasn't logged in, apparently..");
						output.close();input.close();socket.close();	
					}
					else
					{						
					ui.print("Successfully logged out!");
					output.close();input.close();socket.close();
					}
					keepAlive = false;
				}
				else if(((String) O.get("response")).equals("message") && O.containsKey("message"))
				{
					ui.print((String) O.get("message"));
				}
			}
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	boolean keepAlive = true;
	public void run()
	{
		while(keepAlive)
		{
			String nextInput = input.nextLine();
			parse(nextInput);
		}	
	}
	
	//send input til server.
	public void pass(String input) 
	{
		JSONObject obj = new JSONObject();
		obj.put("request", "message");
		obj.put("message", input);
		output.println(obj.toString());
		output.flush();
	}
}
