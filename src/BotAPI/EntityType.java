package BotAPI;

import Entities.*;

public enum EntityType 
{
	GOOD_PLANT(GoodPlant.class),
	BAD_PLANT(BadPlant.class),
	GOOD_BEAST(GoodBeast.class),
	BAD_BEAST(BadBeast.class),
	WALL(Wall.class),
	MASTER_SQUIRREL(MasterSquirrel.class),
	MINI_SQUIRREL(MiniSquirrel.class);
	
	private Class param;
	
	private EntityType(Class param)
	{
		this.param = param;
	}
	
	public Class getParamType()
	{
		return this.param;
	}
}
