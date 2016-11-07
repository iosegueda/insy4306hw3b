//Iris Osegueda
import java.io.*;
import java.net.*;
import java.util.*;

public class Server
{
	private static ServerSocket serverSocket;
	private static Socket connection = null;
	
	public static void main(String[] args)
	{
		Scanner input = null;
		Formatter output = null;
		String message = "";
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
					input = new Scanner(connection.getInputStream());
					output = new Formatter(connection.getOutputStream());

					System.out.println("waiting for input");
					while(true)
					{
						message = input.next() + "\n";

						System.out.println("Received message: " + message);
						response = Integer.toString(message.length() - 1) + "\n";
						System.out.println("Returning length: " + response);
						
						output.format(response);
						output.flush();
						System.out.println("Sent response" + "\n");
					}
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
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