package edu.nyit.csci.cp2.ch9.project3;

import java.util.Scanner;

public class Calculator
{
	private double result;
	private double memory;
	private double precision = 0.0001;

	public static void main(String[] args)
	{
		Calculator clerk = new Calculator();
		try
		{
			System.out.println("Calculator is on.");
			System.out.print("Format of each line: ");
			System.out.println("operator space number");
			System.out.println("For example: + 3");
			System.out.println("To end, enter the letter e.");
			clerk.doCalculation();
		}
		catch(UnknownOpException|EmptyMemoryException e)
		{
			clerk.handleUnknownOpException(e);
		}
		catch(DivideByZeroException e)
		{
			clerk.handleDivideByZeroException(e);
		}
		System.out.println("The final result is " + clerk.getResult());
		System.out.println("Calculator program ending.");
	}
	public Calculator()
	{
		result = 0;
	}

	public void handleDivideByZeroException(DivideByZeroException e)
	{
		System.out.println("Dividing by zero.");
		System.out.println("Program aborted");
		System.exit(0);
	}
	public void handleUnknownOpException(Exception e)
	{
		System.out.println(e.getMessage());
		System.out.println("Try again from the beginning:");
		try
		{
			System.out.print("Format of each line: ");
			System.out.println("operator number");
			System.out.println("For example: + 3");
			System.out.println("To end, enter the letter e.");
			doCalculation();
		}
		catch(UnknownOpException e2)
		{
			System.out.println(e2.getMessage());
			System.out.println("Try again at some other time.");
			System.out.println("Program ending.");
			System.exit(0);
		}
		catch(DivideByZeroException e3)
		{
			handleDivideByZeroException(e3);
		}
		catch(EmptyMemoryException eme)
		{
			System.out.println(eme.getMessage());
			System.out.println("Try again at some other time.");
			System.out.println("Program ending.");
			System.exit(0);
		}
	}
	public void setResult(double newResult)
	{
		result = newResult;
	}
	public double getResult()
	{
		return result;
	}

	public double evaluate(char op, double n1, double n2) throws DivideByZeroException, UnknownOpException
	{
		double answer;
		switch (op)
		{
		case '+':
			answer = n1 + n2;
			break;
		case '-':
			answer = n1 - n2;
			break;
		case '*':
			answer = n1 * n2;
			break;
		case '/':
			if ((-precision < n2) && (n2 < precision))
				throw new DivideByZeroException();
			answer = n1 / n2;
			break;
		default:
			throw new UnknownOpException(op);
		}
		return answer;
	}

	public void doCalculation() throws DivideByZeroException,UnknownOpException, EmptyMemoryException
	{
		Scanner keyboard = new Scanner(System.in);
		boolean done = false;
		result = 0;
		System.out.println("result = " + result);
		while (!done)
		{
			String nextOp = keyboard.next().trim();
			if (nextOp.equalsIgnoreCase("e"))
				done = true;
			else if (nextOp.equalsIgnoreCase("c"))
			{
				result = 0.0;
				System.out.println("The result has been cleared.");
				System.out.println("updated result = " + result);
			}
			else if (nextOp.equalsIgnoreCase("m"))
			{	
				memory = result;
				System.out.println("The result has been saved in memory.");
			}
			else if (nextOp.equalsIgnoreCase("r"))
			{	
				if( memory == 0.0 )
				{
					throw new EmptyMemoryException();
				}
				else
				{
					System.out.println("Recalling the memory gives the value: " + memory + "\n");
				}
			}
			else
			{
				double nextNumber = Double.parseDouble(nextOp.substring(1));
				result = evaluate(nextOp.charAt(0), result, nextNumber);
				System.out.println("result " + nextOp.charAt(0) + " " + nextNumber + " = " + result);
				System.out.println("updated result = " + result);
			}
		}
	}
}
