package ControlEx;

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
	
	public Command next(){
		//Scan Console Words
		Scanner scanner = new Scanner(inputReader);
		String inputString = scanner.nextLine();
		String[] inputArr = inputString.split(" ");
	
		//Split input into command and params
		String command = inputArr[0];
		String[] params = new String[inputArr.length - 1];
		for(int i = 0; i < params.length; i++){
			params[i] = inputArr[i + 1];
		}

		//Check CommandTypes for command string
		for(int i = 0; i < commandTypeInfos.length; i++)
		{
			CommandTypeInfo commandType = commandTypeInfos[i];
			if(commandType.getName().equals(command))
			{
				return new Command(commandType, params);
			}
		}

		//no command found
		return null;
	}
	
}