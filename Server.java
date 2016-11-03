//Iris Osegueda
import java.io.*;
import java.net;
import java.util.*;

public class Server
{
	private ServerSocket serverSocket;
	private Socket connection = null;
	private Scanner myInput = new Scanner(System.in);
	private Formatter output = null;
	private String stringMessage;
	
	public void runServer()
	
	while(true) 
	{
		try
		{
			serverSocket = new ServerSocket(8000, 100);
			
			System.out.println("waiting for input"):
			connection = serverSocket.accept();
			
			string = input.nextLine(); 
			System.out.println("Received message: " + string);
			output.flush();
			
			countMessage();
			
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
public void countMessage(String stringMessage)
{
	int wordCount = 0; 
	int charCount = 0;
	String allCounts = "";
	
	String[] words = line.split( "\\s" );
                
	wordCount += words.length;
	
	for( int i = 0; i < words.length; i++ )
		charCount += words[ i ].length();
	
	allCounts = ( "nNumber of words: " + wordCount +
					"\nNumber of characters:  " + charCount );
	
	System.out.println(allCounts);
}