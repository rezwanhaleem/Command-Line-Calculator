package edu.nyit.csci.cp2.ch9.project3;

public class EmptyMemoryException extends Exception
{

	public EmptyMemoryException() 
	{
		super("\nThe memory is emtpy!\n");
	}

	public EmptyMemoryException(String message) 
	{
		super(message);
	}
}
