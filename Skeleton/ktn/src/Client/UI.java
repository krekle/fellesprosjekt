package Client;

import java.util.Scanner;

public class UI 
{
	public static void main(String[] args)
	{
		UI ui = new UI();
		System.out.println("'/connect username:ip:port' to connect");
		ui.run();
	}
	public void run()
	{
		boolean connected = false;
		Scanner scanner = new Scanner(System.in);
		Connection connection = new Connection(this);
		while(true)
		{
			String input = scanner.nextLine();
			if(connected)
			{
				if(input.equals("/disconnect"))
				{
					connection.logOut();
					connected = false;
					connection = new Connection(this);
				}
				else if(input.startsWith("/"))
				{
					System.out.println("invalid commands. Valid commands are '/connect' and '/disconnect'");
				}
				else if (input.length() != 0)
				{
					connection.pass(input);
				}
			}
			else
			{
				if(input.startsWith("/connect") && !connected)
				{
					String[] server_info = input.substring(9).split(":");
					System.out.println(server_info[0] +":"  + server_info[1] + ":" + server_info[2]);
					if(connection.logIn(server_info[0], server_info[1], Integer.parseInt(server_info[2])))
					{
						connected = true;
						Thread t = new Thread(connection);
						t.start();
					}
					else
					{
						System.out.println("Did not connect..");
						connected = false;
					}
					
				}
				
			}
		}
	}
	public void print(String output)
	{
		System.out.println(output);
	}
}
