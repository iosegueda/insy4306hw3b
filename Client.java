//Iris Osegueda
import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
	private static Socket connection = null;

	public static void main(String args[])
	{
		Scanner input = null;
		Formatter output = null;
		Scanner clientInput = null;
		try
		{
			connection = new Socket("localhost", 8000);
			input = new Scanner(connection.getInputStream());
			output = new Formatter(connection.getOutputStream());
			clientInput = new Scanner(System.in);
			String message = "";

			while(true)
			{
				System.out.println("Please enter a message");
				message = clientInput.next() + "\n";

				System.out.println("Sending message: " + message);
				output.format(message);
				output.flush();

				System.out.println("Waiting for response");
				System.out.println(input.next() + "\n");
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