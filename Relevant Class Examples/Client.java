import java.io.*;
import java.net.*;
import java.util.*;

public class Client
{
	public static void main(String args[])
	{
		private ServerSocket serverSocket;
		private Socket connection = null;
		private Scanner input = null;
		private Formatter output = null;
		private double area;
		private double radius; 
		
		while(true)
		{
			try
			{
				connection = new Socket("localhost", 64494);
				input = new Scanner(connection.getInputStream()
				output = new Formatter(connection.getInputStream)
				
				System.out.println("Enter radius ")
				radius = myInput.nextDouble();
				
				while(radius!=0)
				{
					output.format("%.2f\n", radius);
					output.flush();
					
					area = input.nextDouble();
					System.out.println("Area " + area);
					System.out.println("Enter radius");
					radius = myInput.nextDouble();
				}
			}
			catch(IOException ieo)
			{
				ioe.printStackTrace();
			}
			finally 
			{
				
			}
		}
	}
}