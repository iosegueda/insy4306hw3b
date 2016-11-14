//Iris Osegueda 
import java.io.*;
import java.util.*;
import java.net.*;
import java.util.concurrent.*; 

public class MultiServer 
{
	public static void main(String args[])
	{
		ServerSocket serverSocket;
		Socket connection;
		
		try 
		{
			serverSocket = new ServerSocket(8000);
			
			int clientNo = 1;
			
			ExecutorService threadExecutor = Executors.newCachedThreadPool();
			while(true)
			{
				connection = serverSocket.accept();
				System.out.println("Started thread for client# " + clientNo);
				
				HandleAClient thread = new HandleAClient(connection, clientNo);
				
				threadExecutor.execute(thread);
				
				clientNo++;
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}

class HandleAClient implements Runnable
{
	Scanner input;
	Formatter output;
	Socket connection;
	int clientNo;
	String message = "";
	String response = "";
	
	public HandleAClient(Socket connection, int clientNo)
	{
		this.connection = connection;
		this.clientNo = clientNo;	
	}
	public void run()
	{
		try
		{
			System.out.println("waiting for input");
			input = new Scanner(connection.getInputStream());
			output = new Formatter(connection.getOutputStream());
			
			while(input.hasNext())
			{
				message = input.next() + "\n";

				System.out.println("Received message: " + message);
				response = Integer.toString(message.length() - 1) + "\n";
				System.out.println("Returning length: " + response);
				
				output.format(response);
				output.flush();
				System.out.println("Sent response to client# " + clientNo + "\n");
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}