package core;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.Scanner;

public class CommandScanner
{

	CommandTypeInfo[] commandTypeInfos;
	BufferedReader inputReader;
	PrintStream outputStream = System.out;

	public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader){
		commandTypeInfos = commandTypes;
		this.inputReader = inputReader;
	}
	
	public Command next()
	{
		//Scan Console Words
		Scanner scanner = new Scanner(inputReader);
		String inputString = scanner.nextLine();
		String[] inputArr = inputString.split(" ");
	
		//Split input into command and params
		String command = inputArr[0];
		String[] params = new String[inputArr.length - 1];
		for(int i = 0; i < params.length; i++)
		{
			params[i] = inputArr[i + 1];
		}
		
		//Depending on the type of required parameter in the target method -> change String to required Object-type
		Object[] ObjParams = new Object[params.length];
		for(int i = 0; i < ObjParams.length; i++)
		{
			for(int j = 0; j < commandTypeInfos.length; j++)
			{
				CommandTypeInfo commandType = commandTypeInfos[j];
				if(commandType.getName().equals(command))
				{
					if(commandType.getParamType()[i] == int.class)
					{
						ObjParams[i] = Integer.parseInt(params[i]);
					}
					else if(commandType.getParamType()[i] == String.class)
					{
						ObjParams[i] = params[i];
					}
					else if(commandType.getParamType()[i] == double.class)
					{
						ObjParams[i] = Double.parseDouble(params[i]);
					}
					else if(commandType.getParamType()[i] == long.class)
					{
						ObjParams[i] = Long.parseLong(params[i]);
					}
				}
			}
		}

		//Check CommandTypes for command string
		for(int i = 0; i < commandTypeInfos.length; i++)
		{
			CommandTypeInfo commandType = commandTypeInfos[i];
			if(commandType.getName().equals(command))
			{
				return new Command(commandType, ObjParams);
			}
		}

		//no command found
		return null;
	}
	
}
