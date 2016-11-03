import java.io.*;
import java.net.*;
import java.util.*;
//now you need object streams
//uses class student that we created 

public class ClientObject
{
	public static void main(String args[])
	{
		Socket connection; 
		ObjectOutputStream output;
		ObjectInputStream input;
		
		Object obj;
		Student s = new Student("Jones", 1234);
		
		try
		{
			connection = new Socket("localhost", 8000);
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			
			output.writeObject(s);
			output.flush();
			
			objec = (Object)input.readObject();
			System.out.println(obj.toString());	
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
}