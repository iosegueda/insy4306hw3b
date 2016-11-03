//client send over a radius and the server will send back what the area is 
import java.io.*;
import java.net;
import java.util.*;

public class SingleServer
{
	public static void main(String args[])
	{
		ServerSocket serverSocket;
		Socket connection;
		
		Scanner input;
		Formatter output;
		
		double radius;
		double area;
		
		try
		{
			serverSocket = new ServerSocket(8000);
			//you want to pick a port thats not very commonly used 
			//higher than 1024 but never than 7,000 
			//if it says there's a bind exception when you run it then just pick another number
			
			System.out.println("waiting for input"):
			connection = serverSocket.accept();
			
			input = new Scanner(connection.getInputStream());
			output = new Formatter(connection.getOutputStream());
			
			radius = input.nextDouble();
			System.out.println("Got radius " + radius);
			//this is for us just so we know where we're at 
			
			area = radius*radius*Math.PI;
			//calcuates area
			output.format("%.2f\n", area);
			output.flush(); //forces it to finish whatever is there 
			
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