package core;

import Entities.Entity;
import Entities.HandOperatedMasterSquirrel;
import Entities.MasterSquirrel;

public class GameImpl2 extends Game
{
	public GameImpl2(XY size, int wallCount, int plantCount, int beastCount, UI ui)
	{
		super(new State(new Board(new BoardConfig(size, wallCount, plantCount, beastCount))), ui, 10);
	}
	
	//Default
	public GameImpl2()
	{
		super(new State(new Board(new BoardConfig())), new ConsoleUI(), 1);
	}
	
	@Override
	protected void processInput()
	{
		try 
		{
			Command command = this.ui.getCommand();
			
			if(command == null)
			{
				return;
			}
			
			CommandTypeInfo commandTypeInfo = command.getCommandType();		
			
			getClass().getMethod(commandTypeInfo.getName(), commandTypeInfo.getParamType()).invoke(this, command.getParams());
		}
		/*catch(IllegalAccesException | InvocationTargetException | NoSuchMethodException | IOException e)
		{
			e.printStackTrace();
		}*/
		catch(Exception e)
		{
			System.out.println("Invalid Command");
			e.printStackTrace();
		}
	}
	
	public void help()
	{
		CommandTypeInfo[] commandTypes = GameCommandType.values();
		
		for(int i = 0; i < commandTypes.length; i ++)
		{
			System.out.println(commandTypes[i].getHelpText());
		}
	}
	
	public void exit()
	{
		System.exit(0);
	}
	
	public void all()
	{
		System.out.println(this.state.getBoard().toString());
	}
	
	public void energy()
	{
		Entity[] entities = this.state.getBoard().getEntitySet().getEntities(); 
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] instanceof HandOperatedMasterSquirrel)
			{
				System.out.println(((HandOperatedMasterSquirrel)entities[i]).getEnergy());
			}
		}
	}
	
	public void mini(int energy)
	{
		Entity[] entities = this.state.getBoard().getEntitySet().getEntities(); 
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] instanceof HandOperatedMasterSquirrel)
			{
				((MasterSquirrel)entities[i]).makeMini(energy);
			}
		}
	}
	
	public void move(XY next)
	{
		Entity[] entities = this.state.getBoard().getEntitySet().getEntities(); 
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] instanceof HandOperatedMasterSquirrel)
			{
				((HandOperatedMasterSquirrel)entities[i]).setNextMove(next);
			}
		}
	}
	public void a()
	{
		XY next = new XY(-1,0);
		move(next);
	}
	
	public void w()
	{
		XY next = new XY(0,-1);
		move(next);
	}
	
	public void s()
	{
		XY next = new XY(0, 1);
		move(next);
	}
	
	public void d()
	{
		XY next = new XY(1, 0);
		move(next);
	}
}
