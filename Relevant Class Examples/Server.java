import java.io.*;
import java.net.*;
import java.util.*;
//were gonna break it up a little bit so that there isnt so much work in the main 
class Server 
{
	private ServerSocket serverSocket;
	private Socket connection = null;
	private Scanner input = null;
	private Formatter output = null;
	private double area;
	private double radius; 
	
	public void runServer()
	{
		try
		{
			serverSocket = new ServerSocket(64494, 100);
			
			while(true)
			{
				try
				{
					System.out.println("waiting for input");
					connection = serverSocket.accept();
					
					processConnections();
					processRadius();
				}
				catch(Exception e)
				{
					System.out.println("done");
				}
				finally
				{
					try
					{
						output.close();
						input.close();
						connection.close();
					}
					catch(IOException ioe)
					{
						ioe.printStackTrace();
					}
				}
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	public void processConnections() throws IOException
	{
		input = new Scanner(connection.getInputStream());
		output = new Formatter(connection.getOutputStream());
	}
	public void processRadius()
	{
		do
		{
			try
			{
				radius = input.nextDouble();
				System.out.println("Radius " + radius);
				area = radius*radius*Math.PI;
				output.format("%.2f\n", area);
				output.flush();
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("completed");
				input.nextLine();
			}
		}while(radius!=0);
	}
}
public class ServerTest
{
	public static void main(String args[])
	{
		Server myServer = new Server()
		myServer.runServer();
	}
}