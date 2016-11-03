import java.io.*;
import java.net.*;
import java.util.*;

public class ClientSingle
{
	public static void main(String args[])
	{
		Scanner myInput = new Scanner(System.in);
		Scanner input; 
		Socket connection;
		Formatter output;
		double radius;
		double area;
		
		try
		{
			connection = new Socket("localhost", 8000);
			input = new Scanner(connection.getInputStream());
			output = new Formatter(connection.getOutputStream());
			
			System.out.println("Please enter a radius");
			radius = myInput.nextDouble();
			
			output.format("%.2f\n", radius);
			//everytime you send you have to flush
			output.flush();
			
			//now were waiting to get something back 
			area = input.nextDouble();
			
			System.out.println("area is " + area);
			
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