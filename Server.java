//Iris Osegueda
import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
	private ServerSocket serverSocket;
	private Socket connection = null;
	private Scanner input = null;
	private Formatter output = null;
	private String message = "";
	
	public void runServer()
	{
		String response = "";
		try
		{
			serverSocket = new ServerSocket(8000, 100);
			while(true) 
			{
				try
				{
					System.out.println("waiting for connection");
					connection = serverSocket.accept();

					System.out.println("Client has connected");
					processConnections();

					System.out.println("waiting for input");
					do
					{
						message = input.next() + "\n";

						System.out.println("Received message: " + message);
						response = Integer.toString(message.length()) + "\n";
						System.out.println("Returning length: " + response);
						
						output.format(response);
						output.flush();
						System.out.println("Sent response" + "\n");
					}while(!message.toUpperCase().equals("EXIT"));
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
					output.close();
					input.close();
					connection.close();
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
}