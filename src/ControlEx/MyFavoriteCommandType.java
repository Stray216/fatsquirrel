package ControlEx;

public enum MyFavoriteCommandType implements CommandTypeInfo
{	
	HELP("help", "  * list all commands"),
	EXIT("exit", "  * exit program"),	// Wie bekomme ich von diesen .class Parametern die Params in Command als Richtiges Object eines primitiven Datentyps ?
	ADDI("addi", "<Integer1>  <Integer2>   * simple integer add ",int.class, int.class ),
	ADDF("addf", "<Float1>  <Float2>   * simple float add ",float.class, float.class ),
	ECHO("echo", "<String>  <Integer>   * echos param1 string param2 times ",String.class, int.class );
	
	private String name;
	private String description;
	private Class param1 = null;
	private Class param2 = null;
	
	private MyFavoriteCommandType(String s1, String s2, Class c1, Class c2)
	{
		this.name = s1;
		this.description = s2;
		this.param1 = c1;
		this.param2 = c2;
	}
	
	private MyFavoriteCommandType(String s1, String s2)
	{
		this.name = s1;
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
	
	public Class[] getParamTypes()
	{
		Class[] params = new Class[2];
		params[0] = this.param1;
		params[1] = this.param2;
		return params;
	}
}
