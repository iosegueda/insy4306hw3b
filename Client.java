//Iris Osegueda
import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
	public static void main(String args[])
	{
		private ServerSocket serverSocket;
		private Socket connection = null;
		private Scanner myInput = new Scanner(System.in);
		private Formatter output = null;
		private String stringMessage;
		
		while(true)
		{
			try
			{
				connection = new Socket("localhost", 8000);
				input = new Scanner(connection.getInputStream());
				output = new Formatter(connection.getOutputStream());
				
				System.out.println("Please enter a message");
				stringMessage = myInput.nextLine();
				
				output.flush();
				input.close();
				output.close();
				connection.close();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}
}