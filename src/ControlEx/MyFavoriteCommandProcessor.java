package ControlEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class MyFavoriteCommandProcessor 
{
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	
	public void process()
	{
		 CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader);
	        
		 help();
		// the loop over all commands with one input line for every command
		 while (true) 
		 { 
		     Command command = commandScanner.next();
			
		     Object[] params = command.getParams();
		     
		     MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandType();
		     
		     switch (commandType) 
		     {
		     	case EXIT:
		     		System.exit(0);
		     	case HELP: 
		     		help();
		     		break;
		     	case ADDI:
		     		int i = Integer.parseInt((String)params[0]) + Integer.parseInt((String)params[1]);
		     		outputStream.println(i);
		     		break;
		     	case ADDF:
		     		float f = Float.parseFloat((String)params[0]) + Float.parseFloat((String)params[1]);
		     		outputStream.println(f);
		     		break;
		     	case ECHO:
		     		for(int j = 0; j < (Integer.parseInt((String)params[1])); j ++)
		     		{
		     			outputStream.println((String)params[0]);
		     		}
		     		break;
		     }
		 }
	}
	
	public void help()
	{
		CommandTypeInfo[] commandTypes = MyFavoriteCommandType.values();
		
		for(int i = 0; i < commandTypes.length; i ++)
		{
			System.out.println(commandTypes[i].getHelpText());
		}
	}
	
	public static void main(String[] args)
	{
		MyFavoriteCommandProcessor proc = new MyFavoriteCommandProcessor();
		proc.process();
	}
}


