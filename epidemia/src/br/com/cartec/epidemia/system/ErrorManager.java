package br.com.cartec.epidemia.system;

public class ErrorManager
{
	static boolean debug = true;
	
	public static void printAndExit(Throwable e)
	{
		e.printStackTrace();
		System.exit(0);
	}
}
