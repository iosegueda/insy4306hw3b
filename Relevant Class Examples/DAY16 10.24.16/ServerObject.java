import java.io.*;
import java.util.*;
import java.net.*;
//sends a student ? maybe receives a student?
//changes it
//sends it back
//were changing something on the server and send it back 
//hw key will be sending it back as an object, 
////you'll receive it as a int bc its the length of a string
//server always has to start first

public class ServerObject
{
	public static void main(String args[])
	{
		ServerSocket serverSocket;
		Socket connection;
		ObjectInputStream input;
		ObjectOutputStream output;
		Student s = null;
		Object obj;
		
		try
		{
			serverSocket = new ServerSocket(8000);
			System.out.println("Waiting for input");
			connection = serverSocket.accept();
			
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			
			obj = input.readObject();
			System.out.println(obj.toString());
			
			//change the name from Jones to Smith
			if (obj instanceof Student)
			{
				((Student)obj).setName("Smith");
			}
			output.writeObject(obj);
			output.flush();
			
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