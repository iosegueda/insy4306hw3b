import java.io.*;
//how to pass a string(which is considered an object)
//on the hw you wont need a class String, there should already be one there  

public class Student implements Serializable
{
	private String name;
	private int id;
	
	public Student (String n, int i)
	{
		name = n;
		id = i;
	}
	public void setName(String n)
	{
		name = n;
	}
	public String toString()
	{
		return(name = "" + id);
	}
}