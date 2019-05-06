package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

import Entities.BadBeast;
import Entities.BadPlant;
import Entities.Entity;
import Entities.GoodBeast;
import Entities.GoodPlant;
import Entities.HandOperatedMasterSquirrel;
import Entities.MasterSquirrel;
import Entities.MiniSquirrel;
import Entities.Wall;

public class ConsoleUI implements UI
{
	
	private PrintStream outputStream = System.out;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private Command lastCommand;
	
	
	/*public Command getCommand()
	{
		CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);
		Command command = commandScanner.next();
		return command;
	}*/
	
	public Command getCommand()
	{
		Command command = this.lastCommand;
		this.lastCommand = null;
		return command;
	}
	
	public void setCommand()
	{
			CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);
			Command command = commandScanner.next();
			this.lastCommand = command;
	}
	
	public void render(BoardView view)
	{
		int x = view.getSize().getX();
		int y = view.getSize().getY();
		
		for(int i = 0; i < y; i++)
		{
			for(int j = 0; j < x; j++)
			{
				Entity temp = view.getEntityType(j, i);
				
				if(temp instanceof Wall){
					System.out.print("W");
				}
				else if(temp instanceof GoodPlant){
					System.out.print("G");
				}
				else if(temp instanceof BadPlant){
					System.out.print("B");
				}
				else if(temp instanceof GoodBeast){
					System.out.print("g");
				}
				else if(temp instanceof BadBeast){
					System.out.print("b");
				}
				else if(temp instanceof MasterSquirrel || temp instanceof HandOperatedMasterSquirrel){
					System.out.print("S");
				}
				else if(temp instanceof MiniSquirrel){
					System.out.print("s");
				}
				else if(temp == null){
					System.out.print(" ");
				}
			}
			
			System.out.println();
			
		}		
	}
	
}
