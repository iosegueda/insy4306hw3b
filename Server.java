import java.io.*;
import java.util.*;
import java.net.*;
import java.util.concurrent.*; //have to do it separetely bc it isn't a class 
//we know bc its not capitalized

public class ServerMT 
{
	private static ServerSocket serverSocket;
	private static Socket connection = null;

	public static void main(String args[])
	{		
		try 
		{
			serverSocket = new ServerSocket(8000, 100);
			
			int clientNo = 1;
			
			ExecutorService threadExecutor = Executors.newCachedThreadPool();
			while(true)
			{
				connection = serverSocket.accept();
				System.out.println("Start thread for client " + clientNo);
				
				HandleAClient thread = new HandleAClient(connection, clientNo);
				
				threadExecutor.execute(thread);
				
				clientNo++;
				//everytime a client connects with us we're going to give them a number
				//and start their own thread 
			}
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}

//everytime a client connects with us we're going to give them a number
//and start their own thread 
class HandleAClient implements Runnable
{
	private Scanner input;
	private Formatter output;
	private Socket connection;
	private int clientNo;
	private String message = "";
	private String response = "";
	
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

				System.out.println("Received message from client " + this.clientNo + ": " + message);
				response = Integer.toString(message.length() - 1) + "\n";
				System.out.println("Returning length: " + response);
				
				output.format(response);
				output.flush();
			}
			System.out.println("thread " + clientNo + " ended");
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}