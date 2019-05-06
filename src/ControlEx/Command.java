package ControlEx;

public class Command 
{
	private CommandTypeInfo commandType;
	private Object[] params;
	
	public Command(CommandTypeInfo commandType, Object[] params)
	{
		//Class[] c = commandType.getParamTypes();
		this.commandType = commandType;
		this.params = params;
		
		/*for(int i = 0; i < c.length; i++)
		{
			try
			{
				this.params[i] = c[i].cast(c[i].getClass().);
			}
			catch(Exception ex)
			{
				System.out.println("Yeet");
			}
		}*/

	}
	
	public Object[] getParams()
	{
		return this.params;
	}
	
	public CommandTypeInfo getCommandType()
	{
		return this.commandType;
	}
}
