package edu.nyit.csci.cp2.ch9.project3;

public class DivideByZeroException extends Exception
{
	public DivideByZeroException()
	{
		super("Dividing by Zero!");
	}
	public DivideByZeroException(String message)
	{
		super(message);
	}
}