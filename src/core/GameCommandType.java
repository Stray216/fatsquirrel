package core;

public enum GameCommandType implements CommandTypeInfo
{
	HELP("help", "  * list all commands"),
	EXIT("exit", "  * exit program"),
	ALL("all", "  * list all entities"),
	LEFT("a", "  * move left"),
	RIGHT("d", "  * move right"),
	UP("w", "  * move up"),
	DOWN("s", "  * move down"),
	MASTER_ENERGY("energy", "  * display current Energy"),
	SPAWN_MINI("mini", "  * spawn new mini below Master", int.class);
	
	private String name;
	private String description;
	private Class[] params;
	
	private GameCommandType(String s1, String s2, Class...params)
	{
		this.name = s1;
		this.description = s2;
		this.params = params;
	}
	
	private GameCommandType(String s1, String s2)
	{
		this.name= s1;
		this.description = s2;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getHelpText()
	{
		String s = this.name + "," + this.description;
		return s;
	}
	
	public Class[] getParamType()
	{
		return this.params;
	}
}
