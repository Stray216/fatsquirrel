package core;

import Entities.Entity;
import Entities.MasterSquirrel;
import Entities.MiniSquirrel;

public class EntitySet 
{
	private int maxEntities = 300;
	private Entity[] entities = new Entity[ maxEntities ];
	private int entityCount = 0;
	
	public boolean createEntity(Entity entity)
	{
		
		//Check all entities for matching ID
		for(int i = 0; i < entities.length; i++) 
		{

			if(entities[i] != null)
			{
				if( entities[i].getID() == entity.getID() )
				return false;
			}
		}
		
		//Insert entity into free space in array
		for(int i = 0; i < entities.length; i++) 
		{
			if( entities[i] == null)
			{
				entities[i] = entity;
				entityCount ++;
				return true;
			}
		}
		
		//No free space was available
		return false;
	}
	
	public boolean deleteEntity(int delete_id) 
	{
		//Check for matching id and set entity to null
		for(int i = 0; i < entities.length; i++) 
		{
			if( entities[i].getID() == delete_id )
			{				
				entities[i] = null;
				return true;
			}
		}
		
		//Entity ID not found
		return false;
	}
	
	public String toString() 
	{
		String myString = "";
		for(int i = 0; i < entities.length; i++) 
		{
			if(entities[i] != null)
				//All entity strings, then a new line
				myString += ( entities[i].toString() + "\n" );
		}
		return myString;
	}
	
	public void nextStep(EntityContext entityContext) 
	{
	
		for(int i = 0; i < entities.length; i++)
		{
			if(entities[i] != null)
			{
				entities[i].nextStep(entityContext);
			}
		}
			
			
		for(int i = 0; i < entities.length; i++)
		{
			//MasterSquirrel requested a MiniSquirrel
			if( entities[i] instanceof MasterSquirrel) 
			{
				if( ((MasterSquirrel) entities[i]).getMiniRequest() ) 
				{
					createEntity( new MiniSquirrel( entityCount+1, ((MasterSquirrel) entities[i]).getMiniEnergy(), new XY(entities[i].getPos().getX() + ((MasterSquirrel)entities[i]).getMiniDirection().getX() , entities[i].getPos().getY() + ((MasterSquirrel)entities[i]).getMiniDirection().getY()), entities[i].getID()));
					entities[i].updateEnergy(-(((MasterSquirrel)entities[i]).getMiniEnergy()));
					((MasterSquirrel)entities[i]).setMiniRequest(false);
				}
			}	
		}
			
			/*
			//Iterate nextStep for each Entity
			if(entities[i] != null)
			{
				entities[i].nextStep();
			}
			
			if(entities[i] instanceof Squirrel)
			{
				for(int j = 0; j < entities.length; j++)
				{
					if(entities[j] instanceof GoodPlant && entities[j].getPos().equals(entities[i].getPos()))
					{
						entities[i].addEnergy(entities[j].getEnergy());
						deleteEntity(entities[j].getID());
					}
				}
			}
			
			//Position reset if collision
			for(int j = 0; j < entities.length; j++)
			{
				if(entities[i] != null && entities[j] != null && i != j)
				{
					if(entities[i].getPos().equals(entities[j].getPos()))
					{
						System.out.println("Position ist besetzt!");
						//System.out.println(entities[i].getPreviousPos().getX());
						//System.out.println(entities[i].getPreviousPos().getY());
						entities[i].setPos(entities[i].getPreviousPos());
					}
				}
			}
		}*/
	}
	
	public int getEntityCount()
	{
		return entityCount;
	}
	
	public Entity[] getEntities()
	{
		return entities;
	}
}
