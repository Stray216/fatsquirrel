package core;


import java.util.Random;

import Entities.BadBeast;
import Entities.BadPlant;
import Entities.Entity;
import Entities.GoodBeast;
import Entities.GoodPlant;
import Entities.HandOperatedMasterSquirrel;
import Entities.MasterSquirrel;
import Entities.MiniSquirrel;

import java.lang.Math.*;

public class FlattenedBoard implements BoardView, EntityContext
{
	private Board board;
	private XY size;
	private Entity[][] boardArray;
	

	public FlattenedBoard(Board board) 
	{
		this.board = board;
		this.size = board.getSize();
		this.boardArray = new Entity[size.getX()][size.getY()];
		Entity[] entities = board.getEntitySet().getEntities();
		
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] != null)
			{
				int x = entities[i].getPos().getX();
				int y = entities[i].getPos().getY();
				boardArray[y][x] = entities[i];
			}
		}
	}
	
	public Entity[][] getArray()
	{
		return boardArray;
	}

	public XY getSize()
	{
		return size;
	}
	
	public Entity getEntityType(int x, int y)
	{
		if(boardArray[y][x] != null)
		{
			return boardArray[y][x];
		}
		else
		{
			return null;
		}
	}
	
	public Entity getEntityType(XY pos)
	{
		return boardArray[pos.getY()][pos.getX()];
	}
	
	public void kill(Entity entity)
	{
		board.getEntitySet().deleteEntity(entity.getID());
	}
	
	public void killAndReplace(Entity entity)
	{
		EntitySet set = board.getEntitySet();
		Random rand = new Random();
		int x = rand.nextInt(size.getX()-2) + 1;
		int y = rand.nextInt(size.getY()-2) + 1;
		
		if(entity instanceof GoodPlant){
			set.createEntity(new GoodPlant(set.getEntityCount(), x, y));
		}
		else if(entity instanceof BadPlant){
			set.createEntity(new BadPlant(set.getEntityCount(), x, y));;
		}
		else if(entity instanceof GoodBeast){
			set.createEntity(new GoodBeast(set.getEntityCount(), x, y));;
		}
		else if(entity instanceof BadBeast){
			set.createEntity(new BadBeast(set.getEntityCount(), x, y));;
		}
		
		kill(entity);
	}
	
	public Entity nearestPlayerEntity(XY pos)
	{
		Entity[] entities = board.getEntitySet().getEntities();
		Entity a = null;
		double smol = 100;
		
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] instanceof MasterSquirrel || entities[i] instanceof HandOperatedMasterSquirrel || entities[i] instanceof MiniSquirrel)
			{
				XY temp = entities[i].getPos();
				int x = temp.getX() - pos.getX();
				int y = temp.getY() - pos.getY();
				double dist = Math.sqrt(x^2+y^2);
				
				if(dist < smol)
				{
					smol = dist;
					a = entities[i];
				}
			}
		}
		
		return a;
	}
	
	public void tryMove(MiniSquirrel miniSquirrel, XY direction)
	{
		int x = miniSquirrel.getPos().getX();
		int y = miniSquirrel.getPos().getY();
		if(boardArray[y + direction.getY()][x + direction.getX()] == null)
		{
			miniSquirrel.move(direction);
		}
	}
	
	public void tryMove(GoodBeast goodBeast, XY direction)
	{
		int x = goodBeast.getPos().getX();
		int y = goodBeast.getPos().getY();
		if(boardArray[y + direction.getY()][x + direction.getX()] == null)
		{
			goodBeast.move(direction);
		}
	}
	
	public void tryMove(BadBeast badBeast, XY direction)
	{
		int x = badBeast.getPos().getX();
		int y = badBeast.getPos().getY();
		if(boardArray[y + direction.getY()][x + direction.getX()] == null)
		{
			badBeast.move(direction);
		}
	}
	
	public void tryMove(MasterSquirrel masterSquirrel, XY direction)
	{
		int x = masterSquirrel.getPos().getX();
		int y = masterSquirrel.getPos().getY();
		if(boardArray[y + direction.getY()][x + direction.getX()] == null)
		{
			masterSquirrel.move(direction);
		}
	}
	
	public String toString()
	{
		String s = "";
		for(int i = 0; i < this.size.getY(); i++)
		{
			for(int j = 0; j < this.size.getX();j++)
			{
				if(boardArray[i][j] != null)
				s += boardArray[i][j].toString() + "\n";
			}
		}
		return s;
	}
}