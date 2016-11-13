import java.io.*;
import java.util.*;
import java.net.*;
import java.util.concurrent.*; //have to do it separetely bc it isn't a class 
//we know bc its not capitalized

public class MultiThreadServer 
{
	public static void main(String args[])
	{
		ServerSocket serverSocket();
		Socket connection;
		
		try 
		{
			serverSocket = new ServerSocket(8000);
			
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
class HandleAclient implements Runnable
{
	Scanner input;
	Formatter output;
	Socket connection;
	int clientNo;
	double radius;
	double area;
	
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
				radius = input.nextDouble();
				System.out.println("radius from client " + clientNo + 
					"radius " + radius);
				area = radius * radius * Math.PI;
				output.format("%.2f\n", area)
			}
			System.out.println("thread " + clientNo + " ended")
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}