package core;

import java.util.Random;

import Entities.BadBeast;
import Entities.BadPlant;
import Entities.GoodBeast;
import Entities.GoodPlant;
import Entities.HandOperatedMasterSquirrel;
import Entities.Wall;

public class Board 
{
	private EntitySet entities = new EntitySet();
	private XY size;
	
	public Board(BoardConfig conf)
	{
		initialize(conf);
		this.size = conf.getSize();
	}
	
	public void initialize(BoardConfig conf)
	{
		XY a = conf.getSize();
		int maxX = a.getX();
		int maxY = a.getY();
		Random rand = new Random();
		
		for(int i = 0; i < maxX; i++)
		{
			entities.createEntity(new Wall(entities.getEntityCount(), 0, i));
			entities.createEntity(new Wall(entities.getEntityCount(), i, 0));
			entities.createEntity(new Wall(entities.getEntityCount(), maxY-1, i));
			entities.createEntity(new Wall(entities.getEntityCount(), i, maxX-1));
		}
		
		for(int i = 0; i < conf.getWallCount(); i++)
		{
			int x = rand.nextInt(maxX-2) + 1;
			int y = rand.nextInt(maxY-2) + 1;
			entities.createEntity(new Wall(entities.getEntityCount(), x, y));
		}
		
		for(int i = 0; i < conf.getPlantCount(); i++)
		{
			int x = rand.nextInt(maxX-2) + 1;
			int y = rand.nextInt(maxY-2) + 1;
			
			if(rand.nextBoolean())
			{
				entities.createEntity(new GoodPlant(entities.getEntityCount(), x, y));
			}
			else
			{
				entities.createEntity(new BadPlant(entities.getEntityCount(), x, y));
			}
		}
		
		for(int i = 0; i < conf.getBeastCount(); i++)
		{
			int x = rand.nextInt(maxX-2) + 1;
			int y = rand.nextInt(maxY-2) + 1;
			
			if(rand.nextBoolean())
			{
				entities.createEntity(new GoodBeast(entities.getEntityCount(), x, y));
			}
			else
			{
				entities.createEntity(new BadBeast(entities.getEntityCount(), x, y));
			}
		}
		
		int x = rand.nextInt(maxX-2) + 1;
		int y = rand.nextInt(maxY-2) + 1;
		entities.createEntity(new HandOperatedMasterSquirrel(entities.getEntityCount(), x, y));
		
	}
	
	public XY getSize()
	{
		return size;
	}
	
	public FlattenedBoard flatten()
	{
		return new FlattenedBoard(this);
	}
	
	public EntitySet getEntitySet()
	{
		return entities;
	}
	
	public String toString()
	{
		return entities.toString();
	}
	
	public void update()
	{
		EntityContext entityContext = this.flatten();
		entities.nextStep(entityContext);
	}
}
